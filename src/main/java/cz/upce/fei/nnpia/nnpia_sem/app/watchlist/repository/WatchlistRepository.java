package cz.upce.fei.nnpia.nnpia_sem.app.watchlist.repository;

import cz.upce.fei.nnpia.nnpia_sem.app.movie.entity.Movie;
import cz.upce.fei.nnpia.nnpia_sem.app.rating.entity.ComposedUserMovieId;
import cz.upce.fei.nnpia.nnpia_sem.app.watchlist.entity.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WatchlistRepository extends JpaRepository<Watchlist, ComposedUserMovieId> {

    @Query("SELECT w.movie FROM Watchlist w WHERE w.user.id = ?1")
    List<Movie> findAllByUser_Id(Long user);

}