package org.resala.Volunteer;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.resala.AbstractTest;
import org.resala.Models.Volunteer.Volunteer;
import org.resala.Service.Volunteer.VolunteerService;
import org.resala.StaticNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VolunteerControllerTest extends AbstractTest {
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void t1_add() throws Exception {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(StaticNames.createVolunteer));
        String token = jwtUtil.createTokenForTest(1, 1, authorities);
        String json = "[{\"age\":\"21\",\"gender\":\"1\",\"address\":{\"capital\":{\"id\":\"1\"},\"streetName\":\"151b\",\"regionName\":\"xx3\",\"buildingNumber\":\"15\",\"apartmentNumber\":\"47\"},\"branch\":{\"id\":\"3\"},\"shirt\":{\"id\":\"1\"},\"faculty\":\"FCI\",\"nationalId\":\"2558844663311\",\"university\":\"Cairo\",\"firstName\":\"okasha\",\"midName\":\"okasha\",\"lastName\":\"okasha\",\"nickName\":\"okasha\",\"phoneNumber\":\"01529180900\",\"joinDate\":\"2020-15-12\",\"birthDate\":\"1999-08-23\",\"miniCamp\":\"false\",\"educationLevel\":{\"id\":1}}]";
        mvc.perform(MockMvcRequestBuilders.get("/volunteer/add").header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .content(json)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isMethodNotAllowed());

        mvc.perform(MockMvcRequestBuilders.post("/volunteer/add").header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .content(json)
                .accept(getMediaTypeHeader())
                .contentType(getMediaTypeHeader())).andExpect(status().isOk());

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/volunteer/add").header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .content(json)
                .accept(getMediaTypeHeader())
                .contentType(getMediaTypeHeader())).andExpect(status().isBadRequest())
                .andReturn();
        JSONObject obj = new JSONObject(mvcResult.getResponse().getContentAsString());
        String error = obj.getJSONArray("error").getJSONObject(0).getString("value");
        assertEquals(error, StaticNames.phoneNumberFound);
        //changed phone num
        json = "[{\"age\":\"21\",\"gender\":\"1\",\"address\":{\"capital\":{\"id\":\"1\"},\"streetName\":\"151b\",\"regionName\":\"xx3\",\"buildingNumber\":\"15\",\"apartmentNumber\":\"47\"},\"branch\":{\"id\":\"3\"},\"shirt\":{\"id\":\"1\"},\"faculty\":\"FCI\",\"nationalId\":\"2558844663311\",\"university\":\"Cairo\",\"firstName\":\"okasha\",\"midName\":\"okasha\",\"lastName\":\"okasha\",\"nickName\":\"okasha\",\"phoneNumber\":\"01539180900\",\"joinDate\":\"2020-15-12\",\"birthDate\":\"1999-08-23\",\"miniCamp\":\"false\",\"educationLevel\":{\"id\":1}}]";
        mvc.perform(MockMvcRequestBuilders.post("/volunteer/add").header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .content(json)
                .accept(getMediaTypeHeader())
                .contentType(getMediaTypeHeader())).andExpect(status().isOk());
    }

    @Test
    public void t2_archiving() throws Exception {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(StaticNames.getVolunteerByPhoneNumber));
        authorities.add(new SimpleGrantedAuthority(StaticNames.requestToArchiveVolunteer));
        authorities.add(new SimpleGrantedAuthority(StaticNames.acceptToArchiveVolunteer));
        authorities.add(new SimpleGrantedAuthority(StaticNames.declineToArchiveVolunteer));
        authorities.add(new SimpleGrantedAuthority(StaticNames.createVolunteer));
        String token = jwtUtil.createTokenForTest(1, 1, authorities);
        String json = "[{\"age\":\"21\",\"gender\":\"1\",\"address\":{\"capital\":{\"id\":\"1\"},\"streetName\":\"151b\",\"regionName\":\"xx3\",\"buildingNumber\":\"15\",\"apartmentNumber\":\"47\"},\"branch\":{\"id\":\"3\"},\"shirt\":{\"id\":\"1\"},\"faculty\":\"FCI\",\"nationalId\":\"2558844663311\",\"university\":\"Cairo\",\"firstName\":\"okasha\",\"midName\":\"okasha\",\"lastName\":\"okasha\",\"nickName\":\"okasha\",\"phoneNumber\":\"01529990900\",\"joinDate\":\"2020-15-12\",\"birthDate\":\"1999-08-23\",\"miniCamp\":\"false\",\"educationLevel\":{\"id\":1}}]";
        mvc.perform(MockMvcRequestBuilders.post("/volunteer/add").header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .content(json)
                .accept(getMediaTypeHeader())
                .contentType(getMediaTypeHeader())).andExpect(status().isOk());


        String jsonPhone = "{\"phoneNumber\":\"01529990900\"}";
        MvcResult mvcVolunteerResult = mvc.perform(MockMvcRequestBuilders.post("/volunteer/getByPhoneNumber").header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .content(jsonPhone)
                .accept(getMediaTypeHeader())
                .contentType(getMediaTypeHeader())).andExpect(status().isOk()).andReturn();
        JSONObject obj = new JSONObject(mvcVolunteerResult.getResponse().getContentAsString());
        String state=obj.getJSONObject("message").getJSONObject("volunteerStatus").getString("name");
//        System.out.println(state);
        assertEquals(state,StaticNames.activeState);
        int id = obj.getJSONObject("message").getInt("id");

        String jsonId = "{\"id\":"+id+"}";
        mvc.perform(MockMvcRequestBuilders.post("/volunteer/requestToArchive").header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .content(jsonId)
                .accept(getMediaTypeHeader())
                .contentType(getMediaTypeHeader())).andExpect(status().isOk());
        /*mvc.perform(MockMvcRequestBuilders.post("/volunteer/acceptToArchive").header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .content(jsonId)
                .accept(getMediaTypeHeader())
                .contentType(getMediaTypeHeader())).andExpect(status().isOk());

        mvcVolunteerResult = mvc.perform(MockMvcRequestBuilders.post("/volunteer/getByPhoneNumber").header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .content(jsonPhone)
                .accept(getMediaTypeHeader())
                .contentType(getMediaTypeHeader())).andExpect(status().isOk()).andReturn();

        obj = new JSONObject(mvcVolunteerResult.getResponse().getContentAsString());
        Gson gson = new Gson();
        Volunteer volunteer = gson.fromJson(obj.getJSONObject("message").toString(), Volunteer.class);
        assertEquals(volunteer.getVolunteerStatus().getName(),StaticNames.archivedState);*/

    }

    @Test
    public void t3_getAllVolunteers() throws Exception {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_GET_ALL_VOLUNTEERS"));
        String token = jwtUtil.createTokenForTest(1, 1, authorities);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/volunteer/getAll").header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
        JSONObject obj = new JSONObject(mvcResult.getResponse().getContentAsString());
        Gson gson = new Gson();
        Type type = new TypeToken<List<Volunteer>>() {
        }.getType();
        List<Volunteer> list = gson.fromJson(obj.get("message").toString(), type);
        for (Volunteer volunteer : list) {
            assertNotNull(volunteer);
            assertNotNull(volunteer.getBranch());
            assertNotNull(volunteer.getAddress());
            assertNotNull(volunteer.getVolunteerStatus());
        }
    }

    @Test
    public void t4_getAllVolunteersPublicInfo() throws Exception {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_GET_ALL_VOLUNTEERS_PUBLIC_INFO"));
        String token = jwtUtil.createTokenForTest(1, 1, authorities);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/volunteer/getAll").header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
        JSONObject obj = new JSONObject(mvcResult.getResponse().getContentAsString());
        Gson gson = new Gson();
        Type type = new TypeToken<List<Volunteer>>() {
        }.getType();
        List<Volunteer> list = gson.fromJson(obj.get("message").toString(), type);
        for (Volunteer volunteer : list) {
            assertNotNull(volunteer);
            assertNotNull(volunteer.getBranch());
            assertNull(volunteer.getAddress().getStreetName());
            assertNull(volunteer.getAddress().getAdditionalInfo());
            assertNull(volunteer.getAddress().getApartmentNumber());
            assertNull(volunteer.getAddress().getBuildingNumber());
            assertNotNull(volunteer.getVolunteerStatus());
        }
    }

}
