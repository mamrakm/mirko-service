package cz.ememsoft.micro.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableFeignClients
@SpringBootApplication
@EnableJpaRepositories(basePackages = "cz.ememsoft.micro.service.repository")
@EntityScan(basePackages = "cz.ememsoft.micro.service.domain")
@Slf4j
public class MicroServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(MicroServiceApplication.class, args);
  }

}
