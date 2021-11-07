package com.spotify.spotifyservice.controller;

import com.spotify.spotifyservice.domain.model.Artist;
import com.spotify.spotifyservice.domain.model.Track;
import com.spotify.spotifyservice.repositories.ArtistRepository;
import com.spotify.spotifyservice.service.IArtistService;
import com.spotify.spotifyservice.service.Impl.ArtistService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/")
public class ArtistController {

    @Autowired
    private ArtistRepository ARepository;


    @Autowired
    @Qualifier("artist")
    private ArtistService AService;

    @GetMapping("/list")
    public List<Artist> retriveArtist() {
        return AService.getArtisList();
    }
    /*
    @GetMapping("/artist/{artistId}/songs/rank")
    public ResponseEntity<Artist> getTopFive(@PathVariable("artistId") Long idArtist){
        return AService.getArtist(idArtist);
    }
     */
    @GetMapping("/artist/rank")
    public ResponseEntity<List<Artist>> getTopFiveArtist(){
        return AService.getTopFiveArtist();
    }



}
