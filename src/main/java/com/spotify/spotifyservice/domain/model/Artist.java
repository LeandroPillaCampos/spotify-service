package com.spotify.spotifyservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Artist {

    private Long idArtist;
    private String name;
    private String genre;
    private String image;

}
