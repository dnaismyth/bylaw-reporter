package flow.security;

import flow.domain.Authority;
import flow.domain.RUser;
import flow.dto.RoleType;
import flow.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(UserDetailsService.class);

    @Inject
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {
        log.debug("Authenticating {}", login);
        String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
        Optional<RUser> userFromDatabase = userRepository.findOneByLogin(lowercaseLogin);
        return userFromDatabase.map(user -> {
            if (!user.getActivated()) {
                throw new UserNotActivatedException("User " + lowercaseLogin + " was not activated");
            }
            
            Collection<? extends GrantedAuthority> authorityList = mapGrantedAuthorities(user);
            return new org.springframework.security.core.userdetails.User(lowercaseLogin,
                user.getPassword(),
                authorityList);
        }).orElseThrow(() -> new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the " +
        "database"));
    }
    
    /**
     * Map RoleType to user authorities
     * @param ruser
     * @return
     */
    private Collection<? extends GrantedAuthority> mapGrantedAuthorities(RUser ruser){
    	Set<SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();
    	RoleType role = ruser.getRole();
    	if(role == RoleType.ADMIN){
    		SimpleGrantedAuthority admin = new SimpleGrantedAuthority(AuthoritiesConstants.ADMIN);
    		authorities.add(admin);
    	}
    	
    	if(role == RoleType.USER){
    		SimpleGrantedAuthority user = new SimpleGrantedAuthority(AuthoritiesConstants.USER);
    		authorities.add(user);
    	}
    	
    	if(role == RoleType.GUEST){
    		SimpleGrantedAuthority guest = new SimpleGrantedAuthority(AuthoritiesConstants.GUEST);
    		authorities.add(guest);
    	}
    	
    	return authorities;
    	
    }
}
