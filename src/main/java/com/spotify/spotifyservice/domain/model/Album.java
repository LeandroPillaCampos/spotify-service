package com.spotify.spotifyservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @NotEmpty(message = "The 'name' field can not be empty")
    @NotNull(message = "The 'name' field can not be empty")
    @Size(min=3, max=100, message="The number of characters is not allowed")
    private String name;

    @OneToMany(mappedBy = "joinAlbum")
    private List<Track> joinTrack=new ArrayList<>();


    @ManyToOne
    @JoinColumn()
    private Artist joinArtist;





}
