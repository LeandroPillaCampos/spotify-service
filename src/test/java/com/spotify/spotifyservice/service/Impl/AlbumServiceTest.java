package com.spotify.spotifyservice.service.Impl;

import com.spotify.spotifyservice.domain.model.Album;
import com.spotify.spotifyservice.repositories.AlbumRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class AlbumServiceTest {

    @InjectMocks
    private ImplAlbumService albService;

    @Mock
    private AlbumRepository albRepository;

    Album album;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        album = Album.builder()
                .idAlbum(2L)
                .name("Cross Road")
                .build();
    }

    @Test
    void getAlbumID() {

        when(albRepository.findById(any())).thenReturn( Optional.ofNullable(album));
        assertNotNull(albService.getAlbumID(any()));
    }

    @Test
    void deleteAlbumFound() {
        Long number=1000000L;

        assertEquals(albService.deleteAlbum(any()).getStatusCode(),HttpStatus.OK);
        albRepository.deleteById(number);
        verify(albRepository).deleteById(eq(number));
    }

    @Test
    void deleteAlbumNotFound() {

    }


}