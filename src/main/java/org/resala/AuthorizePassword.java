package org.resala;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthorizePassword {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[\\w]+@[\\w]+\\.[A-Za-z]{2,3}$", Pattern.CASE_INSENSITIVE);
    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }
    public static void main(String[] args) {

        for (int i=1;i<=50;++i)
            System.out.println("insert into privilege_action (privilege_id,action_id) values (7,"+i+");");

        System.out.println(validate("Resala@resala.cccc"));
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pass = "test";
        System.out.println(encoder.encode(pass));



    }
}
