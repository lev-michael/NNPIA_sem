package cz.upce.fei.nnpia.nnpia_sem.app.movie.controller;

import cz.upce.fei.nnpia.nnpia_sem.app.movie.dto.*;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.entity.Movie;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/movie")
@CrossOrigin
public class MovieController {

    @Autowired
    private MovieService movieService;


    @PostMapping("/list")
    private Page<MovieListDto> searchAllMovies(@RequestBody(required = false) SearchDto searchDto, Pageable pageable) {
        return movieService.searchAllMovies(pageable, searchDto == null ? "" : searchDto.getQuery());
    }

    @PostMapping("/add")
    private Movie addMovie(@RequestBody AddMovieDto movieDto) {
        return movieService.addMovie(movieDto);
    }

    @GetMapping("/{id}")
    private MovieDto findMovie(@PathVariable Long id) {
        return movieService.findMovie(id);
    }

    @GetMapping("/{id}/actors")
    private List<ActorDto> findAllActorsByMovie(@PathVariable Long id) {
        return movieService.findAllActors(id);
    }

    @GetMapping("/{id}/crew")
    private List<CrewDto> findAllCrewByMovie(@PathVariable Long id) {
        return movieService.findAllCrew(id);
    }
}
