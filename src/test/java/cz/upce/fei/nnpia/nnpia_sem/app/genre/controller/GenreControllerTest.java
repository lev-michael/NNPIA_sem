package cz.upce.fei.nnpia.nnpia_sem.app.genre.controller;

import cz.upce.fei.nnpia.nnpia_sem.app.genre.entity.Genre;
import cz.upce.fei.nnpia.nnpia_sem.app.genre.service.GenreService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {GenreController.class})
@ExtendWith(SpringExtension.class)
class GenreControllerTest {
    @Autowired
    private GenreController genreController;

    @MockBean
    private GenreService genreService;

    /**
     * Method under test: {@link GenreController#findAllGenres()}
     */
    @Test
    void testFindAllGenresEmptyArray() throws Exception {
        List<Genre> genres = new ArrayList<>();
        Genre actionGenre = new Genre();
        actionGenre.setId(1L);
        actionGenre.setName("Action");
        Genre comedyGenre = new Genre();
        comedyGenre.setId(2L);
        comedyGenre.setName("Comedy");
        genres.add(actionGenre);
        genres.add(comedyGenre);
        when(this.genreService.getAllGenres()).thenReturn(genres);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/genre/list");
        MockMvcBuilders.standaloneSetup(this.genreController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":200,\"status\":\"SUCCESS\",\"result\":[{\"id\":1,\"name\":\"Action\"},{\"id\":2,\"name\":\"Comedy\"}]}"));
    }

    /**
     * Method under test: {@link GenreController#findAllGenres()}
     */
    @Test
    void testFindAllGenresNonEmptyGenres() throws Exception {
        List<Genre> genres = new ArrayList<>();
        genres.add(new Genre(1L, "Action"));
        genres.add(new Genre(2L, "Comedy"));
        when(this.genreService.getAllGenres()).thenReturn(genres);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/genre/list");
        MockMvcBuilders.standaloneSetup(this.genreController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":200,\"status\":\"SUCCESS\",\"result\":[{\"id\":1,\"name\":\"Action\"},{\"id\":2,\"name\":\"Comedy\"}]}"));
    }
}

