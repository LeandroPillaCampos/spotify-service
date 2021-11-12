package com.spotify.spotifyservice.domain.mapper;

import com.spotify.spotifyservice.controller.request.TrackRequest;
import com.spotify.spotifyservice.domain.model.Track;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class TrackMapper implements Function<TrackRequest, Track> {
    @Override
    public Track apply(TrackRequest trackRequest) {
        return Track.builder()
                .id(trackRequest.getId())
                .name(trackRequest.getName())
                .album(trackRequest.getAlbum())
                .artist(trackRequest.getAlbum().getArtist())
                .reproduction(trackRequest.getReproduction())
                .duration(trackRequest.getDuration())
                .build();
    }
}