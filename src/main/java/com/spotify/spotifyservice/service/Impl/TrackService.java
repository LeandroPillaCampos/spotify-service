package com.spotify.spotifyservice.service.Impl;

import com.spotify.spotifyservice.controller.request.TrackRequest;
import com.spotify.spotifyservice.domain.model.Track;
import com.spotify.spotifyservice.service.ITrackService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackService  implements ITrackService {
    @Override
    public List<Track> getTrackList() {
        return null;
    }

    @Override
    public Track getTrack() {
        return null;
    }

    @Override
    public Track createTrack(TrackRequest request) {
        return null;
    }

    @Override
    public void updateTrack(Long id) {

    }
}
