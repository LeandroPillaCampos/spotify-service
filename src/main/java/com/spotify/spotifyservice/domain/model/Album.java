package com.spotify.spotifyservice.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "Album")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ALBUM_ID")
    private Long idAlbum;
    private String name;

    @OneToMany(mappedBy = "album")
    @JsonIgnore
    private List<Track> track = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "ARTIST_ID")
    private Artist artist;

}
