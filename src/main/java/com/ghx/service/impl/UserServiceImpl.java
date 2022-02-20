package com.ghx.service.impl;

import com.ghx.dao.UserDao;
import com.ghx.entity.User;
import com.ghx.service.UserService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Data
@NoArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    @Qualifier("userDaoJDBCImpl")
    private UserDao userDaoJDBC;

    @Autowired
    @Qualifier("userDaoHibernateImpl")
    private UserDao userDaoHibernate;

    @Override
    public User validateLogin(String username, String password) {
        return userDaoJDBC.validateLogin(username, password);
    }

    @Override
    public User register(String name, String email, String username, String password) {
        return userDaoJDBC.register(name, email, username, password);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDaoHibernate.getAllUsers();
    }

    @Override
    public User findUser(String name) {
        return userDaoJDBC.findUser(name);
    }

    @Override
    @Transactional
    public User suspendUser(long id) {
        return userDaoHibernate.suspendUser(id);
    }

    @Override
    @Transactional
    public User activateUser(long id) {
        return userDaoHibernate.activateUser(id);
    }
}
