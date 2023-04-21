package ms.spring.crudbasic.api.domains.entities;

import jakarta.persistence.*;
import lombok.*;
import ms.spring.crudbasic.api.domains.dtos.profiles.CreateProfileDto;

import java.util.Date;

@Table(name = "profiles")
@Entity
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
    private String acronym;
    private Boolean active;
    private Date createdAt;
    private Date updatedAt;

    public ProfileEntity(CreateProfileDto dto) {
        active = true;
        createdAt = new Date();
        name = dto.name();
        description = dto.description();
        acronym = dto.acronym().toUpperCase();
    }

    public static ProfileEntity createNewProfile(CreateProfileDto dto) {
        return new ProfileEntity(dto);
    }
}
