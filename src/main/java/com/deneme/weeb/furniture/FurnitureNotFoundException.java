package com.deneme.weeb.furniture;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FurnitureNotFoundException extends RuntimeException {
    public FurnitureNotFoundException() {
        super("Furniture not found");
    }
}
