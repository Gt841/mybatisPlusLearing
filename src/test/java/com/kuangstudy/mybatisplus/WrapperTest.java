package com.kuangstudy.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kuangstudy.mybatisplus.config.SpringConfig;
import com.kuangstudy.mybatisplus.mapper.UserMapper;
import com.kuangstudy.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class WrapperTest {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SpringConfig springConfig;

    @Test
    void contextLoads(){
        //查询name不为空,邮箱不为空,年龄大于等于12的用户
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .isNotNull("name")
                .isNotNull("email")
                .ge("age",12);
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }
    @Test
    void select1(){
        //名字为线程2的
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name","线程2");
        //查询一个数据,出现多个数据用list或map
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
    }
    @Test
    void select2(){
        //在20-30之间的用户
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.between("age",20,30); //区间
        System.out.println(userMapper.selectCount(wrapper));
    }

    //模糊查询
    @Test
    void select3(){
        //名字不包含e的
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper
                .notLike(true,"name","e")   //包含e的
                .likeRight("email","t"); //以t开头的
        List<Map<String, Object>> maps = userMapper.selectMaps(wrapper);
        maps.forEach(System.out::println);
    }
    //模糊查询
    @Test
    void select4(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //id做子查询
        wrapper
                .inSql("id","select id from user where id < 3");
        List<Object> objects = userMapper.selectObjs(wrapper);
        objects.forEach(System.out::println);
    }
    @Test
    void select6(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //查询排序
        wrapper
                .inSql("id","select id from user where id < 3")
                .orderByDesc("id");
        List<Object> objects = userMapper.selectObjs(wrapper);
        objects.forEach(System.out::println);
    }

    @Test
    void printInfo(){
        System.out.println(springConfig.getDataSource());
    }
}
