package com.spotify.spotifyservice.controller;

import com.spotify.spotifyservice.controller.request.TrackRequest;
import com.spotify.spotifyservice.domain.model.Track;
import com.spotify.spotifyservice.repositories.TrackRepository;
import com.spotify.spotifyservice.service.Impl.ImplTrackService;
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
    private TrackRepository trackRepository;

    @Autowired
    private ImplTrackService trackService;

    @GetMapping(path = "/spotify/play/track/{trackId}")
    public ResponseEntity<Track> getReproductions(@PathVariable("trackId") Long id) {
        return trackService.getIncreaseTrack(id);
    }

    @GetMapping("/track/rank")
    public ResponseEntity<List<Track>> getTopFivePopularTracks() {
        return trackService.getTopFivePopularTracks();
    }

    @PostMapping("/track")
    public ResponseEntity<Track> postTrack(@Validated @RequestBody TrackRequest request) {
        return trackService.createTrack(request);
    }

    @PutMapping("/track/{trackId}")
    public ResponseEntity<Track> putTrack(@Validated @RequestBody TrackRequest request, @PathVariable("trackId") Long id) {
        return trackService.updateTrack(id, request);
    }

    @GetMapping("/track/{trackId}")
    public ResponseEntity<Track> getTrackID(@PathVariable("trackId") Long id) {
        return trackService.getTrackID(id);
    }

    @DeleteMapping("/track/{trackId}")
    public ResponseEntity<Track> deleteTrack(@PathVariable("trackId") Long id) {
        return trackService.deleteTrack(id);
    }
}




