package com.autowire;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("autoConfig.xml");

        Employee e1 = context.getBean("emp",Employee.class);
        System.out.println(e1);

    }
}
