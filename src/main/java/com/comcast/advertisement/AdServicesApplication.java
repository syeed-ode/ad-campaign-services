package com.comcast.advertisement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Ad Service Application
 *
 * <p>
 * Author: syeedode
 * Date: 7/13/17
 */

@SpringBootApplication
public class AdServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdServicesApplication.class, args);
    }
}
