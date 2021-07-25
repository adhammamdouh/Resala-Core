package org.resala.CloudFunctions;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import org.springframework.cloud.function.adapter.azure.AzureSpringBootRequestHandler;

public class ServicesHandler extends
        AzureSpringBootRequestHandler<String, String> {

//
//    @FunctionName("leadVolunteerKPIGeneration1")
//    public void generateLeadVolunteerKPI(
//            @TimerTrigger(name = "req",schedule = "0 20 19 * * *") String timerInfo,
//            final ExecutionContext context){
//
//        context.getLogger().info("generating lead volunteer kpi "+timerInfo);
//
//    }

    @FunctionName("testing")
    public String test(
            @HttpTrigger(name = "req123",methods = {HttpMethod.GET,
                    HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Object> request,
            ExecutionContext context) {

        return handleRequest(request.getBody().toString(), context);
    }

}
