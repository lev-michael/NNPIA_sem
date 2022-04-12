package cz.upce.fei.nnpia.nnpia_sem.app.movie.service;

import cz.upce.fei.nnpia.nnpia_sem.app.genre.repository.entity.Genre;
import cz.upce.fei.nnpia.nnpia_sem.app.genre.repository.entity.GenreMovies;
import cz.upce.fei.nnpia.nnpia_sem.app.genre.service.GenreService;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.dto.*;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.entity.Movie;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.repository.MovieRepository;
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

    @Autowired
    private GenreService genreService;

    public Page<Movie> findAllMovies(Pageable pageable) {
        return movieRepository.findAll(pageable);
    }

    public List<Movie> findAllMoviesByIds(List<Long> ids) {
        return movieRepository.findAllById(ids);
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
        return movieRepository.searchAll(search, pageable);
    }

    public Movie addMovie(AddMovieDto movieDto) {
        Movie movie = new Movie();
        movie.setTitle(movieDto.getTitle());
        movie.setDescription(movieDto.getDescription());
        movie.setImg(movieDto.getImg());
        movie.setRelease_date(movieDto.getRelease_date());
        movie.setRuntime(movieDto.getRuntime());
        Movie savedMovie = movieRepository.save(movie);
        for (String id : movieDto.getGenres()) {
            Genre genre = genreService.getGenre(Long.parseLong(id));
            genreService.addGenreToMovie(genre, savedMovie);
        }
        return savedMovie;
    }
}
