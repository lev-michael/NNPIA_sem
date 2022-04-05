package cz.upce.fei.nnpia.nnpia_sem.app.movie.service;

import cz.upce.fei.nnpia.nnpia_sem.app.genre.entity.GenreMovies;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.dto.ActorDto;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.dto.CrewDto;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.dto.MovieDto;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.dto.MovieListDto;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.entity.Movie;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.repository.MovieRepository;
import cz.upce.fei.nnpia.nnpia_sem.app.rating.entity.Rating;
import cz.upce.fei.nnpia.nnpia_sem.app.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
            List<ActorDto> actors = findAllActors(id);
            List<CrewDto> crew = findAllCrew(id);
            List<String> genres = new ArrayList<>();
            double avgRating = ratingService.getAvgRating(id);
            int i = 0;
            for (GenreMovies g : m.getGenres()) {
                genres.add(g.getGenre().getName());
            }
            return new MovieDto(m.getId(), m.getTitle(), m.getDescription(), m.getImg(), m.getRuntime(), avgRating, m.getRelease_date(), genres, actors, crew);
        }
        return null;
    }

    public List<ActorDto> findAllActors(Long id) {
        return movieRepository.findAllActorsByMovie(id);
    }

    public List<CrewDto> findAllCrew(Long id) {
        return movieRepository.findAllCrewByMovie(id);
    }

    public Page<MovieListDto> searchAllMovies(Pageable pageable, String search) {
        Page<Movie> moviePage = movieRepository.searchAll(search, pageable);
        List<MovieListDto> movies = new ArrayList<>();
        moviePage.forEach(m -> {
            int sum = 0;
            double avgScore = 0;
            int size = m.getScores().size();
            if (size > 0) {
                for (Rating r : m.getScores()) {
                    sum += r.getScore();
                }
                avgScore = (double) sum / size;
            }
            movies.add(new MovieListDto(m.getId(), m.getTitle(), m.getImg(), avgScore));
        });
        return new PageImpl<MovieListDto>(movies);
    }
}
