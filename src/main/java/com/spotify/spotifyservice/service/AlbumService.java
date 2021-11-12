package com.spotify.spotifyservice.service;
import com.spotify.spotifyservice.controller.request.AlbumRequest;
import com.spotify.spotifyservice.domain.model.Album;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AlbumService {

    ResponseEntity<Album> getAlbumID(Long idAlbum);
    ResponseEntity<Album> createAlbum(AlbumRequest request);
    ResponseEntity<Album> updateAlbum (Long idAlbum,AlbumRequest request);
    ResponseEntity<Album> deleteAlbum(Long idAlbum);

}
