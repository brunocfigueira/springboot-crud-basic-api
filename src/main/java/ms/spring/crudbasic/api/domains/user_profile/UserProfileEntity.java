package ms.spring.crudbasic.api.domains.user_profile;

import jakarta.persistence.*;
import lombok.*;
import ms.spring.crudbasic.api.domains.user_profile.dtos.CreateUserProfileDto;

import java.util.Date;

@Table(name = "user_profile")
@Entity(name = "UserProfile")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class UserProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long profileId;
    private Date createdAt;
    private Date updatedAt;

    public UserProfileEntity(CreateUserProfileDto dto) {
        createdAt = new Date();
        userId = dto.userId();
        profileId = dto.profileId();
    }

    public static UserProfileEntity createNewUserProfile(CreateUserProfileDto dto) {
        return new UserProfileEntity(dto);
    }
}
