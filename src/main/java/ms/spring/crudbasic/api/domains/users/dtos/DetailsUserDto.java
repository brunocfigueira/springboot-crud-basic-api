package ms.spring.crudbasic.api.domains.users.dtos;

import ms.spring.crudbasic.api.domains.users.UserEntity;

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
