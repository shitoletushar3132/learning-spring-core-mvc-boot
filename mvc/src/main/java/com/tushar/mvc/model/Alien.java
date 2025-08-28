package com.tushar.mvc.model;

public class Alien {
    private int aid;
    private String name;

    public int getAid() {
        return aid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    @Override
    public String toString() {
        return "Alien{" +
                "aid=" + aid +
                ", name='" + name + '\'' +
                '}';
    }
}
