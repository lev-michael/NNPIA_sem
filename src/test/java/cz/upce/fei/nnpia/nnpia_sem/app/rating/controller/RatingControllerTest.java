package cz.upce.fei.nnpia.nnpia_sem.app.rating.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.entity.Movie;
import cz.upce.fei.nnpia.nnpia_sem.app.rating.dto.CreateUpdateRatingDto;
import cz.upce.fei.nnpia.nnpia_sem.app.rating.entity.Rating;
import cz.upce.fei.nnpia.nnpia_sem.app.rating.service.RatingService;
import cz.upce.fei.nnpia.nnpia_sem.app.user.entity.Roles;
import cz.upce.fei.nnpia.nnpia_sem.app.user.entity.User;
import cz.upce.fei.nnpia.nnpia_sem.app.watchlist.dto.UserIdMovieIdDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {RatingController.class})
@ExtendWith(SpringExtension.class)
class RatingControllerTest {

    @Autowired
    private RatingController ratingController;

    @MockBean
    private RatingService ratingService;

    /**
     * Method under test: {@link RatingController#addRating(CreateUpdateRatingDto)}
     */
    @Test
    void testAddRating() throws Exception {
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
        when(this.ratingService.upsertRating(any())).thenReturn(rating);

        CreateUpdateRatingDto createUpdateRatingDto = new CreateUpdateRatingDto();
        createUpdateRatingDto.setMovieId(123L);
        createUpdateRatingDto.setScore(3);
        createUpdateRatingDto.setUserId(123L);
        String content = (new ObjectMapper()).writeValueAsString(createUpdateRatingDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/rating/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.ratingController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"code\":200,\"status\":\"CREATED\",\"result\":{\"score\":3}}"));
    }

    /**
     * Method under test: {@link RatingController#findAvgRating(Long)}
     */
    @Test
    void testFindAvgRating() throws Exception {
        when(this.ratingService.getAvgRating(any())).thenReturn(10.0d);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/rating/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.ratingController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":200,\"status\":\"SUCCESS\",\"result\":10.0}"));
    }

    /**
     * Method under test: {@link RatingController#findAvgRating(Long)}
     */
    @Test
    void testFindAvgRating2() throws Exception {
        when(this.ratingService.getAvgRating((Long) any())).thenReturn(10.0d);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/rating/{id}", 123L);
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(this.ratingController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":200,\"status\":\"SUCCESS\",\"result\":10.0}"));
    }

    /**
     * Method under test: {@link RatingController#findBestRatings()}
     */
    @Test
    void testFindBestRatings() throws Exception {
        when(this.ratingService.getBestMovies()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/rating/best");
        MockMvcBuilders.standaloneSetup(this.ratingController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":200,\"status\":\"SUCCESS\",\"result\":[]}"));
    }

    /**
     * Method under test: {@link RatingController#findBestRatings()}
     */
    @Test
    void testFindBestRatings2() throws Exception {
        when(this.ratingService.getBestMovies()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/rating/best");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(this.ratingController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":200,\"status\":\"SUCCESS\",\"result\":[]}"));
    }

    /**
     * Method under test: {@link RatingController#findRating(UserIdMovieIdDto)}
     */
    @Test
    void testFindRating() throws Exception {
        when(this.ratingService.getRating(any())).thenReturn(1);

        UserIdMovieIdDto userIdMovieIdDto = new UserIdMovieIdDto();
        userIdMovieIdDto.setMovieId(123L);
        userIdMovieIdDto.setUserId(123L);
        String content = (new ObjectMapper()).writeValueAsString(userIdMovieIdDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/rating/rating")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.ratingController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":200,\"status\":\"SUCCESS\",\"result\":1}"));
    }

    /**
     * Method under test: {@link RatingController#findWorstRatings()}
     */
    @Test
    void testFindWorstRatings() throws Exception {
        when(this.ratingService.getWorstMovies()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/rating/worst");
        MockMvcBuilders.standaloneSetup(this.ratingController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":200,\"status\":\"SUCCESS\",\"result\":[]}"));
    }

    /**
     * Method under test: {@link RatingController#updateRating(CreateUpdateRatingDto)}
     */
    @Test
    void testUpdateRating() throws Exception {
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
        when(this.ratingService.upsertRating(any())).thenReturn(rating);

        CreateUpdateRatingDto createUpdateRatingDto = new CreateUpdateRatingDto();
        createUpdateRatingDto.setMovieId(123L);
        createUpdateRatingDto.setScore(3);
        createUpdateRatingDto.setUserId(123L);
        String content = (new ObjectMapper()).writeValueAsString(createUpdateRatingDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/rating/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.ratingController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"code\":200,\"status\":\"SUCCESS\",\"result\":{\"score\":3}}"));
    }
}

