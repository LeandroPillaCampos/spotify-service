package com.spotify.spotifyservice.service.Impl;

import com.spotify.spotifyservice.controller.request.TrackRequest;
import com.spotify.spotifyservice.domain.mapper.TrackMapper;
import com.spotify.spotifyservice.domain.model.Artist;
import com.spotify.spotifyservice.domain.model.Track;
import com.spotify.spotifyservice.exception.TrackExistException;
import com.spotify.spotifyservice.exception.TrackNotExistException;
import com.spotify.spotifyservice.repositories.TrackRepository;
import com.spotify.spotifyservice.service.ITrackService;
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
public class TrackService  implements ITrackService{

    @Qualifier("track")
    @Autowired
    private ITrackService tService;

    @Autowired
    private List<Track> lTrack;

    @Autowired
    private TrackRepository tRepository;

    @Autowired
    private TrackMapper trackMapper;

    @PostConstruct
    public void init() {
        lTrack.stream().forEach(track -> {
            tRepository.save(track);
        });
    }

    @Override
    public ResponseEntity<Track> getTrackID(Long id) {
        Optional<Track> optionaTrack= tRepository.findById(id);
        if(optionaTrack.isPresent()){
            return ResponseEntity.ok(optionaTrack.get());
        }else return ResponseEntity.noContent().build();

    }

    @Override
    public ResponseEntity<List<Track>> getTopFiveTrack(Long id) {
        Iterable<Track> AllTracks= tRepository.findAll();
        List<Track> TracksforArtist=new ArrayList<>();
        for(Track track: AllTracks){
            if(track.getIdArtist().equals(id)){
                TracksforArtist.add(
                        Track.builder().id(track.getId()).idArtist(track.getIdArtist()).idAlbum(track.getIdAlbum()).name(track.getName()).reproduction(track.getReproduction()).duration(track.getDuration()).build()
                );
            }
        }

        TracksforArtist.sort(Comparator.comparing(Track::getReproduction).reversed());

        return ResponseEntity.ok(TracksforArtist);
    }

    @Override
    public ResponseEntity<List<Track>> getTopFivePopulars() {

        Iterable<Track> AllTracks= tRepository.findAll();

        //Iterable to List
        List<Track> PopularsTracks= StreamSupport
                .stream(AllTracks.spliterator(), false)
                .collect(Collectors.toList());

        //Sort by getReproduction
        PopularsTracks.sort(Comparator.comparing(Track::getReproduction).reversed());

        //Truncate PopularsTracks by 5
        PopularsTracks.stream().limit(5);

        return ResponseEntity.ok(PopularsTracks);
    }

    @Override
    public ResponseEntity<Track> getTrack(Long id) {

        Optional<Track> optionalTrack= tRepository.findById(id);

        if(optionalTrack.isPresent()){
            Track countTrack= optionalTrack.get();
            countTrack.setReproduction(optionalTrack.get().getReproduction()+1);
            tRepository.save(countTrack);

            return ResponseEntity.ok(countTrack);
        }else return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Track> createTrack(TrackRequest request) {
        Track track = trackMapper.apply(request);
        if (request.getId() != null && tRepository.findById(request.getId()) != null) {
            log.error("Track already exists");
            throw new TrackExistException("Track not exist");
        } else {
            tRepository.save(trackMapper.apply(request));
        }

        return ResponseEntity.ok(track);
    }

    @Override
    public ResponseEntity<Track> updateTrack(Long id, TrackRequest request) {

        Track track=new Track();

        if (tRepository.findById(id) != null) {
           track= trackMapper.apply(request);
            tRepository.save(track);

            return ResponseEntity.ok(track);
        } else {
            log.error("Track not exist");
            throw new TrackNotExistException("Track not exist");
        }

    }

    @Override
    public ResponseEntity<Track> deleteTrack(Long id) {

        if(tRepository.findById(id)==null){
            return new ResponseEntity<Track>(HttpStatus.NOT_FOUND);
        }else{
            tRepository.deleteById(id);
            return new ResponseEntity<Track>(HttpStatus.OK);
        }
    }



    


}
