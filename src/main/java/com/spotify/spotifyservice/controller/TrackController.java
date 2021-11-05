package com.spotify.spotifyservice.controller;

import com.spotify.spotifyservice.domain.model.Artist;
import com.spotify.spotifyservice.domain.model.Track;
import com.spotify.spotifyservice.service.ITrackService;
import com.spotify.spotifyservice.service.Impl.TrackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/")
public class TrackController {

    @Autowired
    @Qualifier("track")
    TrackService TService;

    @GetMapping(path = "/artist/{artistId}/songs/rank")
    public List<Track> topFive(@PathVariable("artistId")Long idArtist){
        return (List<Track>) this.TService.getTrackList(idArtist);
    }

    @GetMapping(path = "/spotify/play/track/{trackId}")
    public Track getReproductions(@PathVariable("trackId")Long id){
        return this.TService.getTrackList(id);
    }

    }


