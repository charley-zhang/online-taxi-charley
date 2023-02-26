package com.charley.serviceDriverUser.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * @Author Charley_Zhang
 * @Date 2023/2/27 0:09
 * @ClassName: MysqlGenerator
 * @Version 1.0
 * @Description: 自动生成代码工具类
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
                    builder.addInclude("driver_user_work_status");
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
