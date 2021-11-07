package com.spotify.spotifyservice.service;

import com.spotify.spotifyservice.controller.request.AlbumRequest;
import com.spotify.spotifyservice.controller.request.ArtistRequest;
import com.spotify.spotifyservice.domain.model.Album;
import com.spotify.spotifyservice.domain.model.Artist;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IArtistService {

    List<Artist> getArtisList();
    ResponseEntity<Artist> getArtist(Long idArtist);
    ResponseEntity<List<Artist>> getTopFive(Long idArtist);
    Artist createArtist(ArtistRequest request);
    void updateArtist (Long idArtist);

}
