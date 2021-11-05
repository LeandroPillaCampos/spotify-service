package com.spotify.spotifyservice.service.Impl;

import com.spotify.spotifyservice.controller.request.AlbumRequest;
import com.spotify.spotifyservice.controller.request.ArtistRequest;
import com.spotify.spotifyservice.controller.request.TrackRequest;
import com.spotify.spotifyservice.domain.model.Album;
import com.spotify.spotifyservice.domain.model.Artist;
import com.spotify.spotifyservice.domain.model.Track;
import com.spotify.spotifyservice.service.ITrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@Qualifier ("track")
public class TrackService  implements ITrackService {

    @Autowired
    private ITrackService TService;

    @Qualifier("track")
    @Autowired
    private List<Track> Ltrack;


    @PostConstruct
    public void init() {
        TrackMap = new TreeMap<>();
        Ltrack.stream().forEach(track -> {
            TrackMap.put(track.getId(), track);
        });
    }

    private Map<Long, Track> TrackMap;

    @Override
    public List<Track> getTrackList() {
        return Ltrack;
    }

    @Override
    public List<Track> getTList(Long id) {
        List<Track> topFive = new ArrayList<>();

        topFive.add(
        Track.builder()
                .idArtist(id).name(TrackMap.get(id).getName())
                .reproduction(TrackMap.get(id).getReproduction()).build()
        );

        return topFive;
    }

    @Override
    public Track getTrackList(Long id) {

        TrackMap.get(id).setReproduction(TrackMap.get(id).getReproduction()+1);

        return TrackMap.get(id);
    }

    @Override
    public Track getTrack(Long id) {

        Ltrack.stream()
                .filter(track -> track.equals(id)).collect(Collectors.toList());

        return TrackMap.get(id);

    }

    @Override
    public Track createTrack(TrackRequest request) {
        return null;
    }

    @Override
    public void updateTrack(Long id) {

    }

    @Override
    public List<Album> getAlbumList() {
        return null;
    }

    @Override
    public Album getAlbum() {
        return null;
    }

    @Override
    public Album createAlbum(AlbumRequest request) {
        return null;
    }

    @Override
    public void updateAlbum(Long idAlbum) {

    }

    @Override
    public List<Artist> getArtisList() {
        return null;
    }

    @Override
    public Artist getArtist() {
        return null;
    }

    @Override
    public Artist createArtist(ArtistRequest request) {
        return null;
    }

    @Override
    public void updateArtist(Long idArtist) {

    }
}
