package org.resala;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AuthorizePassword {


    public static void main(String[] args) {
        for (int i=1;i<=46;++i)
            System.out.println("insert into privilege_action (privilege_id,action_id) values (5,"+i+");");

        System.out.println(StaticNames.gender.MALE);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        encoder.matches()
        String pass = "test";
        System.out.println(encoder.encode(pass));

        System.out.println(encoder.matches("test","$2a$10$5JLyeE01xeGC4EBqAlR0ru7hB/RBlhSSzjBrxt8aV5LO9xYWe/0rW"));

    }
}
