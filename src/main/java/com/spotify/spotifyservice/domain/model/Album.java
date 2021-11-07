package com.spotify.spotifyservice.domain.model;

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
    private  Long idAlbum;
    private Long idArtist;
    private String name;

    @OneToMany(mappedBy = "joinAlbum")
    private List<Track> joinTrack=new ArrayList<>();


    @ManyToOne
    @JoinColumn(name = "FKArtist")
    private Artist joinArtist;





}
