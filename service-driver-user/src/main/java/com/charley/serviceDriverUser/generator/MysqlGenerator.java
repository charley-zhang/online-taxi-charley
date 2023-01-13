package com.charley.serviceDriverUser.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * 自动生成代码工具类
 */
public class MysqlGenerator {

    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/service-driver-user?characterEncoding=utf-8&serverTimezone=GMT%2B8",
                            "123456", "123456")
                .globalConfig(builder -> {
                    builder.author("Charlry").fileOverride().outputDir("D:\\java\\IDEA_Project\\online-taxi-charley\\service-driver-user\\src\\main\\java");
                })
                .packageConfig(builder -> {
                    builder.parent("com.charley.serviceDriverUser").pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D:\\java\\IDEA_Project\\online-taxi-charley\\service-driver-user\\src\\main\\java\\com\\charley\\serviceDriverUser\\mapper"));
                })
                .strategyConfig(builder -> {
                    builder.addInclude("driver_car_binding_relationship");
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
