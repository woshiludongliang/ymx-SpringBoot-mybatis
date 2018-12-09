package com.dong.liang.service;

import com.dong.liang.HelloService;
import com.dong.liang.db.dao.HelloDao;
import com.dong.liang.db.entity.THello;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class HelloServiceImpl implements HelloService {

    @Autowired
    private HelloDao helloDao;

    @Override
    public String helloMethod() {

        THello tHello = helloDao.selectHello();

        return tHello.getId() +"";
    }
}
