package com.spotify.spotifyservice.domain.mapper;

import com.spotify.spotifyservice.controller.request.TrackRequest;
import com.spotify.spotifyservice.domain.model.Track;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class TrackMapper implements Function<TrackRequest, Track> {


    @Override
    public Track apply(TrackRequest trackRequest) {
        return Track.builder()
                .id(trackRequest.getId())
                .name(trackRequest.getName())
                .idAlbum(trackRequest.getIdAlbum())
                .idArtist(trackRequest.getIdArtist())
                .reproduction(trackRequest.getReproduction())
                .duration(trackRequest.getDuration())
                .build();
    }
}