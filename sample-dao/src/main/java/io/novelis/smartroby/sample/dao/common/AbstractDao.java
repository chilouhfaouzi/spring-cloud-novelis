package io.novelis.smartroby.sample.dao.common;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.novelis.smartroby.sample.domain.common.entities.AbstractEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Repository
public abstract class AbstractDao<T extends AbstractEntity, Q extends JpaRepository<T, Long>> {
    @Autowired
    @Qualifier(value = "entityManagerFactory")
    private EntityManager entityManager;

    public abstract Q getJpaRepository();

    public T create(T entity) {
        return getJpaRepository().save(entity);
    }

    public T update(T entity) {
        return entityManager.merge(entity);
    }

    public void delete(T entity) {
        getJpaRepository().delete(entity);
    }

    public void deleteById(Long id) {
        getJpaRepository().deleteById(id);
    }

    public boolean existsById(Long id) {
        return getJpaRepository().existsById(id);
    }

    public Optional<T> findById(Long id) {
        return getJpaRepository().findById(id);
    }

    public Optional<T> lazyFindById(Long id) {
        T entity = existsById(id) ? getJpaRepository().getOne(id) : null;
        return Optional.ofNullable(entity);
    }

    protected JPAQueryFactory getJPAQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }
}
