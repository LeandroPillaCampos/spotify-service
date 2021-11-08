package com.spotify.spotifyservice.controller;


import com.spotify.spotifyservice.domain.model.Artist;
import com.spotify.spotifyservice.domain.model.Track;
import com.spotify.spotifyservice.exception.AlbumExistException;
import com.spotify.spotifyservice.exception.ArtistExistException;
import com.spotify.spotifyservice.exception.TrackExistException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@ResponseBody
@ControllerAdvice
public class AdviceController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handlerValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({TrackExistException.class,ArtistExistException.class, AlbumExistException.class})
    public Map<String, String> handlerValidationException(TrackExistException ex) {
        Map<String, String> errors = new HashMap<>();
        String fieldName = "Error: ";
        errors.put(fieldName, ex.getMessage());
        return errors;
    }




}
