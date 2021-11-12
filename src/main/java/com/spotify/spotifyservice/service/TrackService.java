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
public interface TrackService {

    ResponseEntity<Track> getTrackID(Long id);
    ResponseEntity<List<Track>> getTopFivePopularTracks();
    ResponseEntity<Track> getIncreaseTrack(Long id);
    ResponseEntity<Track> createTrack(TrackRequest request);
    ResponseEntity<Track> updateTrack (Long id , TrackRequest request);
    ResponseEntity<Track> deleteTrack(Long id);

}
