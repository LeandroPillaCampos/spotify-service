package com.spotify.spotifyservice.domain.mapper;

import com.spotify.spotifyservice.controller.request.AlbumRequest;
import com.spotify.spotifyservice.domain.model.Album;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class AlbumMapper implements Function<AlbumRequest, Album> {
    @Override
    public Album apply(AlbumRequest albumRequest) {
        return Album.builder()
                .idAlbum(albumRequest.getIdAlbum())
                .name(albumRequest.getName())
                .idArtist(albumRequest.getIdArtist())
                .build();
    }
}
