package io.github.noseary.hibernatedemo.configuration;

import io.github.noseary.hibernatedemo.dao.GenericDao;
import io.github.noseary.hibernatedemo.dao.IGenericDao;
import io.github.noseary.hibernatedemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoConfig {

    @Bean
    public IGenericDao<User, Long> userDao() {
        GenericDao<User, Long> userDao = new GenericDao<>();
        userDao.setClazz(User.class);
        userDao.setEntityName("User");
        return userDao;
    }

}
