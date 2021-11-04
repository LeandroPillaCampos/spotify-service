package com.spotify.spotifyservice.service.Impl;

import com.spotify.spotifyservice.controller.request.AlbumRequest;
import com.spotify.spotifyservice.domain.model.Album;
import com.spotify.spotifyservice.service.IAlbumService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService  implements IAlbumService {
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
}
