package cz.upce.fei.nnpia.nnpia_sem.app.rating.controller;

import cz.upce.fei.nnpia.nnpia_sem.app.config.ApiResponse;
import cz.upce.fei.nnpia.nnpia_sem.app.config.StausEnum;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.dto.MovieListDto;
import cz.upce.fei.nnpia.nnpia_sem.app.rating.dto.CreateUpdateRatingDto;
import cz.upce.fei.nnpia.nnpia_sem.app.rating.dto.MovieWithScoreDto;
import cz.upce.fei.nnpia.nnpia_sem.app.rating.entity.Rating;
import cz.upce.fei.nnpia.nnpia_sem.app.rating.service.RatingService;
import cz.upce.fei.nnpia.nnpia_sem.app.watchlist.dto.SearchWithUserIdDto;
import cz.upce.fei.nnpia.nnpia_sem.app.watchlist.dto.UserIdMovieIdDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/rating")
@CrossOrigin
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping("/rating")
    private ApiResponse<Integer> findRating(@Valid @RequestBody() UserIdMovieIdDto userIdMovieIdDto) {
        Integer rating = ratingService.getRating(userIdMovieIdDto);
        return new ApiResponse<>(HttpStatus.OK.value(), StausEnum.SUCCESS, rating);
    }

    @PostMapping("/my-rating")
    private ApiResponse<Page<MovieWithScoreDto>> findUserRating(@RequestBody() SearchWithUserIdDto userIdDto, Pageable pageable) {
        Page<MovieWithScoreDto> userRating = this.ratingService.getUserRating(userIdDto, pageable);
        return new ApiResponse<>(HttpStatus.OK.value(), StausEnum.SUCCESS, userRating);
    }

    @GetMapping("/{id}")
    private ApiResponse<Double> findAvgRating(@PathVariable() Long id) {
        double avgRating = this.ratingService.getAvgRating(id);
        return new ApiResponse<>(HttpStatus.OK.value(), StausEnum.SUCCESS, avgRating);
    }

    @GetMapping("/best")
    private ApiResponse<List<MovieListDto>> findBestRatings() {
        List<MovieListDto> bestMovies = this.ratingService.getBestMovies();
        return new ApiResponse<>(HttpStatus.OK.value(), StausEnum.SUCCESS, bestMovies);
    }

    @GetMapping("/worst")
    private ApiResponse<List<MovieListDto>> findWorstRatings() {
        List<MovieListDto> worstMovies = this.ratingService.getWorstMovies();
        return new ApiResponse<>(HttpStatus.OK.value(), StausEnum.SUCCESS, worstMovies);
    }

    @PostMapping("/add")
    private ApiResponse<Rating> addRating(@RequestBody() CreateUpdateRatingDto createUpdateRatingDto) {
        Rating rating = this.ratingService.upsertRating(createUpdateRatingDto);
        return new ApiResponse<>(HttpStatus.OK.value(), rating != null ? StausEnum.CREATED : StausEnum.NOT_FOUND, rating);
    }

    @PostMapping("/update")
    private ApiResponse<Rating> updateRating(@RequestBody() CreateUpdateRatingDto createUpdateRatingDto) {
        Rating rating = this.ratingService.upsertRating(createUpdateRatingDto);
        return new ApiResponse<>(HttpStatus.OK.value(), rating != null ? StausEnum.SUCCESS : StausEnum.NOT_FOUND, rating);
    }
}
