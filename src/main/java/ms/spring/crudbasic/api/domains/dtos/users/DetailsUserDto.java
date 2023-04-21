package ms.spring.crudbasic.api.domains.dtos.users;

import ms.spring.crudbasic.api.domains.entities.UserEntity;

import java.util.Date;

public record DetailsUserDto(
        Long id,
        String username,
        String login,
        String email,
        Boolean active,
        Date createdAt,
        Date updatedAt
) {
    public DetailsUserDto(UserEntity entity) {
        this(
                entity.getId(),
                entity.getUsername(),
                entity.getLogin(),
                entity.getEmail(),
                entity.getActive(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
