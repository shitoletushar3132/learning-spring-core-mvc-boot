package com.tushar.services;

public class Spring implements Icourses{

    @Override
    public boolean getTheCourse(double amount) {
        System.out.println("spring Course purchased successfully");
        return true;
    }
}
