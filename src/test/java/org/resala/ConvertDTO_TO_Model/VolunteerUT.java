package org.resala.ConvertDTO_TO_Model;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VolunteerUT {
    private static final ModelMapper modelMapper = new ModelMapper();
    @Test
    public void checkVolunteerMapping() {
        /*VolunteerCreationDTO creation = new VolunteerCreationDTO();
        creation.setAge(15);
        Address address=new Address();
        address.setStreetName("aa");
        address.setBuildingNumber("1a");
        address.setApartmentNumber("asdas");

        creation.setAddress(address);
        creation.setBirthDate("aa");
        creation.setFaculty("Asd");
        creation.setFirstName("a");
        creation.setMidName("asd");
        creation.setLastName("qq");
        creation.setJoinDate("asdasd");
        creation.setMiniCamp(true);
        creation.setNationalId("1");
        creation.setNickName("zzz");
        creation.setUniversity("asdasd");
        Branch branch=new Branch();
        branch.setId(1);
        branch.setName("1");
        branch.setRegion("aaa");
        creation.setBranch(branch);*/

        //Volunteer volunteer = modelMapper.map(creation, Volunteer.class);
        /*assertEquals(creation.getAddress(), volunteer.getAddress());
        assertEquals(creation.getBranch(), volunteer.getBranch());
        assertEquals(creation.getFirstName(), volunteer.getFirstName());
        assertEquals(creation.getLastName(), volunteer.getLastName());*/

        /*ExamUpdateDTO update = new ExamUpdateDTO();
        update.setTitle("New title");
        update.setDescription("New description");

        modelMapper.map(update, exam);
        assertEquals(update.getTitle(), exam.getTitle());
        assertEquals(update.getDescription(), exam.getDescription());
        assertEquals(creation.getCreatedAt(), exam.getCreatedAt());
        assertEquals(update.getEditedAt(), exam.getEditedAt());*/
    }
}
