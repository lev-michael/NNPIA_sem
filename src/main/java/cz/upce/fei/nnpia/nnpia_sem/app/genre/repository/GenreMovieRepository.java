package cz.upce.fei.nnpia.nnpia_sem.app.genre.repository;

import cz.upce.fei.nnpia.nnpia_sem.app.genre.repository.entity.ComposedGenreMovieId;
import cz.upce.fei.nnpia.nnpia_sem.app.genre.repository.entity.GenreMovies;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreMovieRepository extends JpaRepository<GenreMovies, ComposedGenreMovieId> {

}
