package cz.upce.fei.nnpia.nnpia_sem.app.movie.controller;

import cz.upce.fei.nnpia.nnpia_sem.app.movie.entity.Movie;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/list")
    private Page<Movie> findAllMovies(Pageable pageable) {
        return movieService.findAllMovies(pageable);
    }

    @GetMapping("/{id}")
    private Movie findMovie(@PathVariable Long id) {
        return movieService.findMovie(id);
    }

    @GetMapping("/{id}/actors")
    private List<Object> findAllActorsByMovie(@PathVariable Long id) {
        return movieService.findAllActors(id);
    }

    @GetMapping("/{id}/crew")
    private List<Object> findAllCrewByMovie(@PathVariable Long id) {
        return movieService.findAllCrew(id);
    }
}
