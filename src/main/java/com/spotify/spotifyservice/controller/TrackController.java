package com.spotify.spotifyservice.controller;

import com.spotify.spotifyservice.domain.model.Track;
import com.spotify.spotifyservice.service.ITrackService;
import com.spotify.spotifyservice.service.Impl.TrackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/spotify/play/track/")
public class TrackController {

    @Autowired
    ITrackService TService;

    @GetMapping("/{id}")
    public Track retriveTrack(@PathVariable Long id) {
        log.info("id", id);
        return this.TService.getTrack();
    }

}
