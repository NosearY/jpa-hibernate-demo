package io.github.noseary.hibernatedemo.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import lombok.Getter;
import org.hibernate.ScrollableResults;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@Getter
public abstract class AbstractGenericDao<T extends Serializable, PK> {

    private Class<T> clazz;

    private String entityName;

    @Autowired
    private EntityManager entityManager;

    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

    public void setEntityName(String entityName) { this.entityName = entityName;}

    public List<T> findAll() {
        return entityManager.createQuery("from " + clazz.getName()).getResultList();
    }

    public Page<T> findAllPaginated(Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        // count
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        countQuery.select(criteriaBuilder.count(countQuery.from(clazz)));
        Long count = entityManager.createQuery(countQuery).getSingleResult();

        // select
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
        Root<T> from = criteriaQuery.from(clazz);
        CriteriaQuery<T> select = criteriaQuery.select(from).orderBy();

        TypedQuery<T> typedQuery = entityManager.createQuery(select).setFirstResult((int)pageable.getOffset()).setMaxResults(pageable.getPageSize());
        return new PageImpl<>(typedQuery.getResultList(), pageable, count);
    }

    public T findOne(PK id) {
        return entityManager.find(clazz, id);
    }

    public void create(T entity) {
        entityManager.persist(entity);
    }

    public T update(T entity) {
        return entityManager.merge(entity);
    }

    public void delete(T entity) {
        entityManager.remove(entity);
    }

    public void deleteById(PK id) {
        entityManager.remove(id);
    }

    public T findByName(String fieldName, String fieldValue) {
        Query query = getEntityManager().createQuery("from " + getEntityName() + " where " + fieldName + " = :" + fieldName, getClazz());
        query.setParameter(fieldName, fieldValue);
        try {
            return (T) query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
