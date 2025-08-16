package com.tushar.services.xmlBase;

public class Java implements ICourse {

    @Override
    public boolean getTheCourse(double price) {
        System.out.println("You buy a java course successfully");
        return true;
    }
}
