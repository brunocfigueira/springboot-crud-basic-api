package ms.spring.crudbasic.api.infrastructure.services;

import ms.spring.crudbasic.api.infrastructure.exceptions.ForbiddenAccessException;
import ms.spring.crudbasic.api.infrastructure.exceptions.RuleViolationException;
import ms.spring.crudbasic.api.infrastructure.responses.ResponseErrors;
import ms.spring.crudbasic.api.infrastructure.responses.ResponseForbidden;
import ms.spring.crudbasic.api.infrastructure.validations.ERoot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public abstract class CrudService<
        R extends JpaRepository, // @Repository
        E,                       // @Entity
        C extends Record,        // CreateDto
        U extends Record         // UpdateDto
        > {

    final protected R repository;

    public CrudService(R repository) {
        this.repository = repository;
    }

    protected boolean isRooProfile(Long id) {
        return ERoot.PROFILE_ID.getValue() == id;
    }

    protected boolean isRooUser(Long id) {
        return ERoot.USER_ID.getValue() == id;
    }

    protected boolean isRooPermission(Long id) {
        return ERoot.PERMISSION_ID.getValue() == id;
    }

    final public void checkRootProfileId(Long id) {
        if (isRooProfile(id)) {
            throw ForbiddenAccessException.emitMessage(ResponseForbidden.rootProfileChange);
        }
    }

    final public void checkRootUserId(Long id) {
        if (isRooUser(id)) {
            throw ForbiddenAccessException.emitMessage(ResponseForbidden.rootUserChange);
        }
    }

    final public void checkRootPermissionId(Long id) {
        if (isRooPermission(id)) {
            throw ForbiddenAccessException.emitMessage(ResponseForbidden.rootPermissionChange);
        }
    }

    final public void checkRootPermissionByProfileId(Long id) {
        if (isRooProfile(id)) {
            throw ForbiddenAccessException.emitMessage(ResponseForbidden.rootPermissionChange);
        }
    }

    final protected void save(E entity) {
        repository.save(entity);
    }

    final protected void delete(Long id) {
        repository.deleteById(id);
    }

    final protected void checkExistsReferenceId(Long id) {
        if (!exists(id)) {
            throw RuleViolationException.emitMessage(ResponseErrors.violationParamRequest);
        }
    }

    final protected boolean exists(Long id) {
        return repository.existsById(id);
    }

    final protected E getReference(Long id) {
        return (E) repository.getReferenceById(id);
    }


    @Transactional
    public abstract E create(C dto);

    @Transactional
    public abstract E update(Long id, U dto);

    public abstract E showDetails(Long id);

    public abstract Page search(Pageable pageable);

    @Transactional
    public abstract boolean remove(Long id);
}
