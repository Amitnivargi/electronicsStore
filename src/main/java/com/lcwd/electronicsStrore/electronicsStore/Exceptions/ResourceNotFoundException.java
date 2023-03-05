package com.lcwd.electronicsStrore.electronicsStore.Exceptions;

import lombok.Builder;

@Builder
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super("Resource not found!!");
    }

    public ResourceNotFoundException(String messege) {
        super(messege);
    }
}
