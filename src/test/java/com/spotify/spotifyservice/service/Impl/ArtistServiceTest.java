package com.spotify.spotifyservice.service.Impl;

import com.spotify.spotifyservice.domain.model.Artist;
import com.spotify.spotifyservice.repositories.ArtistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class ArtistServiceTest {

    @Mock
    private ArtistRepository aRepository;

    @InjectMocks
    private ImplArtistService aService;

    Artist artist;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        artist = Artist.builder()
                .idArtist(1L)
                .name("Bon Jovi")
                .genre("Rock")
                .image("image")
                .build();

    }

    @Test
    void getArtistID() {

        when(aRepository.findById(any())).thenReturn(Optional.ofNullable(artist));
        assertNotNull(aService.getArtistID(any()));
    }


    @Test
    void deleteArtistFound() {

        Long number=1000000L;

        assertEquals(aService.deleteArtist(any()).getStatusCode(),HttpStatus.OK);
        aRepository.deleteById(number);
        verify(aRepository).deleteById(eq(number));
    }

    @Test
    void deleteArtistNotFound() {
        ResponseEntity<Void> responseEntity = ResponseEntity.noContent().build();

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());

    }






}