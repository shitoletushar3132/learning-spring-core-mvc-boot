package com.tushar.services;

public class Password {
    String algo;

    public Password(String algo) {
        this.algo = algo;
        System.out.println("Password Bean created");
    }

    public String aboutAlgo() {
        return ("algo used is " + algo);
    }
}
