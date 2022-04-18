package cz.upce.fei.nnpia.nnpia_sem.app.watchlist.repository;

import cz.upce.fei.nnpia.nnpia_sem.app.movie.entity.Movie;
import cz.upce.fei.nnpia.nnpia_sem.app.rating.entity.ComposedUserMovieId;
import cz.upce.fei.nnpia.nnpia_sem.app.watchlist.entity.Watchlist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WatchlistRepository extends JpaRepository<Watchlist, ComposedUserMovieId> {

    @Query("SELECT w.movie.id FROM Watchlist w WHERE w.user.id = ?1")
    List<Long> findAllIdsByUser_Id(Long user);

    @Query("SELECT w.movie FROM Watchlist w WHERE w.user.id = ?1 AND lower(w.movie.title) like %?2%")
    Page<Movie> findAllMoviesByUser_Id(Long user, Pageable pageable, String query);

}
