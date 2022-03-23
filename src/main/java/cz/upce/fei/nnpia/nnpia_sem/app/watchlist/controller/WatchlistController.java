package cz.upce.fei.nnpia.nnpia_sem.app.watchlist.controller;

import cz.upce.fei.nnpia.nnpia_sem.app.movie.entity.Movie;
import cz.upce.fei.nnpia.nnpia_sem.app.user.dto.UserIdDto;
import cz.upce.fei.nnpia.nnpia_sem.app.watchlist.dto.UserIdMovieIdDto;
import cz.upce.fei.nnpia.nnpia_sem.app.watchlist.service.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/watchlist")
public class WatchlistController {

    @Autowired
    private WatchlistService watchlistService;

    @GetMapping("/")
    public List<Movie> getMoviesOnWatchlist(@RequestBody() UserIdDto userIdDto) {
        return this.watchlistService.getUserWatchlist(userIdDto.getUserId());
    }

    @PostMapping("/add")
    public void addMovieOnWatchList(@RequestBody() UserIdMovieIdDto userIdMovieIdDto) {
        this.watchlistService.addMovieToWatchlist(userIdMovieIdDto);
    }

    @PostMapping("/remove")
    public void removeMovieFromWatchList(@RequestBody() UserIdMovieIdDto userIdMovieIdDto) {
        this.watchlistService.removeMovieFromWatchlist(userIdMovieIdDto);
    }

}