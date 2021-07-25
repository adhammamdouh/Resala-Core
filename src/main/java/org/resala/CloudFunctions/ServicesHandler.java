package org.resala.CloudFunctions;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.TimerTrigger;
import org.resala.Service.Volunteer.VolunteerService;
import org.resala.dto.Volunteer.VolunteerDTO;
import org.springframework.cloud.function.adapter.azure.AzureSpringBootRequestHandler;

public class ServicesHandler extends
        AzureSpringBootRequestHandler<String,String> {


    @FunctionName("leadVolunteerKPIGeneration1")
    public void generateLeadVolunteerKPI(
            @TimerTrigger(name = "req",schedule = "0 20 19 * * *") String timerInfo,
            final ExecutionContext context){

        context.getLogger().info("generating lead volunteer kpi");

    }

    @FunctionName("testing")
    public void test(
            @TimerTrigger(name = "req",schedule = "0 0-59 * * * *") String timerInfo,
            final ExecutionContext context){

        VolunteerService volunteerService= new VolunteerService();

        VolunteerDTO volunteerDTO=new VolunteerDTO();
        volunteerDTO.setId(2);
        String res= volunteerService.archive(volunteerDTO).getBody().toString();

        context.getLogger().info("generating lead volunteer kpi "+res);

    }

}
