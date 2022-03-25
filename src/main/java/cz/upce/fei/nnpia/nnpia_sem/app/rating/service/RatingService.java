package cz.upce.fei.nnpia.nnpia_sem.app.rating.service;

import cz.upce.fei.nnpia.nnpia_sem.app.movie.entity.Movie;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.repository.MovieRepository;
import cz.upce.fei.nnpia.nnpia_sem.app.rating.dto.CreateUpdateRatingDto;
import cz.upce.fei.nnpia.nnpia_sem.app.rating.entity.ComposedUserMovieId;
import cz.upce.fei.nnpia.nnpia_sem.app.rating.entity.Rating;
import cz.upce.fei.nnpia.nnpia_sem.app.rating.repository.RatingRepository;
import cz.upce.fei.nnpia.nnpia_sem.app.user.entity.User;
import cz.upce.fei.nnpia.nnpia_sem.app.user.repository.UserRepository;
import cz.upce.fei.nnpia.nnpia_sem.app.watchlist.dto.UserIdMovieIdDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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

    public void deleteRating(UserIdMovieIdDto userIdMovieIdDto) {
        ComposedUserMovieId id = new ComposedUserMovieId(userIdMovieIdDto.getMovieId(), userIdMovieIdDto.getUserId());
        this.ratingRepository.deleteById(id);
    }

    public Rating getRating(UserIdMovieIdDto userIdMovieIdDto) {
        ComposedUserMovieId id = new ComposedUserMovieId(userIdMovieIdDto.getMovieId(), userIdMovieIdDto.getUserId());
        Optional<Rating> result = ratingRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new RuntimeException("Rating not found");
    }

    public Rating upsertRating(CreateUpdateRatingDto createUpdateRatingDto) {
        Movie movie = movieRepository.getById(createUpdateRatingDto.getMovieId());
        User user = userRepository.getById(createUpdateRatingDto.getUserId());
        Rating rating = new Rating(createUpdateRatingDto.getScore(), movie, user);
        return ratingRepository.save(rating);
    }

    public double getAvgRating(Long movieId) {
        return ratingRepository.findAvgRating(movieId);
    }

    public List<Movie> getBestMovies() {
        return ratingRepository.findBestMovies(PageRequest.of(0, 10));
    }
}
