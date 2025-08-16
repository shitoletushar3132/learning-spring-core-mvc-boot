package com.tushar.services;

public class InterTest {

    private Icourses icourses;

    public void setIcourses(Icourses icourses) {
        this.icourses = icourses;
    }

    public boolean buyTheCourse(Double amount) {
        return icourses.getTheCourse(amount);
    }
}
