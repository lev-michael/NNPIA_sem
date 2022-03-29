package cz.upce.fei.nnpia.nnpia_sem.app.person.entity;

import cz.upce.fei.nnpia.nnpia_sem.app.movie.entity.Movie;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity(name = "movie_crew")
@Data
@IdClass(ComposedPersonMovieId.class)
public class MovieCrew {

    @ManyToOne
    @Id
    private Movie movie;

    @ManyToOne
    @Id
    private Person person;

    @Column
    @NotBlank
    private String role;

}
