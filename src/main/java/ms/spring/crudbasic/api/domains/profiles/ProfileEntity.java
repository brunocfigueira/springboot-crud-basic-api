package ms.spring.crudbasic.api.domains.profiles;

import jakarta.persistence.*;
import lombok.*;
import ms.spring.crudbasic.api.domains.profiles.dtos.NewProfileDto;

import java.util.Date;

@Table(name = "profiles")
@Entity(name = "Profile")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Boolean active;
    private Date createdAt;
    private Date updatedAt;

    public ProfileEntity(NewProfileDto dto) {
        active = true;
        createdAt = new Date();
        name = dto.name();
        description = dto.description();

    }

    public static ProfileEntity createNewProfile(NewProfileDto dto) {
        return new ProfileEntity(dto);
    }
}
