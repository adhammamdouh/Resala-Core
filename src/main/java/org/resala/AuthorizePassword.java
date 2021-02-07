package org.resala;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AuthorizePassword {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pass = "test";
        System.out.println(encoder.encode(pass));
    }
}
