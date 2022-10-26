package com.MoreiraJunior.cloud.temafinal2.appservice.exceptions;

public class SongServiceNotFoundException extends RuntimeException{

    private String message = "The song microservice was not found!";

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
