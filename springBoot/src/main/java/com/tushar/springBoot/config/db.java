package com.tushar.springBoot.config;

public class db {
    String url;


    public db(){

    }

    public db(String url){
        this.url = url;
    }


    public void connnect() {
        System.out.println("connected");
    }

}
