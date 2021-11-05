package com.spotify.spotifyservice.service.Impl;

import com.spotify.spotifyservice.controller.ArtistController;
import com.spotify.spotifyservice.controller.request.ArtistRequest;
import com.spotify.spotifyservice.domain.model.Artist;
import com.spotify.spotifyservice.service.IArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("artist")
public class ArtistService implements IArtistService {

    @Autowired
    //@Qualifier("artist")
    private List<Artist> artists;

    @Override
    public List<Artist> getArtisList() {
        return artists;
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
