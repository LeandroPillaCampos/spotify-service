package com.spotify.spotifyservice.controller;

import com.spotify.spotifyservice.controller.request.TrackRequest;
import com.spotify.spotifyservice.domain.model.Track;
import com.spotify.spotifyservice.repositories.TrackRepository;
import com.spotify.spotifyservice.service.Impl.ArtistService;
import com.spotify.spotifyservice.service.Impl.TrackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/")
public class TrackController {

    @Autowired
    private TrackRepository TRepository;

    @Autowired
    private TrackService TService;

    @Autowired
    private ArtistService AService;

    @GetMapping(path = "/spotify/play/track/{trackId}")
    public ResponseEntity<Track> getReproductions(@PathVariable("trackId")Long id){
        return TService.getTrack(id);
    }

    @GetMapping("/artist/{artistId}/songs/rank")
    public ResponseEntity<List<Track>> getTopFiveTracks(@PathVariable("artistId") Long idArtist){
        return TService.getTopFiveTrack(idArtist);
    }

    @GetMapping("/track/rank")
    public ResponseEntity<List<Track>> getTopFivePopulars(){
        return TService.getTopFivePopulars();
    }

    @PostMapping("/track")
    public ResponseEntity<Track> postTrack(@Validated @RequestBody TrackRequest request){
        return TService.createTrack(request);
    }

    @PutMapping("/track/{trackId}")
    public ResponseEntity<Track> putTrack(@Validated @RequestBody TrackRequest request, @PathVariable("trackId") Long id){
        return TService.updateTrack(id ,request);
    }

    @GetMapping("/track/{trackId}")
    public ResponseEntity<Track> getTrackID(@PathVariable("trackId") Long id){
        return TService.getTrackID(id);
    }

    @DeleteMapping("/track/{trackId}")
        public ResponseEntity<Track> deleteTrack(@PathVariable("trackId") Long id){
            return TService.deleteTrack(id);
        }
    }




