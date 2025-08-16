package com.springlifecycle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("samosaConfig.xml");
        Samosa s = (Samosa) context.getBean("samosa1");
        System.out.println(s);
        context.registerShutdownHook();
    }
}
