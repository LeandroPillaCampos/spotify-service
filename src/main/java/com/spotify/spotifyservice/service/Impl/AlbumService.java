package com.spotify.spotifyservice.service.Impl;

import com.spotify.spotifyservice.controller.request.AlbumRequest;
import com.spotify.spotifyservice.domain.mapper.AlbumMapper;
import com.spotify.spotifyservice.domain.model.Album;
import com.spotify.spotifyservice.exception.AlbumNotExistException;
import com.spotify.spotifyservice.exception.ArtistExistException;
import com.spotify.spotifyservice.repositories.AlbumRepository;
import com.spotify.spotifyservice.service.IAlbumService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Qualifier("album")
public class AlbumService  implements IAlbumService {

    @Qualifier("album")
    @Autowired
    private IAlbumService albService;

   // @Qualifier("album")
    @Autowired
    private List<Album> lAlbum;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private AlbumMapper albMapper;

    @PostConstruct
    public void init() {
        lAlbum.stream().forEach(track -> {
            //TrackMap.put(track.getId(), track);
            albumRepository.save(track);
        });
    }

    @Override
    public ResponseEntity<Album> getAlbumID(Long idAlbum) {
        Optional<Album> optionalAlbum= albumRepository.findById(idAlbum);
        if(optionalAlbum.isPresent()){
            return ResponseEntity.ok(optionalAlbum.get());

        }else return ResponseEntity.noContent().build();

    }

    @Override
    public ResponseEntity<Album> createAlbum(AlbumRequest request) {
        Album album = albMapper.apply(request);
        if (request.getIdAlbum() != null && albumRepository.findById(request.getIdAlbum()) != null) {
            log.error("Album already exists");
            throw new ArtistExistException("Album not exist");
        } else {
            albumRepository.save(albMapper.apply(request));
        }
        return ResponseEntity.ok(album);
    }

    @Override
    public ResponseEntity<Album> updateAlbum(Long idAlbum, AlbumRequest request) {

        Album album=new Album();

        if (albumRepository.findById(idAlbum) != null) {
            album= albMapper.apply(request);
            albumRepository.save(album);

            return ResponseEntity.ok(album);
        } else {
            log.error("Album not exist");
            throw new AlbumNotExistException("Album not exist");
        }
    }

    @Override
    public ResponseEntity<Album> deleteAlbum(Long idAlbum) {

        if(albumRepository.findById(idAlbum)==null){
            return new ResponseEntity<Album>(HttpStatus.NOT_FOUND);
        }else{
            albumRepository.deleteById(idAlbum);
            return new ResponseEntity<Album>(HttpStatus.OK);
        }

    }




}
