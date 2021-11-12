package com.spotify.spotifyservice.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "Artist")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ARTIST_ID")
    private Long idArtist;
    private String name;
    private String genre;
    private String image;

    @OneToMany(mappedBy = "artist")
    @JsonIgnore
    private List<Album> album = new ArrayList<>();

    @OneToMany(mappedBy = "artist")
    @JsonIgnore
    private List<Track> track = new ArrayList<>();


}
