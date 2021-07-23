package org.resala.CloudFunctions;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.TimerTrigger;
import org.resala.Service.KPI.LeadVolunteerKPIService;
import org.resala.Service.KPI.VolunteerKPIService;
import org.resala.Service.Volunteer.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Azure Functions with HTTP Trigger.
 */
@Service
public class Test {

    @Autowired
    VolunteerKPIService volunteerKPIService;
    @Autowired
    LeadVolunteerKPIService leadVolunteerKPIService;
    @Autowired
    VolunteerService volunteerService;
    /**
     * This function listens at endpoint "/api/test". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/test
     * 2. curl {your host}/api/test?name=HTTP%20Query
     */

//    @FunctionName("test2")
//    public HttpResponseMessage hello(
//            @HttpTrigger(name = "test2", methods = {HttpMethod.GET, HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
//            final ExecutionContext context) {
//
//        VolunteerDTO volunteerDTO=new VolunteerDTO();
//        volunteerDTO.setId(2);
//        volunteerService.archive(volunteerDTO);
//        context.getLogger().info("Java HTTP trigger processed a request.");
//
//       return request.createResponseBuilder(HttpStatus.OK).body("Hello, Okasha" ).build();
//    }


        public  String getParamsString(Map<String, String> params)
                throws UnsupportedEncodingException {
            StringBuilder result = new StringBuilder();

            for (Map.Entry<String, String> entry : params.entrySet()) {
                result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                result.append("&");
            }

            String resultString = result.toString();
            return resultString.length() > 0
                    ? resultString.substring(0, resultString.length() - 1)
                    : resultString;
        }



    @FunctionName("test")
    public void run(
            @TimerTrigger(name = "req",schedule = "0 0-59 * * * *") String timerInfo,
            final ExecutionContext context) {

        String httpsUrl="https://resala-core.azurewebsites.net/volunteer/acceptToArchive";
        URL url;
        String token="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiYXVkIjoiW1JPTEVfR0VUX0FMTF9WT0xVTlRFRVJTLCBST0xFX0dFVF9BTExfVk9MVU5URUVSU19QVUJMSUNfSU5GTywgUk9MRV9HRVRfVk9MVU5URUVSU19CWV9NWV9CUkFOQ0hfSUQsIFJPTEVfR0VUX1ZPTFVOVEVFUlNfUFVCTElDX0lORk9fQllfTVlfQlJBTkNILCBST0xFX0NSRUFURV9WT0xVTlRFRVIsIFJPTEVfVVBEQVRFX1ZPTFVOVEVFUiwgUk9MRV9SRVFVRVNUX1RPX0FSQ0hJVkVfVk9MVU5URUVSLCBST0xFX0FDQ0VQVF9UT19BUkNISVZFX1ZPTFVOVEVFUiwgUk9MRV9ERUNMSU5FX1RPX0FSQ0hJVkVfVk9MVU5URUVSLCBST0xFX0dFVF9BTExfVk9MVU5URUVSU19CWV9TVEFUVVMsIFJPTEVfR0VUX0FMTF9WT0xVTlRFRVJTX1BVQkxJQ19JTkZPX0JZX1NUQVRVUywgUk9MRV9HRVRfQUxMX1ZPTFVOVEVFUlNfQllfU1RBVFVTX0FORF9NWV9CUkFOQ0gsIFJPTEVfR0VUX0FMTF9WT0xVTlRFRVJTX1BVQkxJQ19JTkZPX0JZX1NUQVRVU19BTkRfTVlfQlJBTkNILCBST0xFX0dFVF9BTExfRVZFTlRTLCBST0xFX0dFVF9FVkVOVFNfQllfTVlfQlJBTkNILCBST0xFX0FERF9FVkVOVCwgUk9MRV9BUkNISVZFX0VWRU5ULCBST0xFX0NPTVBMRVRFX0VWRU5ULCBST0xFX1VQREFURV9FVkVOVCwgUk9MRV9HRVRfQWxsX0VWRU5UU19CWV9TVEFURSwgUk9MRV9HRVRfQWxsX1NIQVJFQUJMRV9FVkVOVFNfQllfU1RBVEUsIFJPTEVfR0VUX0FsbF9FVkVOVFNfQllfU1RBVEVfQU5EX01ZX0JSQU5DSCwgUk9MRV9HRVRfQWxsX1NIQVJFQUJMRV9FVkVOVFNfQllfU1RBVEVfQU5EX01ZX0JSQU5DSCwgUk9MRV9HRVRfQUxMX0xFQURfVk9MVU5URUVSUywgUk9MRV9HRVRfQUxMX0xFQURfVk9MVU5URUVSU19QVUJMSUNfSU5GTywgUk9MRV9HRVRfTEVBRF9WT0xVTlRFRVJTX0JZX01ZX0JSQU5DSCwgUk9MRV9HRVRfTEVBRF9WT0xVTlRFRVJTX1BVQkxJQ19JTkZPX0JZX01ZX0JSQU5DSCwgUk9MRV9HRVRfQUxMX0xFQURfVk9MVU5URUVSU19CWV9TVEFURSwgUk9MRV9HRVRfQUxMX0xFQURfVk9MVU5URUVSU19QVUJMSUNfSU5GT19CWV9TVEFURSwgUk9MRV9HRVRfQUxMX0xFQURfVk9MVU5URUVSU19CWV9TVEFURV9BTkRfTVlfQlJBTkNILCBST0xFX0dFVF9BTExfTEVBRF9WT0xVTlRFRVJTX1BVQkxJQ19JTkZPX0JZX1NUQVRFX0FORF9NWV9CUkFOQ0gsIFJPTEVfQVNTSUdOX0NBTExTLCBST0xFX0dFVF9BU1NJR05FRF9DQUxMUywgUk9MRV9TVUJNSVRfQVNTSUdORURfQ0FMTFMsIFJPTEVfTUFLRV9FVkVOVF9BVFRFTkRBTkNFX1RPX1ZPTFVOVEVFUiwgUk9MRV9DUkVBVEVfTEVBRF9WT0xVTlRFRVIsIFJPTEVfR0VUX0FMTF9DT01NSVRURUVfVEVBTSwgUk9MRV9HRVRfTkVUV09SS19UWVBFX0FTU0lHTkVEX1RPX1ZPTFVOVEVFUlNdIiwiaXNzIjoiMSwzIiwiZXhwIjo2MTYwMTgxMDQwMCwiaWF0IjoxNjI2MTkzODM0fQ.RbdJbPep43zqneIx3Pt2Vc-A78yqTk7dL2b3ekV-ViY";



        try{
            url = new URL(httpsUrl);
            HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization","Bearer "+token);

            Map<String, String> parameters = new HashMap<>();
            parameters.put("id", "2");

            con.setDoOutput(true);
            DataOutputStream out = new DataOutputStream(con.getOutputStream());
            out.writeBytes(getParamsString(parameters));
            out.flush();
            out.close();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            con.disconnect();
            context.getLogger().info("RESULT IS  "+content.toString()+" " + timerInfo);

        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        context.getLogger().info("lead volunteer kpi is triggered: " + timerInfo);
    }

    @FunctionName("volunteerKPIGeneration")
    public void generateVolunteerKPI(
            ///sec min hour day month day of a week
            @TimerTrigger(name = "timerInfo",schedule = "0 0 19 * * *") String timerInfo,
            final ExecutionContext context) {
        String httpsUrl="https://resala-core.azurewebsites.net/volunteerKPI/generateKPIs";
        URL url;
        String token="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiYXVkIjoiW1JPTEVfR0VUX0FMTF9WT0xVTlRFRVJTLCBST0xFX0dFVF9BTExfVk9MVU5URUVSU19QVUJMSUNfSU5GTywgUk9MRV9HRVRfVk9MVU5URUVSU19CWV9NWV9CUkFOQ0hfSUQsIFJPTEVfR0VUX1ZPTFVOVEVFUlNfUFVCTElDX0lORk9fQllfTVlfQlJBTkNILCBST0xFX0NSRUFURV9WT0xVTlRFRVIsIFJPTEVfVVBEQVRFX1ZPTFVOVEVFUiwgUk9MRV9SRVFVRVNUX1RPX0FSQ0hJVkVfVk9MVU5URUVSLCBST0xFX0FDQ0VQVF9UT19BUkNISVZFX1ZPTFVOVEVFUiwgUk9MRV9ERUNMSU5FX1RPX0FSQ0hJVkVfVk9MVU5URUVSLCBST0xFX0dFVF9BTExfVk9MVU5URUVSU19CWV9TVEFUVVMsIFJPTEVfR0VUX0FMTF9WT0xVTlRFRVJTX1BVQkxJQ19JTkZPX0JZX1NUQVRVUywgUk9MRV9HRVRfQUxMX1ZPTFVOVEVFUlNfQllfU1RBVFVTX0FORF9NWV9CUkFOQ0gsIFJPTEVfR0VUX0FMTF9WT0xVTlRFRVJTX1BVQkxJQ19JTkZPX0JZX1NUQVRVU19BTkRfTVlfQlJBTkNILCBST0xFX0dFVF9BTExfRVZFTlRTLCBST0xFX0dFVF9FVkVOVFNfQllfTVlfQlJBTkNILCBST0xFX0FERF9FVkVOVCwgUk9MRV9BUkNISVZFX0VWRU5ULCBST0xFX0NPTVBMRVRFX0VWRU5ULCBST0xFX1VQREFURV9FVkVOVCwgUk9MRV9HRVRfQWxsX0VWRU5UU19CWV9TVEFURSwgUk9MRV9HRVRfQWxsX1NIQVJFQUJMRV9FVkVOVFNfQllfU1RBVEUsIFJPTEVfR0VUX0FsbF9FVkVOVFNfQllfU1RBVEVfQU5EX01ZX0JSQU5DSCwgUk9MRV9HRVRfQWxsX1NIQVJFQUJMRV9FVkVOVFNfQllfU1RBVEVfQU5EX01ZX0JSQU5DSCwgUk9MRV9HRVRfQUxMX0xFQURfVk9MVU5URUVSUywgUk9MRV9HRVRfQUxMX0xFQURfVk9MVU5URUVSU19QVUJMSUNfSU5GTywgUk9MRV9HRVRfTEVBRF9WT0xVTlRFRVJTX0JZX01ZX0JSQU5DSCwgUk9MRV9HRVRfTEVBRF9WT0xVTlRFRVJTX1BVQkxJQ19JTkZPX0JZX01ZX0JSQU5DSCwgUk9MRV9HRVRfQUxMX0xFQURfVk9MVU5URUVSU19CWV9TVEFURSwgUk9MRV9HRVRfQUxMX0xFQURfVk9MVU5URUVSU19QVUJMSUNfSU5GT19CWV9TVEFURSwgUk9MRV9HRVRfQUxMX0xFQURfVk9MVU5URUVSU19CWV9TVEFURV9BTkRfTVlfQlJBTkNILCBST0xFX0dFVF9BTExfTEVBRF9WT0xVTlRFRVJTX1BVQkxJQ19JTkZPX0JZX1NUQVRFX0FORF9NWV9CUkFOQ0gsIFJPTEVfQVNTSUdOX0NBTExTLCBST0xFX0dFVF9BU1NJR05FRF9DQUxMUywgUk9MRV9TVUJNSVRfQVNTSUdORURfQ0FMTFMsIFJPTEVfTUFLRV9FVkVOVF9BVFRFTkRBTkNFX1RPX1ZPTFVOVEVFUiwgUk9MRV9DUkVBVEVfTEVBRF9WT0xVTlRFRVIsIFJPTEVfR0VUX0FMTF9DT01NSVRURUVfVEVBTSwgUk9MRV9HRVRfTkVUV09SS19UWVBFX0FTU0lHTkVEX1RPX1ZPTFVOVEVFUlNdIiwiaXNzIjoiMSwzIiwiZXhwIjo2MTYwMTgxMDQwMCwiaWF0IjoxNjI2MTkzODM0fQ.RbdJbPep43zqneIx3Pt2Vc-A78yqTk7dL2b3ekV-ViY";
        try{
            url = new URL(httpsUrl);
            HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization","Bearer "+token);
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        context.getLogger().info("volunteer kpi is triggered: " + timerInfo);

    }

    @FunctionName("leadVolunteerKPIGeneration")
    public void generateLeadVolunteerKPI(
            @TimerTrigger(name = "req",schedule = "0 20 19 * * *") String timerInfo,
            final ExecutionContext context) {

        leadVolunteerKPIService.generateKPIsForAll();

        context.getLogger().info("lead volunteer kpi is triggered: " + timerInfo);

    }
}
