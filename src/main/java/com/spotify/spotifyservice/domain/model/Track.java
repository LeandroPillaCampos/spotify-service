package com.spotify.spotifyservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "Track")
public class Track {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "The 'name' field can not be empty")
    @NotNull (message = "The 'name' field can not be empty")
    @Size(min=3, max=30, message="The number of characters is not allowed")
    private String name;
    private Long idArtist;
    private Long idAlbum;
    private Long reproduction;
    @NotNull (message = "The 'duration' field can not be empty")
    private Double duration;

    @ManyToOne
    @JoinColumn()
    private Album joinAlbum;


}
