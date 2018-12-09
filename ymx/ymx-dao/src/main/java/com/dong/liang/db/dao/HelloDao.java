package com.dong.liang.db.dao;

import com.dong.liang.db.entity.THello;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HelloDao  {

    THello selectHello();

}
