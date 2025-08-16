package com.tushar;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tushar.services.Password;

public class App {

    // default bean name or id will we camel case of that class
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationconfig.xml");
        Password pass =context.getBean(Password.class);
        System.out.println(pass.aboutAlgo());
    }
}
