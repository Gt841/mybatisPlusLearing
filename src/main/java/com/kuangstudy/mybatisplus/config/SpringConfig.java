package com.kuangstudy.mybatisplus.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Data
@Component
@ConfigurationProperties("spring")
public class SpringConfig {

    private HashMap<String, Object> dataSource;

}
