package org.resala;

import org.resala.Models.Volunteer.Volunteer;
import org.resala.Service.KPI.LeadVolunteerKPIService;
import org.resala.Service.KPI.VolunteerKPIService;
import org.resala.Service.Volunteer.VolunteerService;
import org.resala.dto.Volunteer.VolunteerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.transaction.Transactional;
import java.util.function.Function;

@SpringBootApplication
@EnableJpaRepositories("org.resala.Repository")
//@EnableTransactionManagement
public class ResalaCoreSystemApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ResalaCoreSystemApplication.class, args);
    }


    @Autowired
    VolunteerKPIService volunteerKPIService;
    @Autowired
    VolunteerService volunteerService;
    @Autowired
    LeadVolunteerKPIService leadVolunteerKPIService;


    @Override
    public void run(String... args) throws Exception {
        //volunteerKPIService.generateKPIsForAll();
//        leadVolunteerKPIService.generateKPIsForAll();
    }

    @Transactional
    public void showAddress(Volunteer volunteer) {

    }

    @Bean
    public Function<String,String> leadVolunteerKPIGeneration1(){
        return timerInfo ->{
            leadVolunteerKPIService.generateKPIsForAll();
            return "";
        };
    }

    @Bean
    public Function<String,String> testing(){
        return timerInfo ->{
            VolunteerDTO volunteerDTO=new VolunteerDTO();
            volunteerDTO.setId(2);
            volunteerService.archive(volunteerDTO);
            return "";
        };
    }

}
