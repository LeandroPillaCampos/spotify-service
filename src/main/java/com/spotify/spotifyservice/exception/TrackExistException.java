package com.spotify.spotifyservice.exception;

public class TrackExistException extends RuntimeException{

    public TrackExistException(String message){
        super(message);
    }
}
