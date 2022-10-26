package com.MoreiraJunior.cloud.temafinal2.appservice.controller;

import com.MoreiraJunior.cloud.temafinal2.appservice.exceptions.PlaylistServiceNotFoundException;
import com.MoreiraJunior.cloud.temafinal2.appservice.exceptions.SongServiceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PlaylistServiceNotFoundException.class)
    public ResponseEntity<Object> playlistMicroserviceNotFound(){
        String exceptionMessage = new PlaylistServiceNotFoundException().getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionMessage);
    }

    @ExceptionHandler(SongServiceNotFoundException.class)
    public ResponseEntity<Object> songMicroserviceNotFound(){
        String exceptionMessage = new SongServiceNotFoundException().getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionMessage);
    }
}
