package cz.upce.fei.nnpia.nnpia_sem.app.watchlist.controller;

import cz.upce.fei.nnpia.nnpia_sem.app.config.ApiResponse;
import cz.upce.fei.nnpia.nnpia_sem.app.config.StausEnum;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.entity.Movie;
import cz.upce.fei.nnpia.nnpia_sem.app.watchlist.dto.SearchWithUserIdDto;
import cz.upce.fei.nnpia.nnpia_sem.app.watchlist.dto.UserIdMovieIdDto;
import cz.upce.fei.nnpia.nnpia_sem.app.watchlist.service.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/watchlist")
@CrossOrigin
public class WatchlistController {

    @Autowired
    private WatchlistService watchlistService;

    @GetMapping("/{id}")
    public ApiResponse<List<Long>> getIdsMoviesOnWatchlist(@PathVariable() Long id) {
        List<Long> userMoviesIdsOnWatchlist = this.watchlistService.getUserMoviesIdsOnWatchlist(id);
        return new ApiResponse<>(HttpStatus.OK.value(), StausEnum.SUCCESS, userMoviesIdsOnWatchlist);
    }

    @PostMapping("/list")
    public ApiResponse<Page<Movie>> getMoviesOnWatchlist(@RequestBody() SearchWithUserIdDto searchWithUserIdDto, Pageable pageable) {
        Page<Movie> userMoviesOnWatchlist = this.watchlistService.getUserMoviesOnWatchlist(searchWithUserIdDto.getUserId(), pageable, searchWithUserIdDto.getQuery());
        return new ApiResponse<>(HttpStatus.OK.value(), StausEnum.SUCCESS, userMoviesOnWatchlist);
    }

    @PostMapping("/add")
    public ApiResponse<Boolean> addMovieOnWatchList(@RequestBody() UserIdMovieIdDto userIdMovieIdDto) {
        Boolean added = this.watchlistService.addMovieToWatchlist(userIdMovieIdDto);
        return new ApiResponse<>(HttpStatus.OK.value(), added ? StausEnum.SUCCESS : StausEnum.NOT_FOUND, added);
    }

    @PostMapping("/remove")
    public ApiResponse<Boolean> removeMovieFromWatchList(@RequestBody() UserIdMovieIdDto userIdMovieIdDto) {
        Boolean removed = this.watchlistService.removeMovieFromWatchlist(userIdMovieIdDto);
        return new ApiResponse<>(HttpStatus.OK.value(), removed ? StausEnum.SUCCESS : StausEnum.NOT_FOUND, removed);
    }

    @ExceptionHandler({Exception.class})
    public StausEnum handleException() {
        return StausEnum.NOT_FOUND;
    }
}
