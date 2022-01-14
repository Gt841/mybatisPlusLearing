package com.kuangstudy.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kuangstudy.mybatisplus.mapper.UserMapper;
import com.kuangstudy.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class MybatisPlusApplicationTests {
    @Autowired
    private UserMapper userMapper;
    //userMapper继承了BaseMapper,所有方法都来自父类,我们也可以编写自己的扩展方法
    @Test
    void contextLoads() {

        //参数是wapper,条件构造器。使用null表示没有条件,查询全部用户。
        List<User> users = userMapper.selectList(null);
        for (User user:users
             ) {
            System.out.println(user);
        }
    }
    //插入测试
    @Test
    public void testInsert(){
        User user = new User();
        user.setName("狂神");
        user.setAge(3);
        user.setEmail("24736743@qq.com");
        int i = userMapper.insert(user); //会自动生成id
        System.out.println(i); // 受影响的条数
        System.out.println(user); //id会自动回填
    }
    //更新测试
    @Test
    public void testUpdata(){
        User user = new User();
        //通过条件自动拼接动态sql
        user.setAge(9);
        user.setId(7L);
        //user.setName("狂神说Java");
        System.out.println(user);
        // 注意 updateByid 但是参数是一个对象.里面包含要修改的参数
        int i = userMapper.updateById(user);
        System.out.println(i);
    }

    //测试乐观锁成功
    @Test
    public void testOptimisticLockerSuccess(){
        //1.查询用户信息
        User user = userMapper.selectById(1L);
        //2,修改用户信息
        user.setAge(33);
        //执行单线程操作
        userMapper.updateById(user);
    }
    //测试乐观锁失败多线程下
    @Test
    public void testOptimisticLockerFail(){
        //线程1
        User user1 = userMapper.selectById(1L);
        user1.setAge(1);
        user1.setName("线程1");
        //线程2模拟插队操作
        User user2 = userMapper.selectById(1L);
        user2.setName("线程2");
        user2.setAge(2);
        userMapper.updateById(user2);
        userMapper.updateById(user1); //如果没有乐观锁就会覆盖
    }

    //测试批量查询
    @Test
    public void testSelect(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        users.forEach(System.out::println);
        users.forEach(u -> { u.setAge(12);
            System.out.println(u);
        } );

    }
    //测试条件查询 map
    @Test
    public void testSelectMap(){
        HashMap<String, Object> map = new HashMap<>();
        //自定义查询的条件
        map.put("name","狂神");
        List<User> users = userMapper.selectByMap(map);
        users.forEach(u -> {
            System.out.println(u);
        });
    }

    @Test
    public void testSelectWarper(){

    }

    //测试分页查询
    @Test
    public void testSelectPage(){
        Page<User> page = new Page<>(3, 2);
        userMapper.selectPage(page, null);
        System.out.println(page.getCurrent()); //当前页码
        System.out.println(page.getSize()); //当前页面大小
        System.out.println(page.getTotal()); //当前记录总条数
        List<User> users = page.getRecords();
        users.forEach(System.out::println);

    }
    //测试删除
    @Test
    public void testDelete(){
        userMapper.deleteById(1L); //通过id删除
        userMapper.deleteBatchIds(Arrays.asList(1, 2, 3)); //通过id列表实现批量删除
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","狂神说");
        userMapper.deleteByMap(map); //通过map筛选删除
        userMapper.delete(null); //通过wrapper删除指定数据
    }
    //测试删除
    @Test
    public void testLogicDelete(){
        userMapper.deleteById(1L); //通过id删除
        //userMapper.deleteBatchIds(Arrays.asList(1, 2, 3)); //通过id列表实现批量删除
        //HashMap<String, Object> map = new HashMap<>();
        //map.put("name","狂神说");
        //userMapper.deleteByMap(map); //通过map筛选删除
        //userMapper.delete(null); //通过wrapper删除指定数据
    }

}

