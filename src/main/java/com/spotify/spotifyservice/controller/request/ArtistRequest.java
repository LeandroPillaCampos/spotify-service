package com.spotify.spotifyservice.controller.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ArtistRequest {

    private Long idArtist;
    private String name;
    private String genre;
    private String image;

}
