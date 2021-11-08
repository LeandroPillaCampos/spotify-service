package com.spotify.spotifyservice.exception;

public class TrackNotExistException extends RuntimeException{

    public TrackNotExistException(String message){
        super(message);
    }
}
