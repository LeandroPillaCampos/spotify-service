package com.spotify.spotifyservice.exception;

public class AlbumNotExistException extends RuntimeException{

    public AlbumNotExistException(String message){
        super(message);
    }
}
