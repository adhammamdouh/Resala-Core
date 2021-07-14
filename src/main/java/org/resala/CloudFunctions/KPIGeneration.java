package org.resala.CloudFunctions;


import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.TimerTrigger;
import org.resala.Service.KPI.LeadVolunteerKPIService;
import org.resala.Service.KPI.VolunteerKPIService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

/**
 * Azure Functions with HTTP Trigger.
 */
public class KPIGeneration {
    @Autowired
    VolunteerKPIService volunteerKPIService;
    @Autowired
    LeadVolunteerKPIService leadVolunteerKPIService;
    /**
     * This function listens at endpoint "/api/org.resala.CloudFunctions.KPIGeneration". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/org.resala.CloudFunctions.KPIGeneration
     * 2. curl {your host}/api/resala.CloudFunctions.KPIGeneration?name=HTTP%20Query
     */
    @FunctionName("resala.CloudFunctions.volunteerKPIGeneration")
    public HttpResponseMessage generateVolunteerKPI(
                                    ///sec min hour day month day of a week
            @TimerTrigger(name = "req",schedule = "0 0 19 * * *") HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {

        volunteerKPIService.generateKPIsForAll();


        return request.createResponseBuilder(HttpStatus.OK).body("KPI generated successfully for volunteers").build();

    }

    @FunctionName("resala.CloudFunctions.leadVolunteerKPIGeneration")
    public HttpResponseMessage generateLeadVolunteerKPI(
            @TimerTrigger(name = "req",schedule = "0 0 21 * * *") HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {

        leadVolunteerKPIService.generateKPIsForAll();


        return request.createResponseBuilder(HttpStatus.OK).body("KPI generated successfully for lead volunteers").build();

    }
}
