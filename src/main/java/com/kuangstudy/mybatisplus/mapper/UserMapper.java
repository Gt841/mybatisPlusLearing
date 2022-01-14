package com.kuangstudy.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kuangstudy.mybatisplus.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

//在对应的mapper上面继承基本的接口 BaseMapper
@Mapper //表明是个mapper
//@Repository //代表持久层的
public interface UserMapper extends BaseMapper<User> {
    //所有的crud已经编写完成了
    //不需要像往常一样
}
