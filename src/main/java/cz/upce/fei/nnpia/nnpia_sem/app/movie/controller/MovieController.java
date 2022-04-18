package cz.upce.fei.nnpia.nnpia_sem.app.movie.controller;

import cz.upce.fei.nnpia.nnpia_sem.app.config.ApiResponse;
import cz.upce.fei.nnpia.nnpia_sem.app.config.StausEnum;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.dto.*;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.entity.RandomMovieDto;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/movie")
@CrossOrigin
public class MovieController {

    @Autowired
    private MovieService movieService;

    @ExceptionHandler({Exception.class})
    public StausEnum handleException() {
        return StausEnum.NOT_FOUND;
    }

    @PostMapping("/list")
    public ApiResponse<Page<MovieListDto>> searchAllMovies(@RequestBody(required = false) SearchDto searchDto, Pageable pageable) {
        Page<MovieListDto> movieList = movieService.searchAllMovies(pageable, searchDto == null ? "" : searchDto.getQuery());
        return new ApiResponse<>(HttpStatus.OK.value(), StausEnum.SUCCESS, movieList);
    }

    @PostMapping("/add")
    public ApiResponse<Long> addMovie(@RequestBody AddMovieDto movieDto) {
        Long movieId = movieService.addMovie(movieDto);
        return new ApiResponse<>(HttpStatus.OK.value(), StausEnum.CREATED, movieId);
    }

    @PostMapping("/edit")
    public ApiResponse<Long> addMovie(@RequestBody EditMovieDto movieDto) {
        Long movieId = movieService.editMovie(movieDto);
        return new ApiResponse<>(HttpStatus.OK.value(), movieId != null ? StausEnum.SUCCESS : StausEnum.NOT_FOUND, movieId);
    }

    @PostMapping("/cast/add")
    public ApiResponse<Long> addMovieCast(@RequestBody AddMovieCastDto addMovieCastDto) {
        Long id = movieService.addMovieCast(addMovieCastDto);
        return new ApiResponse<>(HttpStatus.OK.value(), id != null ? StausEnum.SUCCESS : StausEnum.NOT_FOUND, id);
    }

    @PostMapping("/crew/add")
    public ApiResponse<Long> addMovieCast(@RequestBody AddMovieCrewDto addMovieCrewDto) {
        Long id = movieService.addMovieCrew(addMovieCrewDto);
        return new ApiResponse<>(HttpStatus.OK.value(), id != null ? StausEnum.SUCCESS : StausEnum.NOT_FOUND, id);
    }

    @GetMapping("/{id}")
    public ApiResponse<MovieDto> findMovie(@PathVariable Long id) {
        MovieDto movie = movieService.findMovie(id);
        return new ApiResponse<>(HttpStatus.OK.value(), movie != null ? StausEnum.SUCCESS : StausEnum.NOT_FOUND, movie);
    }

    @GetMapping("/{id}/actors")
    public ApiResponse<List<ActorDto>> findAllActorsByMovie(@PathVariable Long id) {
        List<ActorDto> allActors = movieService.findAllActors(id);
        return new ApiResponse<>(HttpStatus.OK.value(), allActors != null ? StausEnum.SUCCESS : StausEnum.NOT_FOUND, allActors);
    }

    @GetMapping("/{id}/crew")
    public ApiResponse<List<CrewDto>> findAllCrewByMovie(@PathVariable Long id) {
        List<CrewDto> allCrew = movieService.findAllCrew(id);
        return new ApiResponse<>(HttpStatus.OK.value(), allCrew != null ? StausEnum.SUCCESS : StausEnum.NOT_FOUND, allCrew);
    }

    @GetMapping("/ids")
    public ApiResponse<List<MovieIdTitleDto>> findAllMovieIds() {
        List<MovieIdTitleDto> allMoviesIds = movieService.findAllMoviesIdsTitles();
        return new ApiResponse<>(HttpStatus.OK.value(), StausEnum.SUCCESS, allMoviesIds);
    }

    @GetMapping("/random")
    public ApiResponse<RandomMovieDto> findRandomMovie() {
        RandomMovieDto randomMovie = movieService.findRandomMovie();
        return new ApiResponse<>(HttpStatus.OK.value(), StausEnum.SUCCESS, randomMovie);
    }

}
