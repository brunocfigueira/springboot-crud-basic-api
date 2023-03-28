package ms.spring.crudbasic.api.domains.users;

import jakarta.persistence.*;
import lombok.*;
import ms.spring.crudbasic.api.domains.users.dtos.*;

import java.util.Date;


@Table(name = "users")
@Entity(name = "User")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String login;
    private String password;
    private String email;
    private Boolean active;
    private Date createdAt;
    private Date updatedAt;

    private UserEntity(CreateUserDto dto) {
        active = true;
        createdAt = new Date();
        username = dto.username();
        login = dto.login();
        password = dto.password();
        email = dto.email();
    }

    private UserEntity(UpdateUserDto dto) {
        active = dto.active();
        updatedAt = new Date();
        username = dto.username();
        login = dto.login();
        email = dto.email();
    }

    public static UserEntity createNewUser(CreateUserDto dto) {
        return new UserEntity(dto);
    }
}
