package org.resala;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
import org.junit.runner.RunWith;
import org.resala.Models.Volunteer.Volunteer;
import org.resala.ResalaCoreSystemApplication;
import org.resala.Security.Jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ResalaCoreSystemApplication.class)
@WebAppConfiguration
public abstract class AbstractTest {
    protected MockMvc mvc;
    @Autowired
    WebApplicationContext webApplicationContext;
    @Autowired
    protected JwtUtil jwtUtil;

    protected void setUp() {

        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
    }

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    protected <T> T mapFromJson(String json, Class<T> myClass)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, myClass);
    }

    /*protected <T> List<T> mapJsonToListOfObjects(String json, Class<T> tClass) throws JsonProcessingException {
        *//*ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, new TypeReference<List<T>>() {
        });*//*

        Gson gson = new Gson();
        Type type = new TypeToken<List<T>>(){}.getType();

        return gson.fromJson(json, type);
    }*/

    protected void setAuth(List<SimpleGrantedAuthority> authorities, int orgId, int branchId) {

        Authentication auth = new AbstractAuthenticationToken(authorities) {
            @Override
            public Object getCredentials() {
                return orgId + "," + branchId;
            }

            @Override
            public Object getPrincipal() {
                return null;
            }
        };

        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);
    }
}
