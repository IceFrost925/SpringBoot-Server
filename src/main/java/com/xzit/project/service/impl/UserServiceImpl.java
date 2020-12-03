package com.xzit.project.service.impl;

import com.xzit.project.domain.Authority;
import com.xzit.project.domain.User;
import com.xzit.project.repository.AuthorityRepository;
import com.xzit.project.repository.UserRepository;
import com.xzit.project.security.AuthoritiesConstants;
import com.xzit.project.service.UserService;
import com.xzit.project.utils.DateUtil;
import com.xzit.project.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> selectAll() {
        return userRepository.findAll();
    }

    @Override
    public ResultObj createUser(User user) {
        setAuthority(authorityRepository);
        if (userRepository.findUserByPhone(user.getPhone()).isPresent()) {
            return ResultObj.back(201, "用户已存在", null);
        }
        user.setId(String.valueOf(new Date().getTime()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreateTime(DateUtil.getZoneDateTime());
        user.setUpdateTime(DateUtil.getZoneDateTime());
        Set<Authority> authorities = new HashSet<>();
        authorityRepository.findById(AuthoritiesConstants.USER).ifPresent(authorities::add);
        user.setAuthorities(authorities);
        userRepository.save(user);
        return ResultObj.back(200, "添加成功", null);
    }

    @Override
    public List<User> findByType(String type, String content) {
        if (type.equals("phone")) {
            return userRepository.findUsersByPhoneLike("%" + content + "%");
        }
        return userRepository.findUsersByUsernameLike("%" + content + "%");
    }

    @Override
    public ResultObj updateUser(User user) {
        if (userRepository.findById(user.getId()).isPresent()) {
            userRepository.findById(user.getId()).ifPresent(user2 -> {
                if (!passwordEncoder.matches(user.getPassword(), user2.getPassword())) {
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
                }
            });
            user.setUpdateTime(DateUtil.getZoneDateTime());
            userRepository.save(user);
            return ResultObj.back(200, "修改成功", null);
        }
        return ResultObj.back(201, "修改失败", null);
    }

    @Override
    public ResultObj deleteUser(String id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            return ResultObj.back(200, "删除成功", null);
        }
        return ResultObj.back(201, "删除失败", null);
    }

    Boolean flag = false;

    @Override
    public ResultObj login(String login, String password) {
        userRepository.login(login).ifPresent(user -> {
            if (passwordEncoder.matches(password, user.getPassword())) {
                flag = true;
            }
        });
        if (flag) {
            return ResultObj.back(200, "登录成功", null);
        }
        return ResultObj.back(201, "登录失败", null);
    }

    private static void setAuthority(AuthorityRepository authorityRepository) {
        Authority auth = new Authority();
        List<Authority> authorities = authorityRepository.findAll();
        if(!authorities.contains(AuthoritiesConstants.USER)){
            auth.setName(AuthoritiesConstants.USER);
            authorityRepository.save(auth);
        }
        if(!authorities.contains(AuthoritiesConstants.ADMIN)){
            auth.setName(AuthoritiesConstants.ADMIN);
            authorityRepository.save(auth);
        }
    }
}
