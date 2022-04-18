package cz.upce.fei.nnpia.nnpia_sem.app.watchlist.service;

import cz.upce.fei.nnpia.nnpia_sem.app.movie.entity.Movie;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.repository.MovieRepository;
import cz.upce.fei.nnpia.nnpia_sem.app.user.entity.Roles;
import cz.upce.fei.nnpia.nnpia_sem.app.user.entity.User;
import cz.upce.fei.nnpia.nnpia_sem.app.user.repository.UserRepository;
import cz.upce.fei.nnpia.nnpia_sem.app.watchlist.dto.UserIdMovieIdDto;
import cz.upce.fei.nnpia.nnpia_sem.app.watchlist.entity.Watchlist;
import cz.upce.fei.nnpia.nnpia_sem.app.watchlist.repository.WatchlistRepository;
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

@ContextConfiguration(classes = {WatchlistService.class})
@ExtendWith(SpringExtension.class)
class WatchlistServiceTest {
    @MockBean
    private MovieRepository movieRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private WatchlistRepository watchlistRepository;

    @Autowired
    private WatchlistService watchlistService;

    /**
     * Method under test: {@link WatchlistService#getUserMoviesIdsOnWatchlist(Long)}
     */
    @Test
    void testGetUserMoviesIdsOnWatchlist() {
        ArrayList<Long> resultLongList = new ArrayList<>();
        when(this.watchlistRepository.findAllIdsByUser_Id(any())).thenReturn(resultLongList);
        List<Long> actualUserMoviesIdsOnWatchlist = this.watchlistService.getUserMoviesIdsOnWatchlist(123L);
        assertSame(resultLongList, actualUserMoviesIdsOnWatchlist);
        assertTrue(actualUserMoviesIdsOnWatchlist.isEmpty());
        verify(this.watchlistRepository).findAllIdsByUser_Id(any());
    }

    /**
     * Method under test: {@link WatchlistService#addMovieToWatchlist(UserIdMovieIdDto)}
     */
    @Test
    void testAddMovieToWatchlist() {
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

        Watchlist watchlist = new Watchlist();
        watchlist.setMovie(movie);
        watchlist.setUser(user);
        when(this.watchlistRepository.save(any())).thenReturn(watchlist);

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
        Optional<User> ofResult = Optional.of(user1);
        when(this.userRepository.findById(any())).thenReturn(ofResult);

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
        assertTrue(this.watchlistService.addMovieToWatchlist(new UserIdMovieIdDto()));
        verify(this.watchlistRepository).save(any());
        verify(this.userRepository).findById(any());
        verify(this.movieRepository).findById(any());
    }

    /**
     * Method under test: {@link WatchlistService#addMovieToWatchlist(UserIdMovieIdDto)}
     */
    @Test
    void testAddMovieToWatchlist2() {
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

        Watchlist watchlist = new Watchlist();
        watchlist.setMovie(movie);
        watchlist.setUser(user);
        when(this.watchlistRepository.save(any())).thenReturn(watchlist);
        when(this.userRepository.findById(any())).thenReturn(Optional.empty());

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
        assertFalse(this.watchlistService.addMovieToWatchlist(new UserIdMovieIdDto()));
        verify(this.userRepository).findById(any());
        verify(this.movieRepository).findById(any());
    }

    /**
     * Method under test: {@link WatchlistService#removeMovieFromWatchlist(UserIdMovieIdDto)}
     */
    @Test
    void testRemoveMovieFromWatchlist() {
        doNothing().when(this.watchlistRepository).delete(any());

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
        Optional<Movie> ofResult1 = Optional.of(movie);
        when(this.movieRepository.findById(any())).thenReturn(ofResult1);
        assertTrue(this.watchlistService.removeMovieFromWatchlist(new UserIdMovieIdDto()));
        verify(this.watchlistRepository).delete(any());
        verify(this.userRepository).findById(any());
        verify(this.movieRepository).findById(any());
    }

    /**
     * Method under test: {@link WatchlistService#removeMovieFromWatchlist(UserIdMovieIdDto)}
     */
    @Test
    void testRemoveMovieFromWatchlist3() {
        doNothing().when(this.watchlistRepository)
                .delete(any());
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
        Optional<Movie> ofResult = Optional.of(movie);
        when(this.movieRepository.findById(any())).thenReturn(ofResult);
        assertFalse(this.watchlistService.removeMovieFromWatchlist(new UserIdMovieIdDto()));
        verify(this.userRepository).findById(any());
        verify(this.movieRepository).findById(any());
    }

    /**
     * Method under test: {@link WatchlistService#removeMovieFromWatchlist(UserIdMovieIdDto)}
     */
    @Test
    void testRemoveMovieFromWatchlist2() {
        doNothing().when(this.watchlistRepository).delete(any());

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
        when(this.movieRepository.findById(any())).thenReturn(Optional.empty());
        assertFalse(this.watchlistService.removeMovieFromWatchlist(new UserIdMovieIdDto()));
        verify(this.userRepository).findById(any());
        verify(this.movieRepository).findById(any());
    }

    /**
     * Method under test: {@link WatchlistService#getUserMoviesOnWatchlist(Long, org.springframework.data.domain.Pageable, String)}
     */
    @Test
    void testGetUserMoviesOnWatchlist() {
        PageImpl<Movie> pageImpl = new PageImpl<>(new ArrayList<>());
        when(this.watchlistRepository.findAllMoviesByUser_Id(any(), any(),
                any())).thenReturn(pageImpl);
        assertSame(pageImpl, this.watchlistService.getUserMoviesOnWatchlist(123L, PageRequest.of(1, 3), "Query"));
        verify(this.watchlistRepository).findAllMoviesByUser_Id(any(),
                any(), any());
    }
}

