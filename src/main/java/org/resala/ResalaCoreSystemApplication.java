package org.resala;

import org.resala.Models.Volunteer.Volunteer;
import org.resala.Repository.Address.AddressRepo;
import org.resala.Repository.Address.CapitalRepo;
import org.resala.Repository.UserRepository;
import org.resala.Service.KPI.LeadVolunteerKPIService;
import org.resala.Service.KPI.VolunteerKPIService;
import org.resala.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;

@SpringBootApplication
@EnableJpaRepositories("org.resala.Repository")
//@EnableTransactionManagement
public class ResalaCoreSystemApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ResalaCoreSystemApplication.class, args);
    }

   /* @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("https://resala-engine.ey.r.appspot.com");
            }
        };
    }*/

    @Autowired
    VolunteerKPIService volunteerKPIService;
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
}
