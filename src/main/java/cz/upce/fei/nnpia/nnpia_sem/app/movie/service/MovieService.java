package cz.upce.fei.nnpia.nnpia_sem.app.movie.service;

import cz.upce.fei.nnpia.nnpia_sem.app.movie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

}
