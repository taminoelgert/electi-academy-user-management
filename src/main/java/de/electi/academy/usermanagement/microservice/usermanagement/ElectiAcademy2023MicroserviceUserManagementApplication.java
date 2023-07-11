package de.electi.academy.usermanagement.microservice.usermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ElectiAcademy2023MicroserviceUserManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElectiAcademy2023MicroserviceUserManagementApplication.class, args);
    }

}
