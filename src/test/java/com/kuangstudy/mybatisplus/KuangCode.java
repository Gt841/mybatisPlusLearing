package com.kuangstudy.mybatisplus;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.kuangstudy.mybatisplus.config.SpringConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

//代码生成器
public class KuangCode {

    @Test
    public static void main(String[] args){
        //构建代码生成器对象
        AutoGenerator mgp = new AutoGenerator();
        //配置策略
        //1.全局配置
        GlobalConfig gc = new GlobalConfig();
        //获取当前目录
        String projectPath = System.getProperty("user.dir");
        System.out.println(projectPath);
        //设置当前输出路径
        gc.setOutputDir(projectPath+"/src/main/java");
        gc.setAuthor("Kuang");//设置作者信息
        gc.setOpen(false);//是否打开输出目录
        gc.setFileOverride(false);//是否覆盖原有的
        gc.setServiceName("%sService");//去接口的I前缀
        gc.setIdType(IdType.ID_WORKER);//设置生成的主键的ID类型
        gc.setDateType(DateType.ONLY_DATE);//设置日期格式
        gc.setSwagger2(false);//配置swagger文档
        mgp.setGlobalConfig(gc);//全局配置生效

        //2.数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://域名端口/kuangstudy_bbs?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("password");
        dsc.setDbType(DbType.MYSQL);
        mgp.setDataSource(dsc);

        //3. 生成包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("blog");
        pc.setParent("com.kuangstudy.mybatisplus.user");
        pc.setEntity("entity");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setController("controller");
        mgp.setPackageInfo(pc);

        //4.策略配置
        StrategyConfig sc = new StrategyConfig();
        sc.setNaming(NamingStrategy.underline_to_camel);//驼峰命名
        sc.setColumnNaming(NamingStrategy.underline_to_camel);//驼峰
        sc.setInclude("user");//要映射的表;
        sc.setEntityLombokModel(true); //自动lombok
        sc.setLogicDeleteFieldName("deleted");
        //自动填充策略
        List<TableFill> fillList = new ArrayList<>();
        fillList.add(new TableFill("gmt_create", FieldFill.INSERT));
        fillList.add(new TableFill("gmt_modified", FieldFill.INSERT_UPDATE));
        sc.setTableFillList(fillList);
        //乐观锁
        sc.setVersionFieldName("version");
        //驼峰
        sc.setRestControllerStyle(true);
        sc.setControllerMappingHyphenStyle(true);
        mgp.setStrategy(sc);

        //5. 执行
        mgp.execute();//执行
    }

}
