package cz.upce.fei.nnpia.nnpia_sem.app.movie.service;

import cz.upce.fei.nnpia.nnpia_sem.app.movie.entity.Movie;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public Page<Movie> findAllMovies(Pageable pageable) {
        return movieRepository.findAll(pageable);
    }

    public Movie findMovie(Long id) {
        return movieRepository.getById(id).orElse(null);
    }

    public List<Object> findAllActors(Long id) {
        return movieRepository.findAllActorsByMovie(id);
    }

    public List<Object> findAllCrew(Long id) {
        return movieRepository.findAllCrewByMovie(id);
    }
}
