package com.tushar;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
// import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tushar.services.Password;

@ComponentScan(basePackages = { "com.tushar" })
public class App {

    // default bean name or id will we camel case of that class
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(App.class);
        Password pass = context.getBean(Password.class);
        System.out.println(pass.aboutAlgo());

        int count = context.getBeanDefinitionCount();
        System.out.println(count);

        String[] beanNames = context.getBeanDefinitionNames();
        for (String name : beanNames) {
            System.out.println(name);
        }
    }
}
