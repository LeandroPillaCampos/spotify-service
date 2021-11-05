package com.spotify.spotifyservice.configuration;

import com.spotify.spotifyservice.domain.model.Album;

import java.util.Arrays;
import java.util.List;

public class AlbumConfiguration {

    public List<Album> getListAlbum(){
        return Arrays.asList(
                Album.builder().idAlbum(1L).idArtist(1L).name("Crush").build(),
                Album.builder().idAlbum(2L).idArtist(2L).name("Out of Our Heads").build(),
                Album.builder().idAlbum(3L).idArtist(3L).name("Synchronicity").build(),
                Album.builder().idAlbum(4L).idArtist(4L).name("The Eminem Show").build()
                
        );
    }
}
