package io.github.noseary.hibernatedemo.dao;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IGenericDao<T extends Serializable, PK> {

    void setClazz(Class< T > clazzToSet);

    void setEntityName(String entityName);

    T findOne(PK id);

    List<T> findAll();

    Page<T> findAllPaginated(Pageable pageable);

    void create(T entity);

    T update(T entity);

    void delete(T entity);

    void deleteById(PK id);

    T findByName(String fieldName, String fieldValue);

}
