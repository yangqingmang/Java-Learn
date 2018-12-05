package com.qmyang;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 启动
 *
 * @author yangqingmang
 * @version 1.0
 * @date Create in 2018-12-05 17:19
 */
@SpringBootApplication
public class MyApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyApplication.class);

    public static void main(String[] args) {
        LOGGER.info("MyApplication start " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        SpringApplication.run(MyApplication.class, args);
    }
}
