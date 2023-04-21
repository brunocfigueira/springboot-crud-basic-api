package ms.spring.crudbasic.api.domains.entities;

import jakarta.persistence.*;
import lombok.*;
import ms.spring.crudbasic.api.domains.dtos.users.DetailsUserToken;
import ms.spring.crudbasic.api.domains.dtos.users.CreateUserDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;


@Table(name = "users")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@NamedNativeQuery(name = "UserEntity.findDetailsTokenByUserId",
        query = """
                    SELECT
                        u.id AS userId,
                        p.acronym AS profileAcronym,
                        array_to_string(array_agg(pm.id), ',') AS permissionIds,
                        array_to_string(array_agg(pm."name"), ',') AS permissionNames
                    FROM users u
                    JOIN profiles p ON p.id = u.profile_id
                    JOIN profile_permission pp ON pp.profile_id = p.id
                    JOIN permissions pm ON pm.id = pp.permission_id
                    WHERE u.id = :userId
                    GROUP BY u.id, p.acronym
                """,
        resultSetMapping = "Mapping.DetailsUserToken")
@SqlResultSetMapping(name = "Mapping.DetailsUserToken",
        classes = @ConstructorResult(targetClass = DetailsUserToken.class,
                columns = {
                        @ColumnResult(name = "userId", type = Long.class),
                        @ColumnResult(name = "profileAcronym", type = String.class),
                        @ColumnResult(name = "permissionIds", type = String.class),
                        @ColumnResult(name = "permissionNames", type = String.class)
                }
        )
)
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long profileId;
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
        profileId = dto.profileId();
        username = dto.username();
        login = dto.login();
        password = dto.password();
        email = dto.email();
    }

    public static UserEntity createNewUser(CreateUserDto dto) {
        return new UserEntity(dto);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
