package com.spotify.spotifyservice.configuration;

import com.spotify.spotifyservice.domain.model.Track;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class TrackConfiguration {

    @Bean ("track")
    public List<Track> getListTrack(){
        return Arrays.asList(
                Track.builder().id(1L).name("It's My Life").idAlbum(1L).idArtist(1L).reproduction(0L).duration(4.27).build(),
                Track.builder().id(2L).name("Satisfaction").idAlbum(2L).idArtist(2L).reproduction(0L).duration(3.54).build(),
                Track.builder().id(3L).name("Every Breath You Take").idAlbum(3L).idArtist(3L).reproduction(0L).duration(4.13).build(),
                Track.builder().id(4L).name("Without Me").idAlbum(4L).idArtist(4L).reproduction(0L).duration(4.57).build()
                );
    }

}
