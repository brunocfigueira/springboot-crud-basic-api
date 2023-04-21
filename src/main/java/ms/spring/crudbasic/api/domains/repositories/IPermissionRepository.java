package ms.spring.crudbasic.api.domains.repositories;

import ms.spring.crudbasic.api.domains.entities.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPermissionRepository extends JpaRepository<PermissionEntity, Long> {
}
