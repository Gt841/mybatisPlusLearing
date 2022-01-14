package com.kuangstudy.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    //对应数据库中的主键(uuid,自增id,雪花算法,redis,zookeeper)
    @TableId(type = IdType.AUTO)
    private Long id;
    //其它属性
    private String name;
    private Integer age;
    private String email;

    @TableLogic //逻辑删除
    private int deleted;

    @Version //乐观锁version注解
    private int version;

    //新增时间戳
    //增加填充内容
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
