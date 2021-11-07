package com.spotify.spotifyservice.service.Impl;

import com.spotify.spotifyservice.controller.request.AlbumRequest;
import com.spotify.spotifyservice.controller.request.ArtistRequest;
import com.spotify.spotifyservice.controller.request.TrackRequest;
import com.spotify.spotifyservice.domain.model.Album;
import com.spotify.spotifyservice.domain.model.Artist;
import com.spotify.spotifyservice.domain.model.Track;
import com.spotify.spotifyservice.repositories.ArtistRepository;
import com.spotify.spotifyservice.repositories.TrackRepository;
import com.spotify.spotifyservice.service.IArtistService;
import com.spotify.spotifyservice.service.ITrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@Qualifier ("track")
public class TrackService  implements ITrackService{

    @Qualifier("track")
    @Autowired
    private ITrackService TService;

    @Autowired
    private List<Track> Ltrack;

    @Autowired
    private TrackRepository TRepository;

    @PostConstruct
    public void init() {
        Ltrack.stream().forEach(track -> {
            //TrackMap.put(track.getId(), track);
            TRepository.save(track);
        });
    }

    private Map<Long, Track> TrackMap;

    @Override
    public Iterable<Track> getTrackList() {
        //List<Track> tracks= (List<Track>) TRepository.findAll();
        return TRepository.findAll();
    }

    @Override
    public ResponseEntity<List<Track>> getTopFiveTrack(Long id) {
        Iterable<Track> AllTracks=TRepository.findAll();
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
    public ResponseEntity<Track> getTrack(Long id) {

        //TrackMap.get(id).setReproduction(TrackMap.get(id).getReproduction()+1);
        Optional<Track> optionalTrack= TRepository.findById(id);

        if(optionalTrack.isPresent()){
            Track countTrack= optionalTrack.get();
            countTrack.setReproduction(optionalTrack.get().getReproduction()+1);
            TRepository.save(countTrack);

            return ResponseEntity.ok(countTrack);
        }else return ResponseEntity.noContent().build();
    }

/*
    @Override
    public Track getTrack(Long id) {

        Ltrack.stream()
                .filter(track -> track.equals(id)).collect(Collectors.toList());

        return TrackMap.get(id);

      //  return null;
    }


 */
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
    public ResponseEntity<Artist> getArtist(Long idArtist) {
        return null;
    }

    @Override
    public ResponseEntity<List<Artist>> getTopFiveArtist() {

        /*
         Iterable<Track> AllTracks=TRepository.findAll();
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
         */

        Iterable<Track> AllArtists=TRepository.findAll();

        List<Track> artists=new ArrayList<>();
        for(Track artist: AllArtists){

           // artists.add(Track.builder().);

        }


        return null;
    }

    @Override
    public Artist createArtist(ArtistRequest request) {
        return null;
    }

    @Override
    public void updateArtist(Long idArtist) {

    }
/*
    @Override
    public int compareTo(Track track) {

        return 0;
    }

 */
}
