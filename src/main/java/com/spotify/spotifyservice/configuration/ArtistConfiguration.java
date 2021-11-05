package com.spotify.spotifyservice.configuration;

import com.spotify.spotifyservice.domain.model.Artist;
import com.spotify.spotifyservice.domain.model.Track;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Arrays;
import java.util.List;

@Configuration
public class ArtistConfiguration {


    @Bean("artist")
    public List<Artist> getArtist(){
        return Arrays.asList(
                Artist.builder().idArtist(1L).name("Bon Jovi").genre("Male").image("image").build(),
                Artist.builder().idArtist(1L).name("The Rolling Stones").genre("Male").image("image").build(),
                Artist.builder().idArtist(1L).name("The Police").genre("Male").image("image").build(),
                Artist.builder().idArtist(1L).name("Eminem").genre("Male").image("image").build()


        );
    }
}
