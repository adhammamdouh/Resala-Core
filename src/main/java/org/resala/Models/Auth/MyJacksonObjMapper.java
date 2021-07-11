package org.resala.Models.Auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class MyJacksonObjMapper extends ObjectMapper {
    public MyJacksonObjMapper() {
        super();
        this.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        this.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        this.configure(MapperFeature.AUTO_DETECT_IS_GETTERS, true);
        this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        this.setSerializationInclusion(JsonInclude.Include.NON_ABSENT);
        this.registerModule(new JavaTimeModule());
        this.registerModule(new JodaModule());
    }
}
