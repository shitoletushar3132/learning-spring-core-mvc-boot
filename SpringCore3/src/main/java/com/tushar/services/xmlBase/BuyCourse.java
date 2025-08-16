package com.tushar.services.xmlBase;

public class BuyCourse {
    private ICourse course;

    public BuyCourse() {
        System.out.println("bean will be created");
    }

    public BuyCourse(ICourse course) {
        System.out.println("this is constructor");
        this.course = course;
    }

    public void setCourse(ICourse course) {
        System.out.println("setter called");
        this.course = course;
    }

    public boolean buyTheCourse(double amount) {
        return course.getTheCourse(amount);
    }

    @Override
    public String toString() {
        return "BuyCourse{" + "course=" + course + '}';
    }
}
