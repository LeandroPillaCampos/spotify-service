package com.spotify.spotifyservice.controller;

import com.spotify.spotifyservice.controller.request.ArtistRequest;
import com.spotify.spotifyservice.domain.model.Artist;
import com.spotify.spotifyservice.repositories.ArtistRepository;
import com.spotify.spotifyservice.service.Impl.ArtistService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/")
public class ArtistController {

    @Autowired
    private ArtistRepository aRepository;

    @Autowired
    private ArtistService aService;

    @GetMapping("/artist/rank")
    public ResponseEntity<List<Artist>> getTopFiveArtist(){
        return aService.getTopFiveArtist();
    }

    @PostMapping("/artists")
    public ResponseEntity<Artist> postArtist(@Validated @RequestBody ArtistRequest request){
        return aService.createArtist(request);
    }

    @PutMapping("/artist/{artistId}")
    public ResponseEntity<Artist> putArtist(@Validated @RequestBody ArtistRequest request, @PathVariable("artistId") Long idArtist){
        return aService.updateArtist(idArtist ,request);
    }

    @GetMapping("/artist/{artistId}")
    public ResponseEntity<Artist> getArtistID(@PathVariable("artistId") Long idArtist){
        return aService.getArtistID(idArtist);
    }

    @DeleteMapping("/artist/{artistId}")
    public ResponseEntity<Artist> deleteArtist(@PathVariable("artistId") Long idArtist){
        return aService.deleteArtist(idArtist);
    }

}
