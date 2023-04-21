package ms.spring.crudbasic.api.domains.entities;

import jakarta.persistence.*;
import lombok.*;
import ms.spring.crudbasic.api.domains.dtos.permissions.CreatePermissionDto;

import java.util.Date;

@Table(name = "permissions")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class PermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Date createdAt;
    private Date updatedAt;


    public PermissionEntity(CreatePermissionDto dto) {
        createdAt = new Date();
        name = dto.name().toUpperCase();
        description = dto.description();
    }

    public static PermissionEntity createNewPermission(CreatePermissionDto dto) {
        return new PermissionEntity(dto);
    }

}
