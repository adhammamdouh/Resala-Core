package org.resala;

import io.micronaut.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.TimerTrigger;
import org.springframework.cloud.function.adapter.azure.AzureSpringBootRequestHandler;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@SpringBootApplication
@EnableJpaRepositories("org.resala.Repository")
public class ResalaCoreSystemApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ResalaCoreSystemApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println();
    }


}
