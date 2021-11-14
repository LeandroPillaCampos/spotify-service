package com.spotify.spotifyservice.service.Impl;

import com.spotify.spotifyservice.controller.request.AlbumRequest;
import com.spotify.spotifyservice.controller.request.TrackRequest;
import com.spotify.spotifyservice.domain.mapper.TrackMapper;
import com.spotify.spotifyservice.domain.model.Album;
import com.spotify.spotifyservice.domain.model.Artist;
import com.spotify.spotifyservice.domain.model.Track;
import com.spotify.spotifyservice.repositories.TrackRepository;
import com.spotify.spotifyservice.service.Impl.ImplTrackService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class TrackServiceTest {

    @MockBean
    private TrackRepository trackRepository;

    @Mock
    private ImplTrackService trackService;

    @Mock
    private TrackRequest trackRequest;

    @Mock
    private TrackMapper trackMapper;

    private Track track;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        track = Track.builder()
                .id(2L)
                .name("testName")
                .duration(4.30)
                .reproduction(0L)
                .album(Album.builder().idAlbum(1L).name("testName").build())
                .artist(Artist.builder().idArtist(1L).image("imageTest").genre("genreTest").name("testName").build())
                .build();
    }

    @Test
    void getTrackID() {
        Long idTrack = 2L;

        trackService.getTrackID(idTrack);
        when(trackRepository.findById(idTrack)).thenReturn(Optional.ofNullable(track));

        Optional<Track> optionalTrack = trackRepository.findById(idTrack);
        assertNotNull(optionalTrack.get());
    }

    @Test
    void deleteTrack() {
        Long idTrack = 2L;

        trackService.deleteTrack(idTrack);
        trackRepository.deleteById(idTrack);

        assertNotNull(trackRepository.findById(idTrack));
        verify(trackRepository).deleteById(eq(idTrack));
    }

    @Test
    void createTrack() {

        trackRequest.setId(2L);
        trackRequest.setName("testName");
        trackRequest.setDuration(2.0);
        trackRequest.setReproduction(22L);
        trackRequest.setArtist(any(Artist.class));
        trackRequest.setAlbum(any(Album.class));

        trackService.createTrack(trackRequest);
        Track response = trackMapper.apply(trackRequest);
        trackRepository.save(response);

        Assertions.assertThat(track.getId()).isGreaterThan(0);
    }


    @Test
    void updateTrack() {

        Long idTrack = 2L;
        track.setName("newTestName");

        trackRequest.setId(2L);
        trackRequest.setName("newTestName");
        trackRequest.setDuration(2.0);
        trackRequest.setReproduction(22L);
        trackRequest.setArtist(any(Artist.class));
        trackRequest.setAlbum(any(Album.class));

        Optional<Track> optionalTrack = Optional.of(track);
        when(trackRepository.findById(idTrack)).thenReturn(optionalTrack);

        trackService.updateTrack(idTrack, trackRequest);
        Track response = trackMapper.apply(trackRequest);
        trackRepository.save(response);

        Track testTrack = trackRepository.findById(2L).get();
        assertEquals(testTrack.getName(), "newTestName");

    }

    @Test
    void getTopFivePopularTracks() {
        assertNotNull(trackRepository.findAll());
    }

    @Test
    void getIncreaseTrack() {
        Long idTrack = 2L;

        Optional<Track> optionalTrack = trackRepository.findById(idTrack);

        trackService.getIncreaseTrack(idTrack);

        when(trackRepository.findById(idTrack)).thenReturn(Optional.ofNullable(track));

        assertNotNull(optionalTrack);
    }
}