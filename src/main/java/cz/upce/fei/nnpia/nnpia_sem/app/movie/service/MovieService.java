package cz.upce.fei.nnpia.nnpia_sem.app.movie.service;

import cz.upce.fei.nnpia.nnpia_sem.app.movie.dto.MovieDto;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.entity.Movie;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.repository.MovieRepository;
import cz.upce.fei.nnpia.nnpia_sem.app.person.entity.Person;
import cz.upce.fei.nnpia.nnpia_sem.app.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private RatingService ratingService;

    public Page<Movie> findAllMovies(Pageable pageable) {
        return movieRepository.findAll(pageable);
    }

    public MovieDto findMovie(Long id) {
        Optional<Movie> res = movieRepository.getById(id);
        if (res.isPresent()) {
            Movie m = res.get();
            double avgRating = ratingService.getAvgRating(id);
            List<String> genres = new ArrayList<>();
            System.out.println(m.getGenres());
            m.getGenres().forEach(g -> genres.add(g.getName()));
            List<Person> actors = new ArrayList<>();
            m.getActors().forEach(a -> actors.add(a));
            List<Person> crew = new ArrayList<>();
            m.getCrew().forEach(p -> crew.add(p));
            return new MovieDto(
                    m.getId(),
                    m.getTitle(),
                    m.getDescription(),
                    m.getImg(),
                    m.getRuntime(),
                    m.getRelease_date(),
                    avgRating,
                    genres,
                    actors,
                    crew);
        }
        return null;
    }

    public List<Object> findAllActors(Long id) {
        return movieRepository.findAllActorsByMovie(id);
    }

    public List<Object> findAllCrew(Long id) {
        return movieRepository.findAllCrewByMovie(id);
    }
}
