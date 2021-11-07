package com.spotify.spotifyservice.service.Impl;

import com.spotify.spotifyservice.controller.request.AlbumRequest;
import com.spotify.spotifyservice.controller.request.ArtistRequest;
import com.spotify.spotifyservice.domain.model.Album;
import com.spotify.spotifyservice.domain.model.Artist;
import com.spotify.spotifyservice.domain.model.Track;
import com.spotify.spotifyservice.repositories.AlbumRepository;
import com.spotify.spotifyservice.service.IAlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@Qualifier("album")
public class AlbumService  implements IAlbumService {

    @Qualifier("album")
    @Autowired
    private IAlbumService AlbService;

   // @Qualifier("album")
    @Autowired
    private List<Album> LAlbum;

    @Autowired
    private AlbumRepository AlbRepository;

    @PostConstruct
    public void init() {
        LAlbum.stream().forEach(track -> {
            //TrackMap.put(track.getId(), track);
            AlbRepository.save(track);
        });
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
