package com.spotify.spotifyservice.controller.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@Data
public class ArtistRequest {

    private Long idArtist;
    @NotEmpty(message = "The 'name' field can not be empty")
    @NotNull(message = "The 'name' field can not be empty")
    @Size(min = 3, max = 100, message = "The number of characters is not allowed")
    private String name;
    @NotEmpty(message = "The 'genre' field can not be empty")
    @NotNull(message = "The 'genre' field can not be empty")
    @Size(min = 3, max = 30, message = "The number of characters is not allowed")
    private String genre;
    private String image;

}
