package com.spotify.spotifyservice.exception;

public class ArtistExistException extends RuntimeException{

    public ArtistExistException(String message){
        super(message);
    }
}
