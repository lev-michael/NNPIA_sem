package cz.upce.fei.nnpia.nnpia_sem.app.rating.controller;

import cz.upce.fei.nnpia.nnpia_sem.app.rating.dto.CreateUpdateRatingDto;
import cz.upce.fei.nnpia.nnpia_sem.app.rating.entity.Rating;
import cz.upce.fei.nnpia.nnpia_sem.app.rating.service.RatingService;
import cz.upce.fei.nnpia.nnpia_sem.app.watchlist.dto.UserIdMovieIdDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @GetMapping("/")
    private Rating findRating(@RequestBody() UserIdMovieIdDto userIdMovieIdDto) {
        return this.ratingService.getRating(userIdMovieIdDto);
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
