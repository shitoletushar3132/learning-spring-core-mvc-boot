package com.tushar.springBoot.service;

import org.springframework.stereotype.Service;

@Service
public class Greeting {
    public Greeting(){
        System.out.println("Greeting Bean is created");
    }

    public void call(){
        System.out.println("call in greeting");
    }

    
    
}
