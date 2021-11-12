package com.spotify.spotifyservice.service.Impl;

import com.spotify.spotifyservice.controller.request.ArtistRequest;
import com.spotify.spotifyservice.domain.mapper.ArtistMapper;
import com.spotify.spotifyservice.domain.model.Artist;
import com.spotify.spotifyservice.domain.model.Track;
import com.spotify.spotifyservice.exception.ArtistExistException;
import com.spotify.spotifyservice.exception.ArtistNotExistException;
import com.spotify.spotifyservice.repositories.ArtistRepository;
import com.spotify.spotifyservice.repositories.TrackRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
@Qualifier("artist")
public class ImplArtistService implements com.spotify.spotifyservice.service.ArtistService {

    @Qualifier("artist")
    @Autowired
    private com.spotify.spotifyservice.service.ArtistService aService;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private TrackRepository trackRepository;

    @Autowired
    private ArtistMapper artistMapper;

    @Autowired
    private List<Artist> artistList;

    @PostConstruct
    public void init() {
        artistList.stream().forEach(track -> {
            //TrackMap.put(track.getId(), track);
            artistRepository.save(track);
        });
    }


    @Override
    public ResponseEntity<List<Artist>> getTopFiveArtist() {

        List<Artist> TopFiveArtist = new ArrayList<>();
        Iterable<Track> AllTracks = trackRepository.findAll();

        List<Track> TReproductions = StreamSupport
                .stream(AllTracks.spliterator(), false)
                .collect(Collectors.toList());

        TReproductions.sort(Comparator.comparing(Track::getReproduction).reversed());

        return ResponseEntity.ok(TopFiveArtist);
    }

    @Override
    public ResponseEntity<Artist> getArtistID(Long idArtist) {

        Optional<Artist> optionalArtist = artistRepository.findById(idArtist);
        if (optionalArtist.isPresent()) {
            return ResponseEntity.ok(optionalArtist.get());
        } else return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Artist> createArtist(ArtistRequest request) {
        Artist artist = artistMapper.apply(request);
        if (request.getIdArtist() != null && artistRepository.findById(request.getIdArtist()) != null) {
            log.error("Track already exists");
            throw new ArtistExistException("Track not exist");
        } else {
            artistRepository.save(artistMapper.apply(request));
        }
        return ResponseEntity.ok(artist);
    }

    @Override
    public ResponseEntity<Artist> updateArtist(Long idArtist, ArtistRequest request) {
        Artist artist;

        if (artistRepository.findById(idArtist) != null) {
            artist = artistMapper.apply(request);
            artistRepository.save(artist);

            return ResponseEntity.ok(artist);
        } else {
            log.error("Artist not exist");
            throw new ArtistNotExistException("Artist not exist");
        }
    }

    @Override
    public ResponseEntity<Artist> deleteArtist(Long idArtist) {
        if (artistRepository.findById(idArtist) == null) {
            return ResponseEntity.noContent().build();
        } else {
            artistRepository.deleteById(idArtist);
            return ResponseEntity.ok().build();
        }
    }

    @Override
    public ResponseEntity<List<Track>> getTopFiveArtistTracks(Long idArtist) {

        Optional<Artist> optionalArtist = artistRepository.findById(idArtist);

        if (optionalArtist.isPresent()) {
            List<Track> topFive = new ArrayList<>();
            List<Track> allTracks = optionalArtist.get().getTrack();
            allTracks.sort(Comparator.comparing(Track::getReproduction).reversed());
            allTracks.stream().limit(5).forEach(topFive::add);

            return ResponseEntity.ok(topFive);
        } else return ResponseEntity.noContent().build();
    }

}
