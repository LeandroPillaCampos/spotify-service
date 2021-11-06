package com.spotify.spotifyservice.service;

import com.spotify.spotifyservice.controller.request.AlbumRequest;
import com.spotify.spotifyservice.controller.request.ArtistRequest;
import com.spotify.spotifyservice.domain.model.Album;
import com.spotify.spotifyservice.domain.model.Artist;

import java.util.List;

public interface IArtistService {

    List<Artist> getArtisList();
    Artist getArtist(Long idArtist);
    Artist createArtist(ArtistRequest request);
    void updateArtist (Long idArtist);

}
