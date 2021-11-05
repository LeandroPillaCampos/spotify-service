package com.spotify.spotifyservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Track {

    private Long id;
    private String name;
    private Long idArtist;
    private Long idAlbum;
    private Long reproduction;
    private Double duration;


}
