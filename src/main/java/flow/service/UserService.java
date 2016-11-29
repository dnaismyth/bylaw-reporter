package flow.service;

import flow.domain.Authority;
import flow.domain.RUser;
import flow.dto.RoleType;
import flow.dto.User;
import flow.repository.AuthorityRepository;
import flow.repository.UserRepository;
import flow.security.AuthoritiesConstants;
import flow.security.SecurityUtils;
import flow.service.mapper.UserMapper;
import flow.service.util.RandomUtil;
import flow.service.util.RestPreconditions;
import flow.web.rest.dto.ManagedUserDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

import javax.inject.Inject;

import java.util.*;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class UserService {
	
    private final Logger log = LoggerFactory.getLogger(UserService.class);
    private UserMapper userMapper = new UserMapper();
    
    @Inject
    private PasswordEncoder passwordEncoder;


    @Inject
    private UserRepository userRepository;

    @Inject
    private AuthorityRepository authorityRepository;

    public Optional<RUser> activateRegistration(String key) {
        log.debug("Activating user for activation key {}", key);
        return userRepository.findOneByActivationKey(key)
            .map(user -> {
                // activate given user for the registration key.
                user.setActivated(true);
                user.setActivationKey(null);
                userRepository.save(user);
                log.debug("Activated user: {}", user);
                return user;
            });
    }

    public Optional<RUser> completePasswordReset(String newPassword, String key) {
       log.debug("Reset user password for reset key {}", key);

       return userRepository.findOneByResetKey(key)
            .filter(user -> {
                ZonedDateTime oneDayAgo = ZonedDateTime.now().minusHours(24);
                return user.getResetDate().isAfter(oneDayAgo);
           })
           .map(user -> {
                user.setPassword(passwordEncoder.encode(newPassword));
                user.setResetKey(null);
                user.setResetDate(null);
                userRepository.save(user);
                return user;
           });
    }

    public Optional<RUser> requestPasswordReset(String mail) {
        return userRepository.findOneByEmail(mail)
            .filter(RUser::getActivated)
            .map(user -> {
                user.setResetKey(RandomUtil.generateResetKey());
                user.setResetDate(ZonedDateTime.now());
                userRepository.save(user);
                return user;
            });
    }

    public RUser createUserInformation(String login, String password, String firstName, String lastName, String email,
        String langKey) {

        RUser newUser = new RUser();
        Authority authority = authorityRepository.findOne(AuthoritiesConstants.USER);
        Set<Authority> authorities = new HashSet<>();
        String encryptedPassword = passwordEncoder.encode(password);
        newUser.setLogin(login);
        // new user gets initially a generated password
        newUser.setPassword(encryptedPassword);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setEmail(email);
        newUser.setLangKey(langKey);
        // new user is not active
        newUser.setActivated(false);
        // new user gets registration key
        newUser.setActivationKey(RandomUtil.generateActivationKey());
        authorities.add(authority);
        newUser.setRole(RoleType.ADMIN);
        userRepository.save(newUser);
        log.debug("Created Information for User: {}", newUser);
        return newUser;
    }

    public RUser createUser(ManagedUserDTO managedUserDTO) {
        RUser user = new RUser();
        user.setLogin(managedUserDTO.getLogin());
        user.setFirstName(managedUserDTO.getFirstName());
        user.setLastName(managedUserDTO.getLastName());
        user.setEmail(managedUserDTO.getEmail());
        if (managedUserDTO.getLangKey() == null) {
            user.setLangKey("en"); // default language
        } else {
            user.setLangKey(managedUserDTO.getLangKey());
        }
        user.setRole(RoleType.USER);
        String encryptedPassword = passwordEncoder.encode(RandomUtil.generatePassword());
        user.setPassword(encryptedPassword);
        user.setResetKey(RandomUtil.generateResetKey());
        user.setResetDate(ZonedDateTime.now());
        user.setActivated(true);
        userRepository.save(user);
        log.debug("Created Information for User: {}", user);
        return user;
    }

    public void updateUserInformation(String firstName, String lastName, String email, String langKey) {
        userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin()).ifPresent(u -> {
            u.setFirstName(firstName);
            u.setLastName(lastName);
            u.setEmail(email);
            u.setLangKey(langKey);
            userRepository.save(u);
            log.debug("Changed Information for User: {}", u);
        });
    }

    public void deleteUserInformation(String login) {
        userRepository.findOneByLogin(login).ifPresent(u -> {
            userRepository.delete(u);
            log.debug("Deleted User: {}", u);
        });
    }

    public void changePassword(String password) {
        userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin()).ifPresent(u -> {
            String encryptedPassword = passwordEncoder.encode(password);
            u.setPassword(encryptedPassword);
            userRepository.save(u);
            log.debug("Changed password for User: {}", u);
        });
    }

    @Transactional(readOnly = true)
    public Optional<RUser> getUserWithAuthoritiesByLogin(String login) {
        return userRepository.findOneByLogin(login).map(u -> {
            return u;
        });
    }

    @Transactional(readOnly = true)
    public RUser getUserWithAuthorities(Long id) {
        RUser user = userRepository.findOne(id);
        return user;
    }

    @Transactional(readOnly = true)
    public RUser getUserWithAuthorities() {
        RUser user = userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin()).get();
        return user;
    }
    
    @Transactional(readOnly = true)
    public User getUserDTOWithAuthorities() {
        RUser user = userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin()).get();
        return userMapper.toUser(user);
    }
    
    /**
     * Find a user by their login
     * @param login
     * @return
     */
    public User findUserByLogin(String login){
    	RestPreconditions.checkNotNull(login);
    	RUser ru = userRepository.findUserByLogin(login);
    	return userMapper.toUser(ru);
    }
    
}
