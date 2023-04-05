package ms.spring.crudbasic.api.domains.users;

import ms.spring.crudbasic.api.domains.profiles.ProfileService;
import ms.spring.crudbasic.api.domains.users.dtos.CreateUserDto;
import ms.spring.crudbasic.api.domains.users.dtos.PasswordChangeDto;
import ms.spring.crudbasic.api.domains.users.dtos.UpdateUserDto;
import ms.spring.crudbasic.api.infrastructure.exceptions.RuleViolationException;
import ms.spring.crudbasic.api.infrastructure.responses.ResponseErrors;
import ms.spring.crudbasic.api.infrastructure.services.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class UserService extends CrudService<IUserRepository, UserEntity, CreateUserDto, UpdateUserDto> {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ProfileService profileService;

    public UserService(IUserRepository repository) {
        super(repository);
    }

    private String encryptPassword(String value) {
        return passwordEncoder.encode(value);
    }

    private void applyRuleValidation(Long profileId) {
        if (!profileService.isProfileActive(profileId)) {
            throw RuleViolationException.emitMessage(ResponseErrors.violationUserProfileId);
        }
    }

    private void applyRuleValidationDelete(Long userId) {
        checkRootUserId(userId);
        checkExistsReferenceId(userId);
    }

    @Override
    public UserEntity create(CreateUserDto dto) {
        applyRuleValidation(dto.profileId());
        var reference = UserEntity.createNewUser(dto);
        reference.setPassword(encryptPassword(reference.getPassword()));
        save(reference);
        return reference;
    }

    @Override
    public UserEntity update(Long id, UpdateUserDto dto) {
        applyRuleValidation(dto.profileId());
        var reference = getReference(id);
        reference.setActive(dto.active());
        reference.setUpdatedAt(new Date());
        reference.setProfileId(dto.profileId());
        reference.setUsername(dto.username());
        reference.setLogin(dto.login());
        reference.setEmail(dto.email());
        save(reference);
        return reference;
    }

    @Override
    public UserEntity showDetails(Long id) {
        return getReference(id);
    }

    @Transactional
    public void changePassword(Long id, PasswordChangeDto dto) {
        var reference = getReference(id);
        reference.setPassword(encryptPassword(dto.newPassword()));
        reference.setUpdatedAt(new Date());
        save(reference);
    }

    @Override
    public Page search(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public boolean remove(Long id) {
        applyRuleValidationDelete(id);
        delete(id);
        return true;
    }
}
