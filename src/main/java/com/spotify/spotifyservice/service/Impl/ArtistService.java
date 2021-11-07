package com.spotify.spotifyservice.service.Impl;

import com.spotify.spotifyservice.controller.ArtistController;
import com.spotify.spotifyservice.controller.request.ArtistRequest;
import com.spotify.spotifyservice.domain.model.Artist;
import com.spotify.spotifyservice.domain.model.Track;
import com.spotify.spotifyservice.repositories.ArtistRepository;
import com.spotify.spotifyservice.service.IArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Qualifier("artist")
public class ArtistService implements IArtistService {

    @Autowired
    //@Qualifier("artist")
    private List<Artist> artists;

    @Autowired
    private ArtistRepository ARepository;

    @Override
    public List<Artist> getArtisList() {
        return artists;
    }


    @Override
    public ResponseEntity<Artist> getArtist(Long idArtist) {
    /*
        Optional<Artist> optionalArtist=ARepository.findById(idArtist);
        optionalArtist.
        if(optionalArtist.isPresent()){
            ResponseEntity.ok();
        }else return ResponseEntity.noContent().build();
    */
        return null;
    }

    @Override
    public ResponseEntity<List<Artist>> getTopFive(Long idArtist) {
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
