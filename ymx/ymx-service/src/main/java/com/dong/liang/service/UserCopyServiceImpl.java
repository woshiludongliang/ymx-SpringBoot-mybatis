package com.dong.liang.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dong.liang.IUserCopyService;
import com.dong.liang.db.entity.UserCopy;
import com.dong.liang.db.dao.UserCopyMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2018-12-09
 */
@Service
public class UserCopyServiceImpl extends ServiceImpl<UserCopyMapper, UserCopy> implements IUserCopyService {

}
