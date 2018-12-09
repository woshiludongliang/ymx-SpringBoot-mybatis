package com.dong.liang.db.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dong.liang.db.entity.User;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2018-12-09
 */
public interface UserMapper extends BaseMapper<User> {

    void selectAllInfo();

}
