package com.spotify.spotifyservice.service;

import com.spotify.spotifyservice.controller.request.ArtistRequest;
import com.spotify.spotifyservice.controller.request.TrackRequest;
import com.spotify.spotifyservice.domain.model.Album;
import com.spotify.spotifyservice.domain.model.Artist;
import com.spotify.spotifyservice.domain.model.Track;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ITrackService extends IAlbumService {

    List<Track> getTrackList();
    Track getTrack();
    Track createTrack(TrackRequest request);
    void updateTrack (Long id);


}
