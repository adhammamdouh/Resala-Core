package org.resala.CloudFunctions;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.TimerTrigger;
import org.springframework.cloud.function.adapter.azure.AzureSpringBootRequestHandler;

import java.security.Provider;

public class ServicesHandler extends
        AzureSpringBootRequestHandler<String,String> {


    @FunctionName("leadVolunteerKPIGeneration1")
    public void generateLeadVolunteerKPI(
            @TimerTrigger(name = "req",schedule = "0 20 19 * * *") String timerInfo,
            final ExecutionContext context, Provider provider){

        context.getLogger().info("generating lead volunteer kpi "+timerInfo);

    }

    @FunctionName("testing")
    public void test(
            @TimerTrigger(name = "req1",schedule = "0 0-59 * * * *") String timerInfo,
            final ExecutionContext context, Provider provider){

        context.getLogger().info("deleting volunteer "+timerInfo+" msg ");

    }

}
