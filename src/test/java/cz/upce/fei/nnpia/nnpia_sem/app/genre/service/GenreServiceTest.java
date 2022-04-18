package cz.upce.fei.nnpia.nnpia_sem.app.genre.service;

import cz.upce.fei.nnpia.nnpia_sem.app.genre.entity.Genre;
import cz.upce.fei.nnpia.nnpia_sem.app.genre.entity.GenreMovies;
import cz.upce.fei.nnpia.nnpia_sem.app.genre.repository.GenreMovieRepository;
import cz.upce.fei.nnpia.nnpia_sem.app.genre.repository.GenreRepository;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.entity.Movie;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {GenreService.class})
@ExtendWith(SpringExtension.class)
class GenreServiceTest {
    @MockBean
    private GenreMovieRepository genreMovieRepository;

    @MockBean
    private GenreRepository genreRepository;

    @Autowired
    private GenreService genreService;

    /**
     * Method under test: {@link GenreService#getAllGenres()}
     */
    @Test
    void testGetAllGenres() {
        ArrayList<Genre> genreList = new ArrayList<>();
        when(this.genreRepository.findAll()).thenReturn(genreList);
        List<Genre> actualAllGenres = this.genreService.getAllGenres();
        assertSame(genreList, actualAllGenres);
        assertTrue(actualAllGenres.isEmpty());
        verify(this.genreRepository).findAll();
    }

    /**
     * Method under test: {@link GenreService#getGenre(Long)}
     */
    @Test
    void testGetGenre() {
        Genre genre = new Genre();
        genre.setId(123L);
        genre.setName("Name");
        Optional<Genre> ofResult = Optional.of(genre);
        when(this.genreRepository.findById(any())).thenReturn(ofResult);
        assertSame(genre, this.genreService.getGenre(123L));
        verify(this.genreRepository).findById(any());
    }

    /**
     * Method under test: {@link GenreService#addGenreToMovie(Genre, Movie)}
     */
    @Test
    void testAddGenreToMovie() {
        Genre genre = new Genre();
        genre.setId(123L);
        genre.setName("Name");

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

        GenreMovies genreMovies = new GenreMovies();
        genreMovies.setGenre(genre);
        genreMovies.setMovie(movie);
        when(this.genreMovieRepository.saveAndFlush(any())).thenReturn(genreMovies);

        Genre genre1 = new Genre();
        genre1.setId(123L);
        genre1.setName("Name");

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
        this.genreService.addGenreToMovie(genre1, movie1);
        verify(this.genreMovieRepository).saveAndFlush(any());
    }

    /**
     * Method under test: {@link GenreService#removeGenreFromMovie(Long, Long)}
     */
    @Test
    void testRemoveGenreFromMovie() {
        doNothing().when(this.genreMovieRepository).flush();
        doNothing().when(this.genreMovieRepository)
                .deleteById(any());
        this.genreService.removeGenreFromMovie(123L, 123L);
        verify(this.genreMovieRepository).flush();
        verify(this.genreMovieRepository)
                .deleteById(any());
    }
}

