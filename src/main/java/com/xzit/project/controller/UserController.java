package com.xzit.project.controller;

import com.xzit.project.domain.User;
import com.xzit.project.service.UserService;
import com.xzit.project.utils.ResultObj;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "用户管理")
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user/all")
    private ResultObj selectAll(){
        return ResultObj.back(200,userService.selectAll()) ;
    }

    @PostMapping("/user/create")
    private ResultObj createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @GetMapping("/user/select/type")
    private ResultObj findByType(@RequestParam(value = "type")String type,
                                 @RequestParam(value = "content")String content){
        return ResultObj.back(200,userService.findByType(type,content));
    }

    @PostMapping("/user/update")
    private ResultObj updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @DeleteMapping("/user/delete")
    private ResultObj deleteUser(@RequestParam(value = "id") String id){
        return userService.deleteUser(id);
    }

    @PostMapping("/user/login")
    private ResultObj login(@RequestParam(value = "login")String login,
                            @RequestParam(value = "password")String password){
        return userService.login(login,password);
    }
}
