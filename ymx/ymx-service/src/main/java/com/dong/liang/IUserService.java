package com.dong.liang;

import com.dong.liang.db.entity.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2018-12-09
 */
public interface IUserService  {

    List<User> findUserInfo();

}
