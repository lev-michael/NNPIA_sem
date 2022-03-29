package cz.upce.fei.nnpia.nnpia_sem.app.person.entity;

import cz.upce.fei.nnpia.nnpia_sem.app.movie.entity.Movie;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity(name = "movie_cast")
@Data
@IdClass(ComposedPersonMovieId.class)
public class MovieCast {

    @ManyToOne
    @Id
    private Movie movie;

    @ManyToOne
    @Id
    private Person person;

    @Column(name = "character_name")
    @NotBlank
    private String character;

    @Column(name = "cast_order")
    @NotBlank
    @Min(0)
    private int order;

}
