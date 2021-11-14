package com.spotify.spotifyservice.service.Impl;

import com.spotify.spotifyservice.controller.request.AlbumRequest;
import com.spotify.spotifyservice.domain.mapper.AlbumMapper;
import com.spotify.spotifyservice.domain.model.Album;
import com.spotify.spotifyservice.domain.model.Artist;
import com.spotify.spotifyservice.domain.model.Track;
import com.spotify.spotifyservice.repositories.AlbumRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class AlbumServiceTest {

    @MockBean
    private AlbumRepository albumRepository;

    @Mock
    private ImplAlbumService albumService;

    @Mock
    private AlbumRequest albumRequest;

    @Mock
    private AlbumMapper albumMapper;

    private Album album;


    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        album = Album.builder()
                .idAlbum(2L)
                .name("nameTest")
                .artist(Artist.builder().idArtist(1L).image("imageTest").genre("genreTest").name("nameTest").build())
                .track(new ArrayList<>())
                .build();
    }

    @Test
    void getAlbumID() {

        Long idAlbum=2L;

        albumService.getAlbumID(idAlbum);
        when(albumRepository.findById(idAlbum)).thenReturn( Optional.ofNullable(album));

        Optional<Album> optionalAlbum = albumRepository.findById(idAlbum);
        assertNotNull(optionalAlbum.get());
    }

    @Test
    void deleteAlbum() {
        Long idAlbum=2L;

        albumService.deleteAlbum(idAlbum);
        albumRepository.deleteById(idAlbum);

        assertNotNull(albumRepository.findById(idAlbum));
        verify(albumRepository).deleteById(eq(idAlbum));
    }

    @Test
    void createAlbum(){

        albumRequest.setIdAlbum(2L);
        albumRequest.setName("testName");
        albumRequest.setArtist(any(Artist.class));

        albumService.createAlbum(albumRequest);
        Album response = albumMapper.apply(albumRequest);
        albumRepository.save(response);

        Assertions.assertThat(album.getIdAlbum()).isGreaterThan(0);
    }


    @Test
    void updateAlbum(){

        Long idAlbum=2L;
        album.setName("newTestName");

        albumRequest.setIdAlbum(2L);
        albumRequest.setName("newTestName");
        albumRequest.setArtist(any(Artist.class));

        Optional<Album> optionalAlbum=Optional.of(album);
        when(albumRepository.findById(idAlbum)).thenReturn(optionalAlbum);

        albumService.updateAlbum(idAlbum,albumRequest);
        Album response = albumMapper.apply(albumRequest);
        albumRepository.save(response);

        Album testAlbum= albumRepository.findById(2L).get();
        assertEquals(testAlbum.getName(),"newTestName");
        
    }



}