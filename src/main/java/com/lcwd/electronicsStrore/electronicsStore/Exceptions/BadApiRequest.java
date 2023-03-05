package com.lcwd.electronicsStrore.electronicsStore.Exceptions;

public class BadApiRequest extends RuntimeException{

    public BadApiRequest(String messege){
        super(messege);
    }
    public BadApiRequest() {
        super("Bad Api request");
    }
}
