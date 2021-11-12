package com.spotify.spotifyservice.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spotify.spotifyservice.domain.model.Album;
import com.spotify.spotifyservice.domain.model.Artist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackRequest {

    private Long id;
    @NotEmpty(message = "The 'name' field can not be empty")
    @NotNull(message = "The 'name' field can not be empty")
    @Size(min=3, max=30, message="The number of characters is not allowed")
    private String name;
    private Artist artist;
    private Album album;
    private Long reproduction;
    @NotNull (message = "The 'duration' field can not be empty")
    private Double duration;
}
