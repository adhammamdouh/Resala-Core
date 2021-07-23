package org.resala.Event;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.resala.AbstractTest;
import org.resala.StaticNames;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EventControllerTest extends AbstractTest {
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }
    @Test
    public void t1_add() throws Exception {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(StaticNames.addEvent));
        String token = jwtUtil.createTokenForTest(1, 1, authorities);
        String json = "[{\"name\":\"كوبو 2\",\"fromDate\":\"2021-07-13 12:52\",\"toDate\":\"2021-12-20 12:52\",\"invitationStartTime\":\"2021-07-15 12:52\",\"invitationEndTime\":\"2021-07-15 13:00\",\"feedBackStartTime\":\"2021-07-15 13:52\",\"feedBackEndTime\":\"2021-07-15 14:00\",\"notAttendStartTime\":\"2021-07-15 15:52\",\"notAttendEndTime\":\"2021-07-15 16:00\",\"script\":\"3la allah\",\"description\":\"qq ww ee\",\"hasCalls\":\"true\",\"shareable\":\"true\",\"branches\":[{\"id\":1},{\"id\":2}]},{\"name\":\"aaa\",\"fromDate\":\"2021-07-13 12:52\",\"toDate\":\"2021-12-20 12:52\",\"invitationStartTime\":\"2021-07-15 12:52\",\"invitationEndTime\":\"2021-07-15 13:00\",\"feedBackStartTime\":\"2021-07-15 13:52\",\"feedBackEndTime\":\"2021-07-15 14:00\",\"notAttendStartTime\":\"2021-07-15 15:52\",\"notAttendEndTime\":\"2021-07-15 16:00\",\"script\":\"3la allah\",\"description\":\"qq ww ee\",\"hasCalls\":\"true\",\"shareable\":\"true\",\"branches\":[{\"id\":1},{\"id\":2}]}]";
        mvc.perform(MockMvcRequestBuilders.post("/event/addEvent").header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .content(json)
                .accept(getMediaTypeHeader())
                .contentType(getMediaTypeHeader())).andExpect(status().isOk());
    }
    @Test
    public void t2_changeEventState() throws Exception {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(StaticNames.getAllEventsByState));
        authorities.add(new SimpleGrantedAuthority(StaticNames.completeEvent));
        authorities.add(new SimpleGrantedAuthority(StaticNames.archiveEvent));
        String token = jwtUtil.createTokenForTest(1, 1, authorities);
        String json = "{\"id\":1}";
        mvc.perform(MockMvcRequestBuilders.put("/event/completeEvent").header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .content(json)
                .accept(getMediaTypeHeader())
                .contentType(getMediaTypeHeader())).andExpect(status().isOk());
        mvc.perform(MockMvcRequestBuilders.put("/event/completeEvent").header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .content(json)
                .accept(getMediaTypeHeader())
                .contentType(getMediaTypeHeader())).andExpect(status().isGone());
        mvc.perform(MockMvcRequestBuilders.post("/event/archiveEvent").header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .content(json)
                .accept(getMediaTypeHeader())
                .contentType(getMediaTypeHeader())).andExpect(status().isGone());
        json = "{\"id\":2}";
        mvc.perform(MockMvcRequestBuilders.post("/event/archiveEvent").header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .content(json)
                .accept(getMediaTypeHeader())
                .contentType(getMediaTypeHeader())).andExpect(status().isOk());
        MvcResult  mvcResult=mvc.perform(MockMvcRequestBuilders.get("/event/getAllByState/3").header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .accept(getMediaTypeHeader())
                .contentType(getMediaTypeHeader())).andExpect(status().isOk()).andReturn();
        JSONObject obj = new JSONObject(mvcResult.getResponse().getContentAsString());
        JSONArray jsonArray =obj.getJSONArray("message");
        assertEquals(jsonArray.length(),1);
        assertEquals(jsonArray.getJSONObject(0).getJSONObject("eventStatus").getString("name"),StaticNames.completedState);
        int id=jsonArray.getJSONObject(0).getInt("id");
        assertEquals(id,1);


        mvcResult=mvc.perform(MockMvcRequestBuilders.get("/event/getAllByState/2").header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .accept(getMediaTypeHeader())
                .contentType(getMediaTypeHeader())).andExpect(status().isOk()).andReturn();
        obj = new JSONObject(mvcResult.getResponse().getContentAsString());
        jsonArray =obj.getJSONArray("message");
        assertEquals(jsonArray.length(),1);
        assertEquals(jsonArray.getJSONObject(0).getJSONObject("eventStatus").getString("name"),StaticNames.archivedState);
        id=jsonArray.getJSONObject(0).getInt("id");
        assertEquals(id,2);
    }
}
