package org.resala;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthorizePassword {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[\\w]+@[\\w]+\\.[A-Za-z]{2,3}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public static void main(String[] args) throws IOException {

        /*for (int i=1;i<=50;++i)
            System.out.println("insert ignore into privilege_action (privilege_id,action_id) values (7,"+i+");");

        System.out.println(validate("Resala@resala.cccc"));
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pass = "cloud";
        System.out.println(encoder.encode(pass));*/

        String token = login();
        String res=generateLeadKPI(token);
        System.out.println(res);
        /*String xx=generateLeadKPI(token);
        System.out.println(xx);*/


    }

    private static String login() throws IOException {
        URL url = new URL("https://resala-core.azurewebsites.net/login");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestMethod("POST");
        con.setDoOutput(true);

        String json = "{\"userName\":\"cloud@resala.org\",\"password\":\"cloud\"}";
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = json.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            String responseLine = br.readLine();
            String token = responseLine.substring(responseLine.lastIndexOf("\"token\":\"") + "\"token\":\"".length(), responseLine.lastIndexOf("\""));
            return token;
        }
    }


    private static String generateLeadKPI(String token) throws IOException {
        /*URL url = new URL("https://resala-core.azurewebsites.net/leadVolunteerKPI/generateKPIs");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("Authorization", "Bearer " + token);
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestMethod("POST");
        con.setDoOutput(true);


        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            return br.readLine();
        }*/




        URL url = new URL("https://resala-core.azurewebsites.net/leadVolunteerKPI/generateKPIs");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestProperty("Authorization","Bearer "+token);

        conn.setRequestProperty("Content-Type", "application/json; utf-8");
        conn.setRequestMethod("POST");


        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String output;

        StringBuffer response = new StringBuffer();
        while ((output = in.readLine()) != null) {
            response.append(output);
        }

        in.close();
        // printing result from response
        System.out.println("Response:-" + response.toString());
        return response.toString();
    }
}
