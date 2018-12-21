package com.qmyang;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 *
 * @author yangqingmang
 * @version 1.0
 * @date Create in 2018-12-05 15:02
 */
@SpringBootApplication
public class MyApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyApplication.class);

    public static void main(String[] args) {
        LOGGER.info("============");
        SpringApplication.run(MyApplication.class, args);
    }

}
