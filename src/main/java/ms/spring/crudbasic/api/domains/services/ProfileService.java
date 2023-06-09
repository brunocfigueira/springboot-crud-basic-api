package ms.spring.crudbasic.api.domains.services;

import ms.spring.crudbasic.api.domains.entities.ProfileEntity;
import ms.spring.crudbasic.api.domains.repositories.IProfileRepository;
import ms.spring.crudbasic.api.domains.dtos.profiles.CreateProfileDto;
import ms.spring.crudbasic.api.domains.dtos.profiles.DetailsProfileDto;
import ms.spring.crudbasic.api.domains.dtos.profiles.UpdateProfileDto;
import ms.spring.crudbasic.api.infrastructure.services.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ProfileService extends CrudService<IProfileRepository, ProfileEntity, CreateProfileDto, UpdateProfileDto> {
    @Autowired
    private IProfileRepository repository;

    public ProfileService(IProfileRepository repository) {
        super(repository);
    }

    public boolean isProfileActive(Long id) {
        return repository.existsByIdAndActiveIsTrue(id);
    }

    private void applyRuleValidationDelete(Long profileId) {
        checkRootProfileId(profileId);
        checkExistsReferenceId(profileId);
    }

    @Override
    public ProfileEntity create(CreateProfileDto dto) {
        var reference = ProfileEntity.createNewProfile(dto);
        save(reference);
        return reference;
    }

    @Override
    public ProfileEntity update(Long id, UpdateProfileDto dto) {
        checkRootProfileId(id);
        var reference = getReference(id);
        reference.setUpdatedAt(new Date());
        reference.setName(dto.name());
        reference.setDescription(dto.description());
        reference.setAcronym(dto.acronym().toUpperCase());
        save(reference);
        return reference;
    }

    @Override
    public ProfileEntity showDetails(Long id) {
        return getReference(id);
    }

    @Override
    public Page<DetailsProfileDto> search(Pageable pageable) {
        return repository.findAll(pageable).map(entity -> new DetailsProfileDto(entity));
    }

    @Override
    public boolean remove(Long id) {
        applyRuleValidationDelete(id);
        delete(id);
        return true;
    }
}
