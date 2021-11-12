package com.spotify.spotifyservice.configuration;

import com.spotify.spotifyservice.domain.model.Album;
import com.spotify.spotifyservice.domain.model.Artist;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class AlbumConfiguration {

    @Bean("album")
    public List<Album> getListAlbum() {
        return Arrays.asList(
                Album.builder().idAlbum(1L).name("Crush").build(),
                Album.builder().idAlbum(2L).name("Out of Our Heads").build(),
                Album.builder().idAlbum(3L).name("Synchronicity").build(),
                Album.builder().idAlbum(4L).name("The Eminem Show").build(),
                Album.builder().idAlbum(5L).name("Cross Road").build()

        );
    }
}
