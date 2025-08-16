package com.tushar.services.xmlBase;

public class SpringBoot implements ICourse {

    @Override
    public boolean getTheCourse(double price) {
        System.out.println("You buy a spring boot course successfully");
        return true;
    }
}
