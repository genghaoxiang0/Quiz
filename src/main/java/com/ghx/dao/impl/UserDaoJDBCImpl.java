package com.ghx.dao.impl;

import com.ghx.dao.UserDao;
import com.ghx.entity.User;
import com.ghx.rowMapper.UserRowMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Repository
public class UserDaoJDBCImpl implements UserDao {
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private static final List<User> users = new ArrayList<>();

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User validateLogin(String username, String password) {
        String sql = "select * from user where username = ? and password = ?";
        List<User> users = jdbcTemplate.query(sql, new Object[] {username, password}, new UserRowMapper());
        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }

    @Override
    public User register(String name, String email, String username, String password) {
        String sql = "select count(*) from user where username = ?";
        int number = jdbcTemplate.queryForObject(sql, new Object[] {username}, Integer.class);
        if (number > 0) {
            return null;
        }
        sql = "INSERT INTO USER (username, PASSWORD, NAME, email) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, username, password, name, email);
        sql = "select * from user where username = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] {username}, new UserRowMapper());
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public User findUser(String name) {
        String sql = "select * from user where name = ?";
        List<User> users = jdbcTemplate.query(sql, new UserRowMapper(), name);
        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }

    @Override
    public User suspendUser(long id) {
        return null;
    }

    @Override
    public User activateUser(long id) {
        return null;
    }
}
