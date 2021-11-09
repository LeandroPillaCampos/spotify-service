package com.spotify.spotifyservice.service.Impl;

import com.spotify.spotifyservice.controller.request.ArtistRequest;
import com.spotify.spotifyservice.domain.mapper.ArtistMapper;
import com.spotify.spotifyservice.domain.model.Album;
import com.spotify.spotifyservice.domain.model.Artist;
import com.spotify.spotifyservice.domain.model.Track;
import com.spotify.spotifyservice.exception.ArtistExistException;
import com.spotify.spotifyservice.exception.ArtistNotExistException;
import com.spotify.spotifyservice.repositories.ArtistRepository;
import com.spotify.spotifyservice.repositories.TrackRepository;
import com.spotify.spotifyservice.service.IArtistService;
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
@Qualifier("artist")
public class ArtistService implements IArtistService {

    @Qualifier("artist")
    @Autowired
    private IArtistService aService;

    @Autowired
    private ArtistRepository aRepository;

    @Autowired
    private TrackRepository tRepository;

    @Autowired
    private ArtistMapper artistMapper;



    @Autowired
    private List<Artist> lArtist;

    @PostConstruct
    public void init() {
        lArtist.stream().forEach(track -> {
            //TrackMap.put(track.getId(), track);
            aRepository.save(track);
        });
    }



    @Override
    public ResponseEntity<List<Artist>> getTopFiveArtist() {

        List<Artist> TopFiveArtist = new ArrayList<>();
        Iterable<Track> AllTracks= tRepository.findAll();

        //Iterable to List
        List<Track> TReproductions=StreamSupport
                .stream(AllTracks.spliterator(), false)
                .collect(Collectors.toList());

        //Sorting TReproductions by idArtist and getReproduction
        TReproductions.sort(Comparator.comparing(Track::getReproduction).reversed());


        for(Track tracks: TReproductions){
            //If TopFiveArtist contains 5 elements exit loop
            if(TopFiveArtist.size()==5) break;

            Optional<Artist> optionalIDArtist= aRepository.findById(tracks.getIdArtist());

            //Check if idArtist exist into TopFiveArtist
            if(!(TopFiveArtist.stream().anyMatch(A ->A.getIdArtist().equals(optionalIDArtist.get().getIdArtist())))){

                TopFiveArtist.add(
                        Artist.builder().
                                idArtist(tracks.getIdArtist())
                                .name(aRepository.findById(tracks.getIdArtist()).get().getName())
                                .genre(aRepository.findById(tracks.getIdArtist()).get().getGenre())
                                .image(aRepository.findById(tracks.getIdArtist()).get().getImage())
                                .build()
                );
            }

        }

        return ResponseEntity.ok(TopFiveArtist);
    }

    @Override
    public ResponseEntity<Artist> getArtistID(Long idArtist) {

        Optional<Artist> optionalArtist= aRepository.findById(idArtist);
        if(optionalArtist.isPresent()){
            return ResponseEntity.ok(optionalArtist.get());

        }else return ResponseEntity.noContent().build();

    }

    @Override
    public ResponseEntity<Artist> createArtist(ArtistRequest request) {
        Artist artist = artistMapper.apply(request);
        if (request.getIdArtist() != null && aRepository.findById(request.getIdArtist()) != null) {
            log.error("Track already exists");
            throw new ArtistExistException("Track not exist");
        } else {
            aRepository.save(artistMapper.apply(request));
        }

        return ResponseEntity.ok(artist);
    }

    @Override
    public ResponseEntity<Artist> updateArtist(Long idArtist, ArtistRequest request) {
        Artist artist=new Artist();

        if (aRepository.findById(idArtist) != null) {
            artist= artistMapper.apply(request);
            aRepository.save(artist);

            return ResponseEntity.ok(artist);
        } else {
            log.error("Artist not exist");
            throw new ArtistNotExistException("Artist not exist");
        }
    }

    @Override
    public ResponseEntity<Artist> deleteArtist(Long idArtist) {
        if(aRepository.findById(idArtist)==null){
            return new ResponseEntity<Artist>(HttpStatus.NOT_FOUND);
        }else{
            aRepository.deleteById(idArtist);
            return new ResponseEntity<Artist>(HttpStatus.OK);
        }
    }

}
