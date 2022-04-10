package cz.upce.fei.nnpia.nnpia_sem.app.watchlist.service;

import cz.upce.fei.nnpia.nnpia_sem.app.movie.entity.Movie;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.repository.MovieRepository;
import cz.upce.fei.nnpia.nnpia_sem.app.user.entity.User;
import cz.upce.fei.nnpia.nnpia_sem.app.user.repository.UserRepository;
import cz.upce.fei.nnpia.nnpia_sem.app.watchlist.dto.UserIdMovieIdDto;
import cz.upce.fei.nnpia.nnpia_sem.app.watchlist.entity.Watchlist;
import cz.upce.fei.nnpia.nnpia_sem.app.watchlist.repository.WatchlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WatchlistService {
    @Autowired
    private WatchlistRepository watchlistRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    public List<Long> getUserMoviesIdsOnWatchlist(Long userId) {
        return watchlistRepository.findAllIdsByUser_Id(userId);
    }

    public void addMovieToWatchlist(UserIdMovieIdDto userIdMovieIdDto) {
        Movie movie = movieRepository.findById(userIdMovieIdDto.getMovieId()).orElse(null);
        User user = userRepository.findById(userIdMovieIdDto.getUserId()).orElse(null);
        if (user == null || movie == null) {
            return;
        }
        this.watchlistRepository.save(new Watchlist(movie, user));
    }

    public void removeMovieFromWatchlist(UserIdMovieIdDto userIdMovieIdDto) {
        Movie movie = movieRepository.findById(userIdMovieIdDto.getMovieId()).orElse(null);
        User user = userRepository.findById(userIdMovieIdDto.getUserId()).orElse(null);

        if (user == null || movie == null) {
            return;
        }
        this.watchlistRepository.delete(new Watchlist(movie, user));
    }

    public Page<Movie> getUserMoviesOnWatchlist(Long userId, Pageable pageable, String query) {
        return watchlistRepository.findAllMoviesByUser_Id(userId, pageable, query);
    }
}
