package com.example.consulgate;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

//@EnableZuulProxy
//@EnableDiscoveryClient
@SpringCloudApplication
public class ConsulGateApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsulGateApplication.class, args);
    }

}
