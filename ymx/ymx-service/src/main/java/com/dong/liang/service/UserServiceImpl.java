package com.dong.liang.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dong.liang.IUserService;
import com.dong.liang.db.entity.User;
import com.dong.liang.db.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2018-12-09
 */
@Service
public class UserServiceImpl  implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findUserInfo() {

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        Page<User> page = new Page<>(1, 10);
        page.setOptimizeCountSql(true);
        IPage<User> userIPage = userMapper.selectPage(page, queryWrapper);
        System.out.println(userIPage);
        System.out.println(123);
        return null;
    }
}
