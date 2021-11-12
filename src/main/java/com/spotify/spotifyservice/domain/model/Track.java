package com.spotify.spotifyservice.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "Track")
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRACK_ID")
    private Long id;
    private String name;
    private Long reproduction;
    private Double duration;

    @ManyToOne
    @JoinColumn(name = "ALBUM_ID")
    private Album album;

    @ManyToOne
    @JoinColumn(name = "ARTIST_ID")
    @JsonIgnore
    private Artist artist;


}
