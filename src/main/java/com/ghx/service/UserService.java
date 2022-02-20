package com.ghx.service;

import com.ghx.entity.User;

import java.util.List;

public interface UserService {
    public User validateLogin(String username, String password);

    public User register(String name, String email, String username, String password);

    public List<User> getAllUsers();

    public User findUser(String name);

    public User suspendUser(long id);

    public User activateUser(long id);
}
