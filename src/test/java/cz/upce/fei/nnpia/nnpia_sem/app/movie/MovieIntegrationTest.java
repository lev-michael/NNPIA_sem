package cz.upce.fei.nnpia.nnpia_sem.app.movie;

import cz.upce.fei.nnpia.nnpia_sem.app.config.ApiResponse;
import cz.upce.fei.nnpia.nnpia_sem.app.config.StausEnum;
import cz.upce.fei.nnpia.nnpia_sem.app.genre.controller.GenreController;
import cz.upce.fei.nnpia.nnpia_sem.app.genre.entity.Genre;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.controller.MovieController;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.dto.AddMovieDto;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.dto.EditMovieDto;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.dto.MovieDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class MovieIntegrationTest {

    @Autowired
    private MovieController movieController;

    @Autowired
    private GenreController genreController;


    @Test
    void addAndEditMovie() {
        ApiResponse<List<Genre>> genresResponse = genreController.findAllGenres();
        Assertions.assertEquals(StausEnum.SUCCESS, genresResponse.getStatus());
        List<Genre> genres = genresResponse.getResult();

        AddMovieDto addMovieDto = new AddMovieDto();
        addMovieDto.setTitle("Test");
        addMovieDto.setRuntime(120);
        addMovieDto.setDescription("Description");
        addMovieDto.setRelease_date(new Date());
        List<String> originalGenres = new ArrayList<>();
        originalGenres.add(genres.get(1).getId().toString());
        originalGenres.add(genres.get(2).getId().toString());
        addMovieDto.setGenres(originalGenres);
        ApiResponse<Long> addMovieResponse = movieController.addMovie(addMovieDto);

        Assertions.assertEquals(StausEnum.CREATED, addMovieResponse.getStatus());

        EditMovieDto editMovieDto = new EditMovieDto();
        editMovieDto.setId(addMovieResponse.getResult());
        List<String> newGenres = new ArrayList<>();
        newGenres.add(genres.get(1).getId().toString());
        newGenres.add(genres.get(3).getId().toString());
        editMovieDto.setGenres(newGenres);
        editMovieDto.setDescription(addMovieDto.getDescription());
        editMovieDto.setRuntime(addMovieDto.getRuntime());
        editMovieDto.setTitle(addMovieDto.getTitle());
        editMovieDto.setRelease_date(addMovieDto.getRelease_date());
        ApiResponse<Long> editMovieResponse = movieController.editMovie(editMovieDto);

        Assertions.assertEquals(StausEnum.SUCCESS, editMovieResponse.getStatus());
        Assertions.assertEquals(addMovieResponse.getResult(), editMovieResponse.getResult());

        ApiResponse<MovieDto> movieResponse = movieController.findMovie(addMovieResponse.getResult());
        Assertions.assertEquals(StausEnum.SUCCESS, movieResponse.getStatus());
        MovieDto movieResponseResult = movieResponse.getResult();
        List<String> expectedGenres = new ArrayList<>();
        expectedGenres.add(genres.get(1).getName());
        expectedGenres.add(genres.get(3).getName());
        Assertions.assertEquals(expectedGenres, movieResponseResult.getGenres());
    }

}
