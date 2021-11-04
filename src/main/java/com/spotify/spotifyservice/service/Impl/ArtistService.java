package com.spotify.spotifyservice.service.Impl;

import com.spotify.spotifyservice.controller.request.ArtistRequest;
import com.spotify.spotifyservice.domain.model.Artist;
import com.spotify.spotifyservice.service.IArtistService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService implements IArtistService {
    @Override
    public List<Artist> getArtisList() {
        return null;
    }

    @Override
    public Artist getArtist() {
        return null;
    }

    @Override
    public Artist createArtist(ArtistRequest request) {
        return null;
    }

    @Override
    public void updateArtist(Long idArtist) {

    }
}
