package com.dong.liang.web.controller;

import com.dong.liang.IUserService;
import com.dong.liang.db.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2018-12-09
 */
@RestController
@RequestMapping("/sys/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/find")
    public List<User> findUserMethod(){
        List<User> userList = userService.findUserInfo();
        return userList;

    }

}
