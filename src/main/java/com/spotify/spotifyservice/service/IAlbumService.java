package com.spotify.spotifyservice.service;
import com.spotify.spotifyservice.controller.request.AlbumRequest;
import com.spotify.spotifyservice.domain.model.Album;

import java.util.List;

public interface IAlbumService {

    List<Album> getAlbumList();
    Album getAlbum();
    Album createAlbum(AlbumRequest request);
    void updateAlbum (Long idAlbum);

}
