package com.ghx.dao.impl;

import com.ghx.dao.AbstractHibernateDAO;
import com.ghx.dao.UserDao;
import com.ghx.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoHibernateImpl extends AbstractHibernateDAO<User> implements UserDao {

    public UserDaoHibernateImpl() {
        setClazz(User.class);
    }

    @Override
    public User validateLogin(String username, String password) {
        return null;
    }

    @Override
    public User register(String name, String email, String username, String password) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return findAll();
    }

    @Override
    public User findUser(String name) {
        return null;
    }

    @Override
    public User suspendUser(long id) {
        User user = sessionFactory.getCurrentSession().get(User.class, id);
        user.setActive(false);
        return user;
    }

    @Override
    public User activateUser(long id) {
        User user = sessionFactory.getCurrentSession().get(User.class, id);
        user.setActive(true);
        return user;
    }

}
