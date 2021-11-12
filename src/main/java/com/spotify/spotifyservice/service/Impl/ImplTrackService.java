package com.spotify.spotifyservice.service.Impl;

import com.spotify.spotifyservice.controller.request.TrackRequest;
import com.spotify.spotifyservice.domain.mapper.TrackMapper;
import com.spotify.spotifyservice.domain.model.Track;
import com.spotify.spotifyservice.exception.TrackExistException;
import com.spotify.spotifyservice.exception.TrackNotExistException;
import com.spotify.spotifyservice.repositories.TrackRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
@Qualifier ("track")
public class ImplTrackService implements com.spotify.spotifyservice.service.TrackService {

    @Qualifier("track")
    @Autowired
    private com.spotify.spotifyservice.service.TrackService trackService;

    @Autowired
    private List<Track> trackList;

    @Autowired
    private TrackRepository trackRepository;

    @Autowired
    private TrackMapper trackMapper;

    @PostConstruct
    public void init() {
        trackList.stream().forEach(track -> {
            trackRepository.save(track);
        });
    }

    @Override
    public ResponseEntity<Track> getTrackID(Long id) {
        Optional<Track> optionaTrack= trackRepository.findById(id);
        if(optionaTrack.isPresent()){
            return ResponseEntity.ok(optionaTrack.get());
        }else return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<Track>> getTopFivePopularTracks() {

        Iterable<Track> allTracks= trackRepository.findAll();
        List<Track> topFive=new ArrayList<>();

        List<Track> popularsTracks= StreamSupport
                .stream(allTracks.spliterator(), false)
                .collect(Collectors.toList());

        popularsTracks.sort(Comparator.comparing(Track::getReproduction).reversed());

        popularsTracks.stream().limit(5).forEach(topFive::add);

        return ResponseEntity.ok(topFive);
    }

    @Override
    public ResponseEntity<Track> getIncreaseTrack(Long id) {

        Optional<Track> optionalTrack= trackRepository.findById(id);

        if(optionalTrack.isPresent()){
            Track countTrack= optionalTrack.get();
            countTrack.setReproduction(optionalTrack.get().getReproduction()+1);
            trackRepository.save(countTrack);

            return ResponseEntity.ok(countTrack);
        }else return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Track> createTrack(TrackRequest request) {
        Track track = trackMapper.apply(request);
        if (request.getId() != null && trackRepository.findById(request.getId()) != null) {
            log.error("Track already exists");
            throw new TrackExistException("Track not exist");

        } else {
            trackRepository.save(track);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }
    }

    @Override
    public ResponseEntity<Track> updateTrack(Long id, TrackRequest request) {

        Track track;

        if (trackRepository.findById(id) != null) {
           track= trackMapper.apply(request);
            trackRepository.save(track);

            return ResponseEntity.ok(track);
        } else {
            log.error("Track not exist");
            throw new TrackNotExistException("Track not exist");
        }
    }

    @Override
    public ResponseEntity<Track> deleteTrack(Long id) {

        if(trackRepository.findById(id)==null){
            return ResponseEntity.noContent().build();
        }else{
            trackRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
    }
}
