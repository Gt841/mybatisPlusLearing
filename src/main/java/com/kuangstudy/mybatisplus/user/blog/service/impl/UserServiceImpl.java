package com.kuangstudy.mybatisplus.user.blog.service.impl;

import com.kuangstudy.mybatisplus.user.blog.entity.User;
import com.kuangstudy.mybatisplus.user.blog.mapper.UserMapper;
import com.kuangstudy.mybatisplus.user.blog.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Kuang
 * @since 2022-01-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
