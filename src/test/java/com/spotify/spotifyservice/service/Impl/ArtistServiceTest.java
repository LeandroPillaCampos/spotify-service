package com.spotify.spotifyservice.service.Impl;

import com.spotify.spotifyservice.controller.request.ArtistRequest;
import com.spotify.spotifyservice.domain.mapper.ArtistMapper;
import com.spotify.spotifyservice.domain.model.Album;
import com.spotify.spotifyservice.domain.model.Artist;
import com.spotify.spotifyservice.domain.model.Track;
import com.spotify.spotifyservice.repositories.ArtistRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ArtistServiceTest {

    @MockBean
    private ArtistRepository artistRepository;

    @Mock
    private ImplArtistService artistService;

    @Mock
    private ArtistRequest artistRequest;

    
    @Mock
    private ArtistMapper artistMapper;

    private Artist artist;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        artist = Artist.builder()
                .idArtist(1L)
                .name("testName")
                .genre("genreTest")
                .image("imageTest")
                .build();
    }

    @Test
    void getArtistID() {

        Long idArtist=2L;

        artistService.getArtistID(idArtist);
        when(artistRepository.findById(idArtist)).thenReturn( Optional.ofNullable(artist));

        Optional<Artist> optionalArtist = artistRepository.findById(idArtist);
        assertNotNull(optionalArtist.get());

    }


    @Test
    void deleteArtist() {

        Long idArtist=2L;

        artistService.deleteArtist(idArtist);
        artistRepository.deleteById(idArtist);

        assertNotNull(artistRepository.findById(idArtist));
        verify(artistRepository).deleteById(eq(idArtist));
    }

    @Test
    void createTrack(){

        artistRequest.setIdArtist(2L);
        artistRequest.setName("testName");
        artistRequest.setGenre("genreTest");
        artistRequest.setImage("imageTest");

        artistService.createArtist(artistRequest);
        Artist response = artistMapper.apply(artistRequest);
        artistRepository.save(response);

        Assertions.assertThat(artist.getIdArtist()).isGreaterThan(0);
    }


    @Test
    void updateTrack(){

        Long idArtist=2L;
        artist.setName("newTestName");

        artistRequest.setIdArtist(2L);
        artistRequest.setName("newTestName");
        artistRequest.setGenre("newGenreTest");
        artistRequest.setImage("newImageTest");

        Optional<Artist> optionalArtist=Optional.of(artist);
        when(artistRepository.findById(idArtist)).thenReturn(optionalArtist);

        artistService.updateArtist(idArtist,artistRequest);
        Artist response = artistMapper.apply(artistRequest);
        artistRepository.save(response);

        Artist testTrack= artistRepository.findById(2L).get();
        assertEquals(testTrack.getName(),"newTestName");
    }
}