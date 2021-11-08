package com.spotify.spotifyservice.service.Impl;

import com.spotify.spotifyservice.controller.ArtistController;
import com.spotify.spotifyservice.controller.request.ArtistRequest;
import com.spotify.spotifyservice.domain.mapper.ArtistMapper;
import com.spotify.spotifyservice.domain.model.Artist;
import com.spotify.spotifyservice.domain.model.Track;
import com.spotify.spotifyservice.exception.ArtistExistException;
import com.spotify.spotifyservice.exception.ArtistNotExistException;
import com.spotify.spotifyservice.exception.TrackExistException;
import com.spotify.spotifyservice.exception.TrackNotExistException;
import com.spotify.spotifyservice.repositories.ArtistRepository;
import com.spotify.spotifyservice.repositories.TrackRepository;
import com.spotify.spotifyservice.service.IArtistService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
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
    private IArtistService AService;

    @Autowired
    private ArtistRepository ARepository;

    @Autowired
    private TrackRepository TRepository;

    @Autowired
    private ArtistMapper artistMapper;



    @Autowired
    private List<Artist> LArtist;

    @PostConstruct
    public void init() {
        LArtist.stream().forEach(track -> {
            //TrackMap.put(track.getId(), track);
            ARepository.save(track);
        });
    }



    @Override
    public ResponseEntity<List<Artist>> getTopFiveArtist() {

        List<Artist> TopFiveArtist = new ArrayList<>();
        Iterable<Track> AllTracks=TRepository.findAll();

        //Iterable to List
        List<Track> TReproductions=StreamSupport
                .stream(AllTracks.spliterator(), false)
                .collect(Collectors.toList());

        //Sorting TReproductions by idArtist and getReproduction
        TReproductions.sort(Comparator.comparing(Track::getReproduction).reversed());


        for(Track tracks: TReproductions){
            //If TopFiveArtist contains 5 elements exit loop
            if(TopFiveArtist.size()==5) break;

            Optional<Artist> optionalIDArtist=ARepository.findById(tracks.getIdArtist());

            //Check if idArtist exist into TopFiveArtist
            if(!(TopFiveArtist.stream().anyMatch(A ->A.getIdArtist().equals(optionalIDArtist.get().getIdArtist())))){

                TopFiveArtist.add(
                        Artist.builder().
                                idArtist(tracks.getIdArtist())
                                .name(ARepository.findById(tracks.getIdArtist()).get().getName())
                                .genre(ARepository.findById(tracks.getIdArtist()).get().getGenre())
                                .image(ARepository.findById(tracks.getIdArtist()).get().getImage())
                                .build()
                );
            }

        }

        return ResponseEntity.ok(TopFiveArtist);
    }

    @Override
    public ResponseEntity<Artist> getArtistID(Long idArtist) {

        Optional<Artist> optionalArtist=ARepository.findById(idArtist);
        if(optionalArtist.isPresent()){
            return ResponseEntity.ok(optionalArtist.get());

        }else return ResponseEntity.noContent().build();

    }

    @Override
    public ResponseEntity<Artist> createArtist(ArtistRequest request) {
        Artist artist = artistMapper.apply(request);
        if (request.getIdArtist() != null && ARepository.findById(request.getIdArtist()) != null) {
            log.error("Track already exists");
            throw new ArtistExistException("Track not exist");
        } else {
            ARepository.save(artistMapper.apply(request));
        }

        return ResponseEntity.ok(artist);
    }

    @Override
    public ResponseEntity<Artist> updateArtist(Long idArtist, ArtistRequest request) {
        Artist artist=new Artist();

        if (ARepository.findById(idArtist) != null) {
            artist= artistMapper.apply(request);
            ARepository.save(artist);

            return ResponseEntity.ok(artist);
        } else {
            log.error("Artist not exist");
            throw new ArtistNotExistException("Artist not exist");
        }
    }

    @Override
    public ResponseEntity<Artist> deleteArtist(Long idArtist) {
        ARepository.deleteById(idArtist);
        return null;
    }

}
