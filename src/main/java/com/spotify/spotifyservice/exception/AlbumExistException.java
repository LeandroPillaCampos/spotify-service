package com.spotify.spotifyservice.exception;

public class AlbumExistException extends RuntimeException{

    public AlbumExistException(String message){
        super(message);
    }
}
