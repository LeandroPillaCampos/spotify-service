package com.spotify.spotifyservice.controller.request;

import com.spotify.spotifyservice.domain.model.Artist;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@Data
public class AlbumRequest {

    private Long idAlbum;
    private Artist artist;
    @NotEmpty(message = "The 'name' field can not be empty")
    @NotNull(message = "The 'name' field can not be empty")
    @Size(min = 3, max = 100, message = "The number of characters is not allowed")
    private String name;
}
