package ms.spring.crudbasic.api.domains.repositories;

import ms.spring.crudbasic.api.domains.entities.ProfilePermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProfilePermissionRepository extends JpaRepository<ProfilePermissionEntity, Long> {
    void deleteAllByProfileId(Long profileId);

    List<ProfilePermissionEntity> findAllByProfileId(Long profileId);

    boolean existsByProfileId(Long profileId);
}
