package com.spotify.spotifyservice.controller;

import com.spotify.spotifyservice.controller.request.AlbumRequest;
import com.spotify.spotifyservice.controller.request.TrackRequest;
import com.spotify.spotifyservice.domain.model.Album;
import com.spotify.spotifyservice.domain.model.Track;
import com.spotify.spotifyservice.repositories.AlbumRepository;
import com.spotify.spotifyservice.service.Impl.AlbumService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/")
public class AlbumController {

    @Autowired
    private AlbumRepository AlbRepository;

    @Autowired
    private AlbumService AlbService;


    @PostMapping("/album")
    public ResponseEntity<Album> postAlbum(@Validated @RequestBody AlbumRequest request){
        return AlbService.createAlbum(request);
    }

    @PutMapping("/album/{albumId}")
    public ResponseEntity<Album> putAlbum(@Validated @RequestBody AlbumRequest request, @PathVariable("albumId") Long idAlbum){
        return AlbService.updateAlbum(idAlbum ,request);
    }

    @GetMapping("/album/{albumId}")
    public ResponseEntity<Album> getAlbumID(@PathVariable("albumId") Long idAlbum){
        return AlbService.getAlbumID(idAlbum);
    }

    @DeleteMapping("/album/{albumId}")
    public ResponseEntity<Album> deleteAlbum(@PathVariable("albumId") Long idAlbum){
        return AlbService.deleteAlbum(idAlbum);
    }

}
