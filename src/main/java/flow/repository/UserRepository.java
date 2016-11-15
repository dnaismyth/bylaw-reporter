package flow.repository;

import flow.domain.RUser;

import java.time.ZonedDateTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the User entity.
 */
public interface UserRepository extends JpaRepository<RUser, Long> {

    Optional<RUser> findOneByActivationKey(String activationKey);

    List<RUser> findAllByActivatedIsFalseAndCreatedDateBefore(ZonedDateTime dateTime);

    Optional<RUser> findOneByResetKey(String resetKey);

    Optional<RUser> findOneByEmail(String email);

    Optional<RUser> findOneByLogin(String login);

    Optional<RUser> findOneById(Long userId);
    
    @Query("SELECT u FROM RUser u WHERE LOWER(u.username) = LOWER(:username)")
    public RUser findUserByLogin(String login);

    @Override
    void delete(RUser t);

}
