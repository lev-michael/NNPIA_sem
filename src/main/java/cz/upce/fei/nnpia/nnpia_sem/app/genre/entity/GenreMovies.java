package cz.upce.fei.nnpia.nnpia_sem.app.genre.entity;

import cz.upce.fei.nnpia.nnpia_sem.app.movie.entity.Movie;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

@Entity
@IdClass(ComposedGenreMovieId.class)
public class GenreMovies {
    @ManyToOne
    @Id
    private Genre genre;

    @ManyToOne
    @Id
    private Movie movie;
}
