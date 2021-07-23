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

        for (int i=1;i<=49;++i)
            System.out.println("insert into privilege_action (privilege_id,action_id) values (5,"+i+");");
        /*
        System.out.println(StaticNames.gender.MALE);
//        encoder.matches()

        System.out.println(encoder.matches("test","$2a$10$5JLyeE01xeGC4EBqAlR0ru7hB/RBlhSSzjBrxt8aV5LO9xYWe/0rW"));*/
        System.out.println(validate("Resala@resala.cccc"));
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pass = "test";
        System.out.println(encoder.encode(pass));



    }
}
