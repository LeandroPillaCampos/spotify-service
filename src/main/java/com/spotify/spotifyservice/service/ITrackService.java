package com.spotify.spotifyservice.service;

import com.spotify.spotifyservice.controller.request.ArtistRequest;
import com.spotify.spotifyservice.controller.request.TrackRequest;
import com.spotify.spotifyservice.domain.model.Album;
import com.spotify.spotifyservice.domain.model.Artist;
import com.spotify.spotifyservice.domain.model.Track;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ITrackService extends IAlbumService {

    List<Track> getTrackList();
    List<Track> getTList(Long id);
    Track getTrackList(Long id);
    Track getTrack(Long id);
    Track createTrack(TrackRequest request);
    void updateTrack (Long id);


}
