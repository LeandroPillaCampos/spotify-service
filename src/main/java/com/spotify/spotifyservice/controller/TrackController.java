package com.spotify.spotifyservice.controller;

import com.spotify.spotifyservice.domain.model.Artist;
import com.spotify.spotifyservice.domain.model.Track;
import com.spotify.spotifyservice.repositories.TrackRepository;
import com.spotify.spotifyservice.service.ITrackService;
import com.spotify.spotifyservice.service.Impl.TrackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Slf4j
@RestController
@RequestMapping("/")
public class TrackController {

    @Autowired
    private TrackRepository TRepository;

    @Autowired
    private TrackService TService;

    @GetMapping("/tracks")
    public Iterable<Track> getAllTracks(){
        return TService.getTrackList();
    }
    /*
    @GetMapping(path = "/artist/{artistId}/songs/rank")
    public List<Track> topFive(@PathVariable("artistId")Long idArtist){
        return Arrays.asList(this.TService.getTrackList(idArtist));
    }
*/
    @GetMapping(path = "/spotify/play/track/{trackId}")
    public ResponseEntity<Track> getReproductions(@PathVariable("trackId")Long id){
        return TService.getTrack(id);
    }



    }


