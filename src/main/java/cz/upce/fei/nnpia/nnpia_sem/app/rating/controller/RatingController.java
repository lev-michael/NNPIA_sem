package cz.upce.fei.nnpia.nnpia_sem.app.rating.controller;

import cz.upce.fei.nnpia.nnpia_sem.app.movie.entity.Movie;
import cz.upce.fei.nnpia.nnpia_sem.app.rating.dto.CreateUpdateRatingDto;
import cz.upce.fei.nnpia.nnpia_sem.app.rating.dto.MovieWithScoreDto;
import cz.upce.fei.nnpia.nnpia_sem.app.rating.entity.Rating;
import cz.upce.fei.nnpia.nnpia_sem.app.rating.service.RatingService;
import cz.upce.fei.nnpia.nnpia_sem.app.watchlist.dto.SearchWithUserIdDto;
import cz.upce.fei.nnpia.nnpia_sem.app.watchlist.dto.UserIdMovieIdDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/rating")
@CrossOrigin
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping("/rating")
    private Integer findRating(@RequestBody() UserIdMovieIdDto userIdMovieIdDto) {
        return this.ratingService.getRating(userIdMovieIdDto);
    }

    @PostMapping("/my-rating")
    private Page<MovieWithScoreDto> findUserRating(@RequestBody() SearchWithUserIdDto userIdDto, Pageable pageable) {
        return this.ratingService.getUserRating(userIdDto, pageable);
    }

    @GetMapping("/{id}")
    private Double findAvgRating(@PathVariable() Long id) {
        return this.ratingService.getAvgRating(id);
    }

    @GetMapping("/top")
    private List<Movie> findTopRatings() {
        return this.ratingService.getBestMovies();
    }

    @PostMapping("/delete")
    private void deleteRating(@RequestBody() UserIdMovieIdDto userIdMovieIdDto) {
        this.ratingService.deleteRating(userIdMovieIdDto);
    }

    @PostMapping("/add")
    private Rating addRating(@RequestBody() CreateUpdateRatingDto createUpdateRatingDto) {
        return this.ratingService.upsertRating(createUpdateRatingDto);
    }

    @PostMapping("/update")
    private void updateRating(@RequestBody() CreateUpdateRatingDto createUpdateRatingDto) {
        this.ratingService.upsertRating(createUpdateRatingDto);
    }
}
