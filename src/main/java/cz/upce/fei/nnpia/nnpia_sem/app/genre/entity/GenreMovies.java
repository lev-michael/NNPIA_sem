package cz.upce.fei.nnpia.nnpia_sem.app.genre.entity;

import cz.upce.fei.nnpia.nnpia_sem.app.movie.entity.Movie;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

@Entity(name = "movie_genres")
@IdClass(ComposedGenreMovieId.class)
@Data
public class GenreMovies {
    @ManyToOne
    @Id
    private Genre genre;

    @ManyToOne
    @Id
    private Movie movie;
}
