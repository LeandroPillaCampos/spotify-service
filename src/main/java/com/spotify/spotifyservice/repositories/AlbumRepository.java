package com.spotify.spotifyservice.repositories;

import com.spotify.spotifyservice.domain.model.Album;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends CrudRepository<Album,Long> {
}
