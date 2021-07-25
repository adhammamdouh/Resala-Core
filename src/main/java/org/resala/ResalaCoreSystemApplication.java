package org.resala;

import org.resala.Service.KPI.LeadVolunteerKPIService;
import org.resala.Service.Volunteer.VolunteerService;
import org.resala.dto.Volunteer.VolunteerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.function.Function;

@SpringBootApplication
@EnableJpaRepositories("org.resala.Repository")
public class ResalaCoreSystemApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ResalaCoreSystemApplication.class, args);
    }

    @Autowired
    VolunteerService volunteerService;
    @Autowired
    LeadVolunteerKPIService leadVolunteerKPIService;


    @Override
    public void run(String... args) throws Exception {
    }

//    @Bean
//    public Function<String,String> leadVolunteerKPIGeneration1(){
//        return timerInfo -> leadVolunteerKPIService.generateKPIsForAll().getBody().toString();
//    }

    @Bean
    public Function<String,String> testing(){
        VolunteerDTO volunteerDTO=new VolunteerDTO();
        volunteerDTO.setId(2);
        return timerInfo -> volunteerService.archive(volunteerDTO).getBody().toString();
    }

}
