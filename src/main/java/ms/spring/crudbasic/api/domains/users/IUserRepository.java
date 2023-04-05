package ms.spring.crudbasic.api.domains.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository("IUserRepository")
public interface IUserRepository extends JpaRepository<UserEntity, Long> {
    UserDetails findByLoginAndActiveIsTrue(String login);

    UserDetails findByLogin(String login);

    @Query(nativeQuery = true)
    DetailsUserToken findDetailsTokenByUserId(Long userId);

}
