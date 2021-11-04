package com.spotify.spotifyservice.controller.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AlbumRequest {

    private  Long idAlbum;
    private Long idArtist;
    private String name;
}
