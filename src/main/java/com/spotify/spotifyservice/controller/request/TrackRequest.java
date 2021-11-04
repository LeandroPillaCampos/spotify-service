package com.spotify.spotifyservice.controller.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TrackRequest {

    private Long id;
    private String name;
    private Long idArtist;
    private Long idAlbum;
    private Integer reproduction;
}
