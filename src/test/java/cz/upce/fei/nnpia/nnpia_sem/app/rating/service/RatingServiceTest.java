package cz.upce.fei.nnpia.nnpia_sem.app.rating.service;

import cz.upce.fei.nnpia.nnpia_sem.app.movie.dto.MovieListDto;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.entity.Movie;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.repository.MovieRepository;
import cz.upce.fei.nnpia.nnpia_sem.app.rating.dto.CreateUpdateRatingDto;
import cz.upce.fei.nnpia.nnpia_sem.app.rating.dto.MovieWithScoreDto;
import cz.upce.fei.nnpia.nnpia_sem.app.rating.entity.Rating;
import cz.upce.fei.nnpia.nnpia_sem.app.rating.repository.RatingRepository;
import cz.upce.fei.nnpia.nnpia_sem.app.user.entity.Roles;
import cz.upce.fei.nnpia.nnpia_sem.app.user.entity.User;
import cz.upce.fei.nnpia.nnpia_sem.app.user.repository.UserRepository;
import cz.upce.fei.nnpia.nnpia_sem.app.watchlist.dto.SearchWithUserIdDto;
import cz.upce.fei.nnpia.nnpia_sem.app.watchlist.dto.UserIdMovieIdDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {RatingService.class})
@ExtendWith(SpringExtension.class)
class RatingServiceTest {
    @MockBean
    private MovieRepository movieRepository;

    @MockBean
    private RatingRepository ratingRepository;

    @Autowired
    private RatingService ratingService;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link RatingService#getRating(UserIdMovieIdDto)}
     */
    @Test
    void testGetRating() {
        Movie movie = new Movie();
        movie.setActors(new ArrayList<>());
        movie.setCrew(new ArrayList<>());
        movie.setDescription("The characteristics of someone or something");
        movie.setGenres(new ArrayList<>());
        movie.setId(123L);
        movie.setImg("Img");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        movie.setRelease_date(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        movie.setRuntime(1);
        movie.setScores(new ArrayList<>());
        movie.setTitle("Dr");

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setRatings(new ArrayList<>());
        user.setRole(Roles.BASIC);
        user.setUserName("janedoe");
        user.setWatchlist(new ArrayList<>());

        Rating rating = new Rating();
        rating.setMovie(movie);
        rating.setScore(3);
        rating.setUser(user);
        Optional<Rating> ofResult = Optional.of(rating);
        when(this.ratingRepository.findById(any()))
                .thenReturn(ofResult);

        UserIdMovieIdDto userIdMovieIdDto = new UserIdMovieIdDto(123L, 123L);
        userIdMovieIdDto.setMovieId(1L);
        assertEquals(3, this.ratingService.getRating(userIdMovieIdDto).intValue());
        verify(this.ratingRepository).findById(any());
    }

    /**
     * Method under test: {@link RatingService#upsertRating(CreateUpdateRatingDto)}
     */
    @Test
    void testUpsertRating() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setRatings(new ArrayList<>());
        user.setRole(Roles.BASIC);
        user.setUserName("janedoe");
        user.setWatchlist(new ArrayList<>());
        Optional<User> ofResult = Optional.of(user);
        when(this.userRepository.findById(any())).thenReturn(ofResult);

        Movie movie = new Movie();
        movie.setActors(new ArrayList<>());
        movie.setCrew(new ArrayList<>());
        movie.setDescription("The characteristics of someone or something");
        movie.setGenres(new ArrayList<>());
        movie.setId(123L);
        movie.setImg("Img");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        movie.setRelease_date(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        movie.setRuntime(1);
        movie.setScores(new ArrayList<>());
        movie.setTitle("Dr");

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(123L);
        user1.setLastName("Doe");
        user1.setPassword("iloveyou");
        user1.setRatings(new ArrayList<>());
        user1.setRole(Roles.BASIC);
        user1.setUserName("janedoe");
        user1.setWatchlist(new ArrayList<>());

        Rating rating = new Rating();
        rating.setMovie(movie);
        rating.setScore(3);
        rating.setUser(user1);
        when(this.ratingRepository.save(any())).thenReturn(rating);

        Movie movie1 = new Movie();
        movie1.setActors(new ArrayList<>());
        movie1.setCrew(new ArrayList<>());
        movie1.setDescription("The characteristics of someone or something");
        movie1.setGenres(new ArrayList<>());
        movie1.setId(123L);
        movie1.setImg("Img");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        movie1.setRelease_date(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        movie1.setRuntime(1);
        movie1.setScores(new ArrayList<>());
        movie1.setTitle("Dr");
        Optional<Movie> ofResult1 = Optional.of(movie1);
        when(this.movieRepository.findById(any())).thenReturn(ofResult1);

        CreateUpdateRatingDto createUpdateRatingDto = new CreateUpdateRatingDto();
        createUpdateRatingDto.setMovieId(123L);
        createUpdateRatingDto.setScore(3);
        createUpdateRatingDto.setUserId(123L);
        assertSame(rating, this.ratingService.upsertRating(createUpdateRatingDto));
        verify(this.userRepository).findById(any());
        verify(this.ratingRepository).save(any());
        verify(this.movieRepository).findById(any());
    }

    /**
     * Method under test: {@link RatingService#upsertRating(CreateUpdateRatingDto)}
     */
    @Test
    void testUpsertRating1() {
        when(this.userRepository.findById(any())).thenReturn(Optional.empty());

        Movie movie = new Movie();
        movie.setActors(new ArrayList<>());
        movie.setCrew(new ArrayList<>());
        movie.setDescription("The characteristics of someone or something");
        movie.setGenres(new ArrayList<>());
        movie.setId(123L);
        movie.setImg("Img");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        movie.setRelease_date(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        movie.setRuntime(1);
        movie.setScores(new ArrayList<>());
        movie.setTitle("Dr");

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setLastName("Doe");
        user.setPassword("iloveyou");
        user.setRatings(new ArrayList<>());
        user.setRole(Roles.BASIC);
        user.setUserName("janedoe");
        user.setWatchlist(new ArrayList<>());

        Rating rating = new Rating();
        rating.setMovie(movie);
        rating.setScore(3);
        rating.setUser(user);
        when(this.ratingRepository.save(any())).thenReturn(rating);

        Movie movie1 = new Movie();
        movie1.setActors(new ArrayList<>());
        movie1.setCrew(new ArrayList<>());
        movie1.setDescription("The characteristics of someone or something");
        movie1.setGenres(new ArrayList<>());
        movie1.setId(123L);
        movie1.setImg("Img");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        movie1.setRelease_date(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        movie1.setRuntime(1);
        movie1.setScores(new ArrayList<>());
        movie1.setTitle("Dr");
        Optional<Movie> ofResult = Optional.of(movie1);
        when(this.movieRepository.findById(any())).thenReturn(ofResult);

        CreateUpdateRatingDto createUpdateRatingDto = new CreateUpdateRatingDto();
        createUpdateRatingDto.setMovieId(123L);
        createUpdateRatingDto.setScore(3);
        createUpdateRatingDto.setUserId(123L);
        assertNull(this.ratingService.upsertRating(createUpdateRatingDto));
        verify(this.userRepository).findById(any());
        verify(this.movieRepository).findById(any());
    }

    /**
     * Method under test: {@link RatingService#getAvgRating(Long)}
     */
    @Test
    void testGetAvgRating() {
        when(this.ratingRepository.findAvgRating(any())).thenReturn(10.0d);
        assertEquals(10.0d, this.ratingService.getAvgRating(123L));
        verify(this.ratingRepository).findAvgRating(any());
    }

    /**
     * Method under test: {@link RatingService#getAvgRating(Long)}
     */
    @Test
    void testGetAvgRating2() {
        when(this.ratingRepository.findAvgRating(any())).thenReturn(null);
        assertEquals(0.0d, this.ratingService.getAvgRating(123L));
        verify(this.ratingRepository).findAvgRating(any());
    }

    /**
     * Method under test: {@link RatingService#getBestMovies()}
     */
    @Test
    void testGetBestMovies() {
        ArrayList<MovieListDto> movieListDtoList = new ArrayList<>();
        when(this.ratingRepository.findBestMovies(any()))
                .thenReturn(movieListDtoList);
        List<MovieListDto> actualBestMovies = this.ratingService.getBestMovies();
        assertSame(movieListDtoList, actualBestMovies);
        assertTrue(actualBestMovies.isEmpty());
        verify(this.ratingRepository).findBestMovies(any());
    }

    /**
     * Method under test: {@link RatingService#getWorstMovies()}
     */
    @Test
    void testGetWorstMovies() {
        ArrayList<MovieListDto> movieListDtoList = new ArrayList<>();
        when(this.ratingRepository.findWorstMovies(any()))
                .thenReturn(movieListDtoList);
        List<MovieListDto> actualWorstMovies = this.ratingService.getWorstMovies();
        assertSame(movieListDtoList, actualWorstMovies);
        assertTrue(actualWorstMovies.isEmpty());
        verify(this.ratingRepository).findWorstMovies(any());
    }

    /**
     * Method under test: {@link RatingService#getUserRating(SearchWithUserIdDto, org.springframework.data.domain.Pageable)}
     */
    @Test
    void testGetUserRating() {
        PageImpl<MovieWithScoreDto> pageImpl = new PageImpl<>(new ArrayList<>());
        when(this.ratingRepository.findAllByUserId(any(), any(),
                any())).thenReturn(pageImpl);

        SearchWithUserIdDto searchWithUserIdDto = new SearchWithUserIdDto();
        searchWithUserIdDto.setQuery("Query");
        searchWithUserIdDto.setUserId(123L);
        assertSame(pageImpl, this.ratingService.getUserRating(searchWithUserIdDto, PageRequest.of(1, 3)));
        verify(this.ratingRepository).findAllByUserId(any(), any(),
                any());
    }
}

