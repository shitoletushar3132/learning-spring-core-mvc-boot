package com.tushar;

import com.tushar.services.InterTest;
import com.tushar.services.Java;

public class App {
    public static void main(String[] args) {
        InterTest test = new InterTest(); // target
        test.setIcourses(new Java()); // dependent // setter injection
        boolean status = test.buyTheCourse(234.24);
        if (status) {
            System.out.println("success");
        } else {
            System.out.println("failed");
        }
    }
}
