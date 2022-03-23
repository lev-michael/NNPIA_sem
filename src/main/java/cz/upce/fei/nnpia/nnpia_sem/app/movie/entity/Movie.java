package cz.upce.fei.nnpia.nnpia_sem.app.movie.entity;

import cz.upce.fei.nnpia.nnpia_sem.app.actor.entity.Actor;
import cz.upce.fei.nnpia.nnpia_sem.app.genre.entity.Genre;
import cz.upce.fei.nnpia.nnpia_sem.app.rating.entity.Rating;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank()
    private String title;

    @Column(length = 2500)
    @NotBlank()
    private String description;

    @Column
    @Min(1)
    private int length;

    @Column
    @NotBlank
    private int launchYear;

    @OneToMany(mappedBy = "id")
    private Set<Actor> actors;

    @OneToMany(mappedBy = "movie")
    private Set<Rating> scores;

    @OneToMany(mappedBy = "id")
    private Set<Genre> genres;
}
