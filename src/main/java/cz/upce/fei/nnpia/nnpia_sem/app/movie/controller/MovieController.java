package cz.upce.fei.nnpia.nnpia_sem.app.movie.controller;

import cz.upce.fei.nnpia.nnpia_sem.app.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/list")
    private void findAllMovies() {
    }

    @GetMapping("/{id}")
    private void findMovie(@PathVariable Long id) {
    }
}
