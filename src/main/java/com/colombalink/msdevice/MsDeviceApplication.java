package com.colombalink.msdevice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MsDeviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsDeviceApplication.class, args);
    }

}
