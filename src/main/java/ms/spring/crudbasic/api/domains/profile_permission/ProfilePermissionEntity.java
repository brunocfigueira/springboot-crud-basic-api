package ms.spring.crudbasic.api.domains.profile_permission;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "profile_permission")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ProfilePermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long profileId;

    private Long permissionId;

    public ProfilePermissionEntity(Long profileId, Long permissionId) {
        this.profileId = profileId;
        this.permissionId = permissionId;
    }

    public static ProfilePermissionEntity createNewProfilePermission(Long profileId, Long permissionId) {
        return new ProfilePermissionEntity(profileId, permissionId);
    }
}
