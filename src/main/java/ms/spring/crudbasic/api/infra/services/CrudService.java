package ms.spring.crudbasic.api.infra.services;

import ms.spring.crudbasic.api.infra.exceptions.RuleViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public abstract class CrudService<
        R extends JpaRepository,
        E,
        C extends Record,
        U extends Record
        > {

    protected R repository;

    public CrudService(R repository) {
        this.repository = repository;
    }

    protected void save(E entity) {
        repository.save(entity);
    }

    protected void delete(Long id) {
        repository.deleteById(id);
    }

    protected void checkReferenceId(Long id) {
        if (!exists(id)) {
            throw RuleViolationException.emitMessage("Violação de parâmetro na requeisição");
        }
    }

    protected boolean exists(Long id) {
        return repository.existsById(id);
    }

    protected E getReference(Long id) {
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
