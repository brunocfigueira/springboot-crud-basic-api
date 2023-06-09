package ms.spring.crudbasic.api.domains.services;

import ms.spring.crudbasic.api.domains.entities.ProfilePermissionEntity;
import ms.spring.crudbasic.api.domains.repositories.IProfilePermissionRepository;
import ms.spring.crudbasic.api.domains.dtos.profile_permission.CreateProfilePermissionsDto;
import ms.spring.crudbasic.api.domains.dtos.profile_permission.DetailsProfilePermissionsDto;
import ms.spring.crudbasic.api.domains.dtos.profile_permission.UpdateProfilePermissionsDto;
import ms.spring.crudbasic.api.infrastructure.exceptions.RuleViolationException;
import ms.spring.crudbasic.api.infrastructure.responses.ResponseErrors;
import ms.spring.crudbasic.api.infrastructure.services.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfilePermissionService extends CrudService<IProfilePermissionRepository, ProfilePermissionEntity, CreateProfilePermissionsDto, UpdateProfilePermissionsDto> {
    @Autowired
    private ProfileService profileService;
    @Autowired
    private PermissionService permissionService;

    public ProfilePermissionService(IProfilePermissionRepository repository) {
        super(repository);
    }

    private void applyRuleValidationProfile(Long profileId) {
        if (isRooProfile(profileId) || !profileService.isProfileActive(profileId)) {
            throw RuleViolationException.emitMessage(ResponseErrors.violationProfileIdPermission);
        }
    }

    private void applyRuleValidation(Long profileId, List<Long> permissionIds) {
        applyRuleValidationProfile(profileId);

        permissionIds.forEach(permissionId -> {

            if (isRooPermission(permissionId) || !permissionService.existsPermission(permissionId)) {
                throw RuleViolationException.emitMessage(ResponseErrors.violationPermissionId);
            }
        });
    }

    private void applyRuleValidationCreate(CreateProfilePermissionsDto dto) {
        applyRuleValidation(dto.profileId(), dto.permissionIds());

        if (existsPermissionsByProfileId(dto.profileId())) {
            throw RuleViolationException.emitMessage(ResponseErrors.hasProfilePermissions);
        }

    }

    private void applyRuleValidationUpdate(Long profileId, UpdateProfilePermissionsDto dto) {
        applyRuleValidation(profileId, dto.permissionIds());
    }

    private boolean existsPermissionsByProfileId(Long profileId) {
        return repository.existsByProfileId(profileId);
    }

    private void applyRuleValidationDelete(Long profileId) {
        applyRuleValidationProfile(profileId);
        checkRootPermissionByProfileId(profileId);
    }

    private void saveProfilePermissions(Long profileId, List<Long> permissionIds) {
        var references = new ArrayList<ProfilePermissionEntity>();
        permissionIds.forEach(permissionId -> {
            references.add(ProfilePermissionEntity.createNewProfilePermission(profileId, permissionId));
        });
        repository.saveAll(references);
    }

    private void deletePermissionByProfileId(Long profileId) {
        repository.deleteAllByProfileId(profileId);
    }

    @Override
    public ProfilePermissionEntity create(CreateProfilePermissionsDto dto) {
        applyRuleValidationCreate(dto);
        saveProfilePermissions(dto.profileId(), dto.permissionIds());
        return null;
    }


    @Override
    public ProfilePermissionEntity update(Long profileId, UpdateProfilePermissionsDto dto) {
        applyRuleValidationUpdate(profileId, dto);
        deletePermissionByProfileId(profileId);
        saveProfilePermissions(profileId, dto.permissionIds());
        return null;
    }

    @Override
    public ProfilePermissionEntity showDetails(Long profileId) {
        return null;
    }

    public List<ProfilePermissionEntity> showDetailsProfilePermission(Long profileId) {
        return repository.findAllByProfileId(profileId);
    }

    @Override
    public Page<DetailsProfilePermissionsDto> search(Pageable pageable) {
        return repository.findAll(pageable).map(entity -> new DetailsProfilePermissionsDto(entity));
    }

    @Override
    public boolean remove(Long profileId) {
        applyRuleValidationDelete(profileId);
        deletePermissionByProfileId(profileId);
        return true;
    }
}
