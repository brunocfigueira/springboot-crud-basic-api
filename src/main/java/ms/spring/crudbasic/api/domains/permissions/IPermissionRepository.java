package ms.spring.crudbasic.api.domains.permissions;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IPermissionRepository extends JpaRepository<PermissionEntity, Long> {
}
