package com.spotify.spotifyservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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
    private Long idArtist;
    @NotEmpty(message = "The 'name' field can not be empty")
    @NotNull(message = "The 'name' field can not be empty")
    @Size(min=3, max=100, message="The number of characters is not allowed")
    private String name;
    @NotEmpty(message = "The 'genre' field can not be empty")
    @NotNull (message = "The 'genre' field can not be empty")
    @Size(min=3, max=30, message="The number of characters is not allowed")
    private String genre;
    private String image;


    @OneToMany(mappedBy = "joinArtist")
    private List<Album> joinAlbum=new ArrayList<>();


}
