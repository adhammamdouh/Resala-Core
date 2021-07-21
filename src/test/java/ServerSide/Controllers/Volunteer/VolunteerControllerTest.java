package ServerSide.Controllers.Volunteer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.resala.AbstractTest;
import org.resala.Models.Volunteer.Volunteer;
import org.resala.Projections.Volunteer.VolunteerProjection;
import org.resala.Security.Jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class VolunteerControllerTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void getAllVolunteers() throws Exception {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_GET_ALL_VOLUNTEERS"));
        String token = jwtUtil.createTokenForTest(1, 1, authorities);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/volunteer/getAll").header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
        JSONObject obj = new JSONObject(mvcResult.getResponse().getContentAsString());
        Gson gson = new Gson();
        Type type = new TypeToken<List<Volunteer>>(){}.getType();
        List<Volunteer> list = gson.fromJson(obj.get("message").toString(), type);
        for(Volunteer volunteer:list){
            assertNotNull(volunteer);
            assertNotNull(volunteer.getBranch());
            assertNotNull(volunteer.getAddress());
            assertNotNull(volunteer.getVolunteerStatus());
        }
    }

    @Test
    public void getAllVolunteersPublicInfo() throws Exception {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_GET_ALL_VOLUNTEERS_PUBLIC_INFO"));
        String token = jwtUtil.createTokenForTest(1, 1, authorities);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/volunteer/getAll").header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
        JSONObject obj = new JSONObject(mvcResult.getResponse().getContentAsString());
        Gson gson = new Gson();
        Type type = new TypeToken<List<Volunteer>>(){}.getType();
        List<Volunteer> list = gson.fromJson(obj.get("message").toString(), type);
        for(Volunteer volunteer:list){
            assertNotNull(volunteer);
            assertNotNull(volunteer.getBranch());
            assertNull(volunteer.getAddress().getStreetName());
            assertNull(volunteer.getAddress().getAdditionalInfo());
            assertNull(volunteer.getAddress().getApartmentNumber());
            assertNull(volunteer.getAddress().getBuildingNumber());
            assertNotNull(volunteer.getVolunteerStatus());
        }
    }
    @Test
    public void test(){
        assertEquals(0,1);
    }
}
