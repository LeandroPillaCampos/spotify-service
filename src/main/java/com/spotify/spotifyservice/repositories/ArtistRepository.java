package com.spotify.spotifyservice.repositories;

import com.spotify.spotifyservice.domain.model.Artist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends CrudRepository<Artist, Long> {
}
