package org.resala.CloudFunctions;


import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.TimerTrigger;
import org.resala.Service.KPI.LeadVolunteerKPIService;
import org.resala.Service.KPI.VolunteerKPIService;
import org.springframework.beans.factory.annotation.Autowired;

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
    public void generateVolunteerKPI(
                                    ///sec min hour day month day of a week
            @TimerTrigger(name = "req",schedule = "0 0 19 * * *") String timerInfo,
            final ExecutionContext context) {

        volunteerKPIService.generateKPIsForAll();


        context.getLogger().info("volunteer kpi is triggered: " + timerInfo);

    }

    @FunctionName("resala.CloudFunctions.leadVolunteerKPIGeneration")
    public void generateLeadVolunteerKPI(
            @TimerTrigger(name = "req",schedule = "0 20 19 * * *") String timerInfo,
            final ExecutionContext context) {

        leadVolunteerKPIService.generateKPIsForAll();

        context.getLogger().info("lead volunteer kpi is triggered: " + timerInfo);

    }
}
