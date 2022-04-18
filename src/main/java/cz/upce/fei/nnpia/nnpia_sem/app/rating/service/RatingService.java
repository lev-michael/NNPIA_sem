package cz.upce.fei.nnpia.nnpia_sem.app.rating.service;

import cz.upce.fei.nnpia.nnpia_sem.app.movie.dto.MovieListDto;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.entity.Movie;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.repository.MovieRepository;
import cz.upce.fei.nnpia.nnpia_sem.app.rating.dto.CreateUpdateRatingDto;
import cz.upce.fei.nnpia.nnpia_sem.app.rating.dto.MovieWithScoreDto;
import cz.upce.fei.nnpia.nnpia_sem.app.rating.entity.ComposedUserMovieId;
import cz.upce.fei.nnpia.nnpia_sem.app.rating.entity.Rating;
import cz.upce.fei.nnpia.nnpia_sem.app.rating.repository.RatingRepository;
import cz.upce.fei.nnpia.nnpia_sem.app.user.entity.User;
import cz.upce.fei.nnpia.nnpia_sem.app.user.repository.UserRepository;
import cz.upce.fei.nnpia.nnpia_sem.app.watchlist.dto.SearchWithUserIdDto;
import cz.upce.fei.nnpia.nnpia_sem.app.watchlist.dto.UserIdMovieIdDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    public Integer getRating(UserIdMovieIdDto userIdMovieIdDto) {
        ComposedUserMovieId id = new ComposedUserMovieId(userIdMovieIdDto.getMovieId(), userIdMovieIdDto.getUserId());
        Optional<Rating> result = ratingRepository.findById(id);
        return result.map(Rating::getScore).orElse(0);
    }

    public Rating upsertRating(CreateUpdateRatingDto createUpdateRatingDto) {
        Movie movie = movieRepository.findById(createUpdateRatingDto.getMovieId()).orElse(null);
        User user = userRepository.findById(createUpdateRatingDto.getUserId()).orElse(null);
        if (user == null || movie == null) {
            return null;
        }
        Rating rating = new Rating(createUpdateRatingDto.getScore(), movie, user);
        return ratingRepository.save(rating);
    }

    public double getAvgRating(Long movieId) {
        Double rating = ratingRepository.findAvgRating(movieId);
        if (rating == null) {
            return 0;
        }
        return rating;
    }

    public List<MovieListDto> getBestMovies() {
        return ratingRepository.findBestMovies(PageRequest.of(0, 10));
    }

    public List<MovieListDto> getWorstMovies() {
        return ratingRepository.findWorstMovies(PageRequest.of(0, 10));
    }

    public Page<MovieWithScoreDto> getUserRating(SearchWithUserIdDto userIdDto, Pageable pageable) {
        return ratingRepository.findAllByUserId(userIdDto.getUserId(), userIdDto.getQuery().toLowerCase(), pageable);
    }
}
