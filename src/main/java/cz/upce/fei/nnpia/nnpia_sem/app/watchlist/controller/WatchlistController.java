package cz.upce.fei.nnpia.nnpia_sem.app.watchlist.controller;

import cz.upce.fei.nnpia.nnpia_sem.app.movie.entity.Movie;
import cz.upce.fei.nnpia.nnpia_sem.app.watchlist.dto.SearchWithUserIdDto;
import cz.upce.fei.nnpia.nnpia_sem.app.watchlist.dto.UserIdMovieIdDto;
import cz.upce.fei.nnpia.nnpia_sem.app.watchlist.service.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/watchlist")
@CrossOrigin
public class WatchlistController {

    @Autowired
    private WatchlistService watchlistService;

    @GetMapping("/{id}")
    public List<Long> getIdsMoviesOnWatchlist(@PathVariable() Long id) {
        return this.watchlistService.getUserMoviesIdsOnWatchlist(id);
    }

    @PostMapping("/list")
    public Page<Movie> getMoviesOnWatchlist(@RequestBody() SearchWithUserIdDto searchWithUserIdDto, Pageable pageable) {
        return this.watchlistService.getUserMoviesOnWatchlist(searchWithUserIdDto.getUserId(), pageable, searchWithUserIdDto.getQuery());
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
