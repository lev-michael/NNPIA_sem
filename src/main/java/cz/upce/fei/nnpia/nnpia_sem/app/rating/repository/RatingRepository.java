package cz.upce.fei.nnpia.nnpia_sem.app.rating.repository;

import cz.upce.fei.nnpia.nnpia_sem.app.movie.dto.MovieListDto;
import cz.upce.fei.nnpia.nnpia_sem.app.rating.dto.MovieWithScoreDto;
import cz.upce.fei.nnpia.nnpia_sem.app.rating.entity.ComposedUserMovieId;
import cz.upce.fei.nnpia.nnpia_sem.app.rating.entity.Rating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, ComposedUserMovieId> {

    @Query("SELECT AVG(r.score) from Rating r WHERE r.movie.id = ?1")
    Double findAvgRating(Long movieId);

    @Query("SELECT m.title as title, m.release_date as release_date, m.img as img, m.id as id, AVG(r.score) as avgScore " +
            "FROM Rating r " +
            "JOIN Movie m on m.id = r.movie.id " +
            "GROUP BY id " +
            "ORDER BY avgScore DESC")
    List<MovieListDto> findBestMovies(Pageable pageable);

    @Query("SELECT m.title as title, m.release_date as release_date, m.img as img, m.id as id, AVG(r.score) as avgScore " +
            "FROM Rating r " +
            "JOIN Movie m on m.id = r.movie.id " +
            "GROUP BY id " +
            "ORDER BY avgScore ASC")
    List<MovieListDto> findWorstMovies(Pageable pageable);

    @Query("SELECT r.movie.id as id,r.movie.title as title, r.movie.img as img, r.movie.description as description, r.movie.runtime as runtime,r.movie.release_date as release_date , r.score as score " +
            "FROM Rating r Where r.user.id = ?1 AND lower(r.movie.title) like %?2%")
    Page<MovieWithScoreDto> findAllByUserId(Long userId, String query, Pageable pageable);
}
