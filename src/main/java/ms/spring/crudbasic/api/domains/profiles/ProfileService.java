package ms.spring.crudbasic.api.domains.profiles;

import ms.spring.crudbasic.api.domains.profiles.dtos.DetailProfileDto;
import ms.spring.crudbasic.api.domains.profiles.dtos.NewProfileDto;
import ms.spring.crudbasic.api.domains.profiles.dtos.UpdateProfileDto;
import ms.spring.crudbasic.api.infra.exceptions.RuleViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class ProfileService {
    @Autowired
    private IProfileRepository repository;

    private void saveOrUpdate(ProfileEntity profile) {
        repository.save(profile);
    }

    private void delete(Long id) {
        repository.deleteById(id);
    }

    private void checkReferenceId(Long id) {
        if (!exists(id)) {
            throw RuleViolationException.emitMessage("Violação de parâmetro na requeisição");
        }
    }

    @Transactional
    public ProfileEntity create(NewProfileDto dto) {
        var reference = ProfileEntity.createNewProfile(dto);
        saveOrUpdate(reference);
        return reference;
    }

    @Transactional
    public ProfileEntity update(Long id, UpdateProfileDto dto) {
        var reference = getReference(id);
        reference.setActive(dto.active());
        reference.setUpdatedAt(new Date());
        reference.setName(dto.name());
        reference.setDescription(dto.description());
        saveOrUpdate(reference);
        return reference;
    }

    @Transactional
    public boolean remove(Long id) {
        checkReferenceId(id);
        delete(id);
        return true;
    }

    public ProfileEntity getReference(Long id) {
        return repository.getReferenceById(id);
    }

    public ProfileEntity showDetails(Long id) {
        return getReference(id);
    }

    public Page search(Pageable pageable) {
        return repository.findAll(pageable).map(DetailProfileDto::new);
    }

    public boolean exists(Long id) {
        return repository.existsById(id);
    }
}
