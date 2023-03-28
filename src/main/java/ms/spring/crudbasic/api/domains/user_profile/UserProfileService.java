package ms.spring.crudbasic.api.domains.user_profile;

import jakarta.validation.constraints.Null;
import ms.spring.crudbasic.api.domains.profiles.IProfileRepository;
import ms.spring.crudbasic.api.domains.user_profile.dtos.CreateUserProfileDto;
import ms.spring.crudbasic.api.domains.user_profile.dtos.UpdateUserProfileDto;
import ms.spring.crudbasic.api.domains.users.IUserRepository;
import ms.spring.crudbasic.api.infra.exceptions.RuleViolationException;
import ms.spring.crudbasic.api.infra.services.CrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserProfileService extends CrudService<IUserProfileRepository, UserProfileEntity, CreateUserProfileDto, UpdateUserProfileDto> {

    private IUserRepository userRepository;
    private IProfileRepository profileRepository;

    public UserProfileService(
            IUserProfileRepository userProfileRepository,
            IUserRepository userRepository,
            IProfileRepository profileRepository) {
        super(userProfileRepository);

        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
    }

    private boolean userAlreadyHasProfile(Long userId) {
        return repository.existsByUserId(userId);
    }

    private boolean userAlreadyHasProfile(Long userId, Long profileId) {
        return repository.existsByUserIdAndProfileId(userId, profileId);
    }

    private boolean isUserActive(Long id) {
        return userRepository.existsByIdAndActiveIsTrue(id);
    }

    private boolean isProfileActive(Long id) {
        return profileRepository.existsByIdAndActiveIsTrue(id);
    }

    private void validateActiveStatus(Long userId, Long profileId) {
        if (userId != null && !isUserActive(userId)) {
            throw RuleViolationException.emitMessage("Não é permitido o cadastro de usuário inativo ou inexistente.");
        }
        if (!isProfileActive(profileId)) {
            throw RuleViolationException.emitMessage("Não é permitido o cadastro de perfil inativo ou inexistente.");
        }
    }

    private void applyRuleValidationOnCreate(CreateUserProfileDto dto) {
        validateActiveStatus(dto.userId(), dto.profileId());
        if (userAlreadyHasProfile(dto.userId())) {
            throw RuleViolationException.emitMessage("Este usuário já possui um perfil cadastrado.");
        }
    }

    private void applyRuleValidationOnUpdate(Long profileId) {
        validateActiveStatus(null, profileId);
    }

    @Override
    public UserProfileEntity create(CreateUserProfileDto dto) {
        applyRuleValidationOnCreate(dto);
        var reference = UserProfileEntity.createNewUserProfile(dto);
        save(reference);
        return reference;
    }

    @Override
    public UserProfileEntity update(Long id, UpdateUserProfileDto dto) {
        var reference = getReference(id);
        applyRuleValidationOnUpdate(dto.profileId());
        reference.setUpdatedAt(new Date());
        reference.setProfileId(dto.profileId());
        save(reference);
        return reference;
    }

    @Override
    public UserProfileEntity showDetails(Long id) {
        return getReference(id);
    }

    @Override
    public Page search(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public boolean remove(Long id) {
        checkReferenceId(id);
        delete(id);
        return true;
    }
}
