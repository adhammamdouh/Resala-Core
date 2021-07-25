package org.resala.CloudFunctions;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.TimerTrigger;
import org.resala.Service.Volunteer.VolunteerService;
import org.resala.dto.Volunteer.VolunteerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.function.adapter.azure.AzureSpringBootRequestHandler;
import org.springframework.stereotype.Service;

@Service
public class ServicesHandler extends
        AzureSpringBootRequestHandler<String,String> {
    @Autowired
    VolunteerService volunteerService;


    @FunctionName("leadVolunteerKPIGeneration1")
    public void generateLeadVolunteerKPI(
            @TimerTrigger(name = "req",schedule = "0 20 19 * * *") String timerInfo,
            final ExecutionContext context){

        context.getLogger().info("generating lead volunteer kpi");

    }

    @FunctionName("testing")
    public void test(
            @TimerTrigger(name = "req12",schedule = "0 0-59 * * * *") String timerInfo,
            final ExecutionContext context){

        VolunteerDTO volunteerDTO=new VolunteerDTO();
        volunteerDTO.setId(2);
        String res= volunteerService.archive(volunteerDTO).getBody().toString();

        context.getLogger().info("generating lead volunteer kpi "+res);

    }

}
