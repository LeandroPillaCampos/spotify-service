package com.spotify.spotifyservice.service.Impl;

import com.spotify.spotifyservice.controller.ArtistController;
import com.spotify.spotifyservice.controller.request.ArtistRequest;
import com.spotify.spotifyservice.domain.model.Artist;
import com.spotify.spotifyservice.domain.model.Track;
import com.spotify.spotifyservice.repositories.ArtistRepository;
import com.spotify.spotifyservice.repositories.TrackRepository;
import com.spotify.spotifyservice.service.IArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Qualifier("artist")
public class ArtistService implements IArtistService {

    @Qualifier("artist")
    @Autowired
    private IArtistService AService;

    @Autowired
    //@Qualifier("artist")
    private List<Artist> artists;

    @Autowired
    private ArtistRepository ARepository;

    @Autowired
    private TrackRepository TRepository;

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
    public List<Artist> getArtisList() {
        return artists;
    }


    @Override
    public ResponseEntity<Artist> getArtist(Long idArtist) {
    /*
        Optional<Artist> optionalArtist=ARepository.findById(idArtist);
        optionalArtist.
        if(optionalArtist.isPresent()){
            ResponseEntity.ok();
        }else return ResponseEntity.noContent().build();
    */

        return null;
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
    public Artist createArtist(ArtistRequest request) {
        return null;
    }

    @Override
    public void updateArtist(Long idArtist) {



    }
}
