package com.ghx.dao;

import com.ghx.entity.User;

import java.util.List;

public interface UserDao {
    public User validateLogin(String username, String password);

    public User register(String name, String email, String username, String password);

    public List<User> getAllUsers();

    public User findUser(String name);

    public User suspendUser(long id);

    public User activateUser(long id);
}
