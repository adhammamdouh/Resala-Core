package org.resala.Volunteer;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.resala.AbstractTest;
import org.resala.StaticNames;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LeadVolunteerControllerTest extends AbstractTest {
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void t1_getCommitteeTeam() throws Exception {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(StaticNames.getMyBranchCommitteeTeam));
        authorities.add(new SimpleGrantedAuthority(StaticNames.createLeadVolunteer));
        String token = jwtUtil.createTokenForTest(1, 3, authorities);
        String json = "[{\"age\":\"21\",\"gender\":\"1\",\"address\":{\"capital\":{\"id\":\"1\"},\"streetName\":\"151b\",\"regionName\":\"xx3\",\"buildingNumber\":\"15\",\"apartmentNumber\":\"47\"},\"branch\":{\"id\":\"3\"},\"shirt\":{\"id\":\"1\"},\"faculty\":\"FCI\",\"nationalId\":\"2558844663311\",\"university\":\"Cairo\",\"firstName\":\"okasha\",\"midName\":\"okasha\",\"lastName\":\"okasha\",\"nickName\":\"okasha\",\"phoneNumber\":\"01055570900\",\"joinDate\":\"2020-15-12\",\"birthDate\":\"1999-08-23\",\"miniCamp\":\"false\",\"role\":{\"id\":3},\"educationLevel\":{\"id\":1},\"personalImageUrl\":\"15b\",\"resalaObjective\":\"200VVQ\",\"personalObjective\":\"15\",\"selfSkills\":\"47\",\"dreams\":\"FCI\",\"nationalIdUrl\":\"2558844663311\",\"doctorMeeting\":\"true\",\"graduationDate\":\"2020-10-12\",\"graduationNumber\":\"2\",\"camp48\":\"2\",\"committee\":{\"id\":2},\"graduated\":\"true\",\"omra\":\"2020-10-12\"}]";
        mvc.perform(MockMvcRequestBuilders.post("/leadVolunteer/add").header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .content(json)
                .accept(getMediaTypeHeader())
                .contentType(getMediaTypeHeader())).andExpect(status().isOk());

        json = "[{\"age\":\"21\",\"gender\":\"1\",\"address\":{\"capital\":{\"id\":\"1\"},\"streetName\":\"151b\",\"regionName\":\"xx3\",\"buildingNumber\":\"15\",\"apartmentNumber\":\"47\"},\"branch\":{\"id\":\"3\"},\"shirt\":{\"id\":\"1\"},\"faculty\":\"FCI\",\"nationalId\":\"2558844663311\",\"university\":\"Cairo\",\"firstName\":\"okasha\",\"midName\":\"okasha\",\"lastName\":\"okasha\",\"nickName\":\"okasha\",\"phoneNumber\":\"01055270900\",\"joinDate\":\"2020-15-12\",\"birthDate\":\"1999-08-23\",\"miniCamp\":\"false\",\"role\":{\"id\":3},\"educationLevel\":{\"id\":1},\"personalImageUrl\":\"15b\",\"resalaObjective\":\"200VVQ\",\"personalObjective\":\"15\",\"selfSkills\":\"47\",\"dreams\":\"FCI\",\"nationalIdUrl\":\"2558844663311\",\"doctorMeeting\":\"true\",\"graduationDate\":\"2020-10-12\",\"graduationNumber\":\"2\",\"camp48\":\"2\",\"committee\":{\"id\":2},\"graduated\":\"true\",\"omra\":\"2020-10-12\"}" +
                ",{\"age\":\"21\",\"gender\":\"1\",\"address\":{\"capital\":{\"id\":\"1\"},\"streetName\":\"151b\",\"regionName\":\"xx3\",\"buildingNumber\":\"15\",\"apartmentNumber\":\"47\"},\"branch\":{\"id\":\"3\"},\"shirt\":{\"id\":\"1\"},\"faculty\":\"FCI\",\"nationalId\":\"2558844663311\",\"university\":\"Cairo\",\"firstName\":\"okasha\",\"midName\":\"okasha\",\"lastName\":\"okasha\",\"nickName\":\"okasha\",\"phoneNumber\":\"01055270911\",\"joinDate\":\"2020-15-12\",\"birthDate\":\"1999-08-23\",\"miniCamp\":\"false\",\"role\":{\"id\":1},\"educationLevel\":{\"id\":1},\"personalImageUrl\":\"15b\",\"resalaObjective\":\"200VVQ\",\"personalObjective\":\"15\",\"selfSkills\":\"47\",\"dreams\":\"FCI\",\"nationalIdUrl\":\"2558844663311\",\"doctorMeeting\":\"true\",\"graduationDate\":\"2020-10-12\",\"graduationNumber\":\"2\",\"camp48\":\"2\",\"committee\":{\"id\":2},\"graduated\":\"true\",\"omra\":\"2020-10-12\"}]";
        mvc.perform(MockMvcRequestBuilders.post("/leadVolunteer/add").header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .content(json)
                .accept(getMediaTypeHeader())
                .contentType(getMediaTypeHeader())).andExpect(status().isOk());

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/leadVolunteer/getBranchCommitteeTeam/2").header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .accept(getMediaTypeHeader())
                .contentType(getMediaTypeHeader())).andExpect(status().isOk())
                .andReturn();
        JSONObject obj = new JSONObject(mvcResult.getResponse().getContentAsString());
        JSONArray array = obj.getJSONArray("message");
        assertEquals(array.length(), 2);

        for (int i = 0; i < array.length(); i++) {
            JSONObject currObj = array.getJSONObject(i);
            assertEquals(currObj.getJSONObject("committee").getInt("id"), 2);
        }

    }
}