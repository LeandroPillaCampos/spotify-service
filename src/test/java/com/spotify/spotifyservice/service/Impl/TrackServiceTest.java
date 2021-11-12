package com.spotify.spotifyservice.service.Impl;

import com.spotify.spotifyservice.domain.model.Track;
import com.spotify.spotifyservice.repositories.TrackRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

class TrackServiceTest {
    @InjectMocks
    private ImplTrackService tService;
    @Mock
    private TrackRepository tRepository;

    private Track track;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        track=Track.builder()
                .id(3L)
                .name("It's my life")
                .duration(4.30)
                .reproduction(0L)
                .build();

    }


    @Test
    void getTrackID(){
        //when(tRepository.findById(any())).thenReturn(Optional.ofNullable(track));
        assertNotNull(tService.getTrackID(any()));
    }

    @Test
    void deleteTrackFound() {
        Long number=1000000L;

        assertEquals(tService.deleteTrack(any()).getStatusCode(), HttpStatus.OK);
        tRepository.deleteById(number);
        verify(tRepository).deleteById(eq(number));
    }

    @Test
    void deleteTrackNotFound() {


    }

}