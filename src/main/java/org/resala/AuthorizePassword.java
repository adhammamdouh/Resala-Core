package org.resala;

import org.resala.Models.Volunteer.Volunteer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AuthorizePassword {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pass = "test";
        System.out.println(encoder.encode(pass));



        Map<Integer,Integer> map = new HashMap<>();
        map.put(1,1);
        map.put(2,2);
        map.put(3,3);


        Map<Integer,Integer> sortedMap = new HashMap<>();
        map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));

        Iterator<Map.Entry<Integer, Integer>> itr = sortedMap.entrySet().iterator();
        Map.Entry<Integer, Integer> entry=itr.next();

        System.out.println(entry.getValue());
        map.put(1,4);
        map.put(2,5);
        map.put(3,6);
        System.out.println(entry.getValue());
    }
}
