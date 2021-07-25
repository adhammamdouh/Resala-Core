package org.resala;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("org.resala.Repository")
public class ResalaCoreSystemApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ResalaCoreSystemApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
    }


}
