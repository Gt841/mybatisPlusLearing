package com.kuangstudy.mybatisplus.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j //日志
@Component //必须要把处理器注册到spring中
public class MyMetaObjectHandler implements MetaObjectHandler {
    //插入时的填充策略
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert ....");
        /**
         * fieldname 修改的字段名
         * fieldVal 修改的值
         * metaObject 处理的元数据
         * default MetaObjectHandler setFieldValByName(String fieldName, Object fieldVal, MetaObject metaObject)
         */
        this.setFieldValByName("createTime",new Date(),metaObject );
        this.setFieldValByName("updateTime",new Date(),metaObject );
    }
    //更新时的填充策略
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update ....");
        this.setFieldValByName("updateTime",new Date(),metaObject );
    }
}
