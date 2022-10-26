package com.MoreiraJunior.cloud.temafinal2.appservice.exceptions;

public class PlaylistServiceNotFoundException extends RuntimeException{

    private String message = "The playlist microservice was not found!";

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
