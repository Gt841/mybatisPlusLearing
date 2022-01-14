package com.kuangstudy.mybatisplus.config;

//新版
//import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
//import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
//import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;

//旧版
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//扫描mapper包
@MapperScan("com.kuangstudy.mybatisplus.mapper")
@EnableTransactionManagement //开启事务管理 (默认开启的)
@Configuration //配置类
public class MyBatisPlusConfig {
    //注册插件时根据官方文档改变
    //注册插件(新版)
    //@Bean
    //public MybatisPlusInterceptor mybatisPlusInterceptor() {
    //    MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
    //    interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());// 乐观锁
    //    interceptor.addInnerInterceptor(new PaginationInnerInterceptor()); //分页插件
    //    //IllegalSQLInnerInterceptor illegalSQLInnerInterceptor = new IllegalSQLInnerInterceptor();
    //    return interceptor;
    //}

    //注册乐观锁插件(3.0.5)
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor(){
        return new OptimisticLockerInterceptor();
    }

    //注册分页插件(3.0.5)
    @Bean
    public PaginationInterceptor paginationInterceptor(){ return new PaginationInterceptor();}

    //注册逻辑删除组件
    @Bean
    public ISqlInjector sqlInjector(){
        return new LogicSqlInjector();
    }

    // sql执行效率插件
    @Bean
    @Profile({"dev","test"}) //指定生效环境在yml文件中进行配置
    public PerformanceInterceptor performanceInterceptor(){
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        //在工作中,不允许用户等待
        performanceInterceptor.setMaxTime(100); //设置sqk执行的最大时间,如果超过了则不执行;单位毫秒
        performanceInterceptor.setFormat(true); //sql格式化
        return performanceInterceptor;
    }

}
