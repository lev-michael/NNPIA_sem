package cz.upce.fei.nnpia.nnpia_sem.app.genre.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.entity.Movie;
import lombok.Data;

import javax.persistence.*;

@Entity(name = "movie_genres")
@IdClass(ComposedGenreMovieId.class)
@Data
public class GenreMovies {


    @ManyToOne()
    @Id
    private Genre genre;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @Id
    private Movie movie;
}
