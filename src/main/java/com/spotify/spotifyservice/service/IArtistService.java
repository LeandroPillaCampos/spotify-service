package com.spotify.spotifyservice.service;

import com.spotify.spotifyservice.controller.request.AlbumRequest;
import com.spotify.spotifyservice.controller.request.ArtistRequest;
import com.spotify.spotifyservice.controller.request.TrackRequest;
import com.spotify.spotifyservice.domain.model.Album;
import com.spotify.spotifyservice.domain.model.Artist;
import com.spotify.spotifyservice.domain.model.Track;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IArtistService {

    ResponseEntity<List<Artist>> getTopFiveArtist();
    ResponseEntity<Artist> getArtistID(Long idArtist);
    ResponseEntity<Artist> createArtist(ArtistRequest request);
    ResponseEntity<Artist> updateArtist (Long idArtist, ArtistRequest request);
    ResponseEntity<Artist> deleteArtist(Long idArtist);


}
