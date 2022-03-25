package cz.upce.fei.nnpia.nnpia_sem.app.rating.repository;

import cz.upce.fei.nnpia.nnpia_sem.app.movie.entity.Movie;
import cz.upce.fei.nnpia.nnpia_sem.app.rating.entity.ComposedUserMovieId;
import cz.upce.fei.nnpia.nnpia_sem.app.rating.entity.Rating;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, ComposedUserMovieId> {

    @Query("SELECT AVG(r.score) from Rating r WHERE r.movie.id = ?1")
    double findAvgRating(Long movieId);

    @Query("SELECT r.movie FROM Rating r ORDER BY r.score DESC")
    List<Movie> findBestMovies(Pageable pageable);
}
