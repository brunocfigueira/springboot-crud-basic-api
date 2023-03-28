package ms.spring.crudbasic.api.domains.profiles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProfileRepository extends JpaRepository<ProfileEntity, Long> {
    boolean existsByIdAndActiveIsTrue(Long id);
}
