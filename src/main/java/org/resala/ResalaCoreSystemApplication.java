package org.resala;

import org.resala.Models.Volunteer.Volunteer;
import org.resala.Repository.Address.AddressRepo;
import org.resala.Repository.Address.CapitalRepo;
import org.resala.Repository.UserRepository;
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
    AddressRepo addressRepo;

    @Autowired
    CapitalRepo capitalRepo;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @Override
    public void run(String... args) throws Exception {
        //System.out.println(userService.getUser("test").getVolunteer().getPrivileges());
		/*Volunteer volunteer=new Volunteer();
		List<Action>actions=new ArrayList<>();
		Action action=new Action();
		action.setName("ROLE_USER");
		actions.add(action);
		volunteer.setActions(actions);

		Address address=new Address();
		address.setAdditionalInfo("a");
		address.setApartmentNumber("a");
		address.setBuildingNumber("a");
		address.setStreetName("a");
		volunteer.setAddress(address);
		Region region=new Region();
		region.setName("a");
		Capital capital=new Capital();
		capital.setName("a");
		capital.setRegion(region);
		region.setCapital(capital);

		address.setRegion(region);
		region.setAddress(address);

		volunteer.setAddress(address);
		address.setVolunteer(volunteer);

		volunteer.setAge(15);
		volunteer.setBirthDate("a");
		volunteer.setBranchId(1);
		volunteer.setFaculty("a");
		volunteer.setFirstName("a");
		volunteer.setMidName("a");
		volunteer.setLastName("a");
		volunteer.setJoinDate("a");
		volunteer.setMiniCamp(true);
		volunteer.setNationalId("a");
		volunteer.setNickName("a");
		volunteer.setPhoneNumber("a");
		volunteer.setPrivilegeId(1);
		volunteer.setRegion("a");
		volunteer.setRoleId(1);
		volunteer.setStatusId(1);
		volunteer.settShirt(true);
		volunteer.setUniversity("a");

		User user=new User();
		user.setUserName("test");
		user.setPassword("$2a$10$X.iLh2uZ1uni8ujbpd7DfufvBXkurHzMcJKMWNSwWVS13hn8fwedO");
		user.setVolunteer(volunteer);
		volunteer.setUser(user);
		capitalRepository.save(capital);
		regionRepository.save(region);
		addressRepository.save(address);
		volunteerRepository.save(volunteer);
		userRepository.save(user);*/
//        User user = userService.getUser("test");
//        Volunteer volunteer = user.getVolunteer();
//        if (volunteer == null)
//            System.out.println("ZZZZZZZZZZZZZZZZ");
//        else {
//            System.out.println(volunteer.getAge());
//        }
    }

    @Transactional
    public void showAddress(Volunteer volunteer) {

    }
}
