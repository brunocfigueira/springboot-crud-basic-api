package ms.spring.crudbasic.api.domains.user_profile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserProfileRepository extends JpaRepository<UserProfileEntity, Long> {
    boolean existsByUserId(Long id);
    boolean existsByUserIdAndProfileId(Long userId, Long profileId);
}
