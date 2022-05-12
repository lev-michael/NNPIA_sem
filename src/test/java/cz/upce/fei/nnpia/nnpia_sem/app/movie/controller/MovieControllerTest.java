package cz.upce.fei.nnpia.nnpia_sem.app.movie.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.dto.*;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.service.MovieService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {MovieController.class})
@ExtendWith(SpringExtension.class)
class MovieControllerTest {
    @Autowired
    private MovieController movieController;

    @MockBean
    private MovieService movieService;

    /**
     * Method under test: {@link MovieController#addMovie(AddMovieDto)}
     */
    @Test
    void testAddMovie() throws Exception {
        when(this.movieService.addMovie(any())).thenReturn(1L);

        AddMovieDto addMovieDto = new AddMovieDto();
        addMovieDto.setDescription("The characteristics of someone or something");
        addMovieDto.setGenres(new ArrayList<>());
        addMovieDto.setImg("Img");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        addMovieDto.setRelease_date(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        addMovieDto.setRuntime(1);
        addMovieDto.setTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(addMovieDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/movie/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.movieController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":200,\"status\":\"CREATED\",\"result\":1}"));
    }

    /**
     * Method under test: {@link MovieController#addMovieCast(AddMovieCastDto)}
     */
    @Test
    void testAddMovieCastSuccess() throws Exception {
        when(this.movieService.addMovieCast(any())).thenReturn(1L);

        AddMovieCastDto addMovieCastDto = new AddMovieCastDto();
        addMovieCastDto.setCast_order(1);
        addMovieCastDto.setCharacter_name("Character name");
        addMovieCastDto.setMovie_id(1L);
        addMovieCastDto.setPerson_id(1L);
        String content = (new ObjectMapper()).writeValueAsString(addMovieCastDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/movie/cast/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.movieController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":200,\"status\":\"SUCCESS\",\"result\":1}"));
    }

    /**
     * Method under test: {@link MovieController#addMovieCast(AddMovieCastDto)}
     */
    @Test
    void testAddMovieCastNotFound() throws Exception {
        when(this.movieService.addMovieCast(any())).thenReturn(null);

        AddMovieCastDto addMovieCastDto = new AddMovieCastDto();
        addMovieCastDto.setCast_order(1);
        addMovieCastDto.setCharacter_name("Character name");
        addMovieCastDto.setMovie_id(1L);
        addMovieCastDto.setPerson_id(1L);
        String content = (new ObjectMapper()).writeValueAsString(addMovieCastDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/movie/cast/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.movieController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":200,\"status\":\"NOT_FOUND\",\"result\":null}"));
    }

    /**
     * Method under test: {@link MovieController#findMovie(Long)}
     */
    @Test
    void testFindMovieSuccess() throws Exception {
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date release_date = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        ArrayList<String> genres = new ArrayList<>();
        ArrayList<ActorDto> actors = new ArrayList<>();
        when(this.movieService.findMovie(any()))
                .thenReturn(new MovieDto(123L, "Dr", "The characteristics of someone or something", "Img", 1, 10.0d,
                        release_date, genres, actors, new ArrayList<>()));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/movie/{id}", 123L);
        ResultActions resultActions = MockMvcBuilders.standaloneSetup(this.movieController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"));
        ContentResultMatchers contentResult = MockMvcResultMatchers.content();
        resultActions.andExpect(contentResult.string(String.join("",
                "{\"code\":200,\"status\":\"SUCCESS\",\"result\":{\"id\":123,\"title\":\"Dr\",\"description\":\"The characteristics of"
                        + " someone or something\",\"img\":\"Img\",\"runtime\":1,\"avgScore\":10.0,\"",
                System.getProperty("jdk.debug"), "_date\":0,\"genres\":[],\"actors\":[],\"crew\":[]}}")));
    }

    /**
     * Method under test: {@link MovieController#findMovie(Long)}
     */
    @Test
    void testFindMovieNotFound() throws Exception {
        when(this.movieService.findMovie(any())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/movie/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.movieController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":200,\"status\":\"NOT_FOUND\",\"result\":null}"));
    }

    @Test
    void testAddMovieSuccess() throws Exception {
        when(this.movieService.editMovie(any())).thenReturn(1L);
        EditMovieDto editMovieDto = new EditMovieDto();
        editMovieDto.setDescription("The characteristics of someone or something");
        editMovieDto.setGenres(new ArrayList<>());
        editMovieDto.setId(123L);
        editMovieDto.setImg("Img");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        editMovieDto.setRelease_date(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        editMovieDto.setRuntime(1);
        editMovieDto.setTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(editMovieDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/movie/edit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.movieController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":200,\"status\":\"SUCCESS\",\"result\":1}"));
    }

    /**
     * Method under test: {@link MovieController#addMovieCast(AddMovieCrewDto)}
     */
    @Test
    void testAddMovieCrewSuccess() throws Exception {
        when(this.movieService.addMovieCrew(any())).thenReturn(1L);

        AddMovieCrewDto addMovieCrewDto = new AddMovieCrewDto();
        addMovieCrewDto.setMovie_id(1L);
        addMovieCrewDto.setPerson_id(1L);
        addMovieCrewDto.setRole("Role");
        String content = (new ObjectMapper()).writeValueAsString(addMovieCrewDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/movie/crew/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.movieController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":200,\"status\":\"SUCCESS\",\"result\":1}"));
    }

    /**
     * Method under test: {@link MovieController#addMovieCast(AddMovieCrewDto)}
     */
    @Test
    void testAddMovieCrewNotFound() throws Exception {
        when(this.movieService.addMovieCrew(any())).thenReturn(null);

        AddMovieCrewDto addMovieCrewDto = new AddMovieCrewDto();
        addMovieCrewDto.setMovie_id(1L);
        addMovieCrewDto.setPerson_id(1L);
        addMovieCrewDto.setRole("Role");
        String content = (new ObjectMapper()).writeValueAsString(addMovieCrewDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/movie/crew/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.movieController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":200,\"status\":\"NOT_FOUND\",\"result\":null}"));
    }

}

