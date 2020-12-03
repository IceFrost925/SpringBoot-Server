package com.xzit.project.service;


import com.xzit.project.domain.User;
import com.xzit.project.utils.ResultObj;

import java.util.List;

public interface UserService {
    List<User> selectAll();

    ResultObj createUser(User user);

    List<User> findByType(String type, String content);

    ResultObj updateUser(User user);

    ResultObj deleteUser(String id);

    ResultObj login(String login, String password);
}
