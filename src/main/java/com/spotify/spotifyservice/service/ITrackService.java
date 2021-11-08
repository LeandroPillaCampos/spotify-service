package com.spotify.spotifyservice.service;

import com.spotify.spotifyservice.controller.request.ArtistRequest;
import com.spotify.spotifyservice.controller.request.TrackRequest;
import com.spotify.spotifyservice.domain.model.Album;
import com.spotify.spotifyservice.domain.model.Artist;
import com.spotify.spotifyservice.domain.model.Track;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ITrackService extends IAlbumService {

    Iterable<Track> getTrackList();
    ResponseEntity<List<Track>> getTopFiveTrack(Long id);
    ResponseEntity<List<Track>> getTopFivePopulars();
    ResponseEntity<Track> getTrack(Long id);
    ResponseEntity<Track> createTrack(TrackRequest request);
    void updateTrack (Long id);


}
