package cz.upce.fei.nnpia.nnpia_sem.app.watchlist.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.upce.fei.nnpia.nnpia_sem.app.watchlist.dto.UserIdMovieIdDto;
import cz.upce.fei.nnpia.nnpia_sem.app.watchlist.service.WatchlistService;
import org.junit.Test;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {WatchlistController.class})
@ExtendWith(SpringExtension.class)
class WatchlistControllerTest {
    @Autowired
    private WatchlistController watchlistController;

    @MockBean
    private WatchlistService watchlistService;

    /**
     * Method under test: {@link WatchlistController#addMovieOnWatchList(UserIdMovieIdDto)}
     */
    @Test
    void testAddMovieOnWatchList() throws Exception {
        when(this.watchlistService.addMovieToWatchlist(any())).thenReturn(true);

        UserIdMovieIdDto userIdMovieIdDto = new UserIdMovieIdDto();
        userIdMovieIdDto.setMovieId(123L);
        userIdMovieIdDto.setUserId(123L);
        String content = (new ObjectMapper()).writeValueAsString(userIdMovieIdDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/watchlist/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.watchlistController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":200,\"status\":\"SUCCESS\",\"result\":true}"));
    }

    /**
     * Method under test: {@link WatchlistController#addMovieOnWatchList(UserIdMovieIdDto)}
     */
    @Test
    void testAddMovieOnWatchListNotFound() throws Exception {
        when(this.watchlistService.addMovieToWatchlist(any())).thenReturn(false);

        UserIdMovieIdDto userIdMovieIdDto = new UserIdMovieIdDto();
        userIdMovieIdDto.setMovieId(123L);
        userIdMovieIdDto.setUserId(123L);
        String content = (new ObjectMapper()).writeValueAsString(userIdMovieIdDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/watchlist/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.watchlistController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":200,\"status\":\"NOT_FOUND\",\"result\":false}"));
    }

    /**
     * Method under test: {@link WatchlistController#getIdsMoviesOnWatchlist(Long)}
     */
    @Test
    void testGetIdsMoviesOnWatchlist() throws Exception {
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);
        when(this.watchlistService.getUserMoviesIdsOnWatchlist(any())).thenReturn(ids);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/watchlist/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.watchlistController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":200,\"status\":\"SUCCESS\",\"result\":[1,2]}"));
    }


    /**
     * Method under test: {@link WatchlistController#removeMovieFromWatchList(UserIdMovieIdDto)}
     */
    @Test
    void testRemoveMovieFromWatchList() throws Exception {
        when(this.watchlistService.removeMovieFromWatchlist(any())).thenReturn(true);
        UserIdMovieIdDto userIdMovieIdDto = new UserIdMovieIdDto();
        userIdMovieIdDto.setMovieId(123L);
        userIdMovieIdDto.setUserId(123L);
        String content = (new ObjectMapper()).writeValueAsString(userIdMovieIdDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/watchlist/remove")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.watchlistController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":200,\"status\":\"SUCCESS\",\"result\":true}"));
    }

    /**
     * Method under test: {@link WatchlistController#removeMovieFromWatchList(UserIdMovieIdDto)}
     */
    @Test
    void testRemoveMovieFromWatchListNotFound() throws Exception {
        when(this.watchlistService.removeMovieFromWatchlist(any())).thenReturn(false);

        UserIdMovieIdDto userIdMovieIdDto = new UserIdMovieIdDto();
        userIdMovieIdDto.setMovieId(123L);
        userIdMovieIdDto.setUserId(123L);
        String content = (new ObjectMapper()).writeValueAsString(userIdMovieIdDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/watchlist/remove")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.watchlistController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":200,\"status\":\"NOT_FOUND\",\"result\":false}"));
    }
}

