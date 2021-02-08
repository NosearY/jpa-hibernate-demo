package io.github.noseary.hibernatedemo.dao;

import java.io.Serializable;
import javax.persistence.Query;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class GenericDao<T extends Serializable, PK> extends AbstractGenericDao<T, PK> implements IGenericDao<T, PK> {

}
