package cz.upce.fei.nnpia.nnpia_sem.app.genre.repository.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "movie_genres")
@IdClass(ComposedGenreMovieId.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenreMovies {

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @Id
    private Genre genre;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @Id
    private Movie movie;
}
