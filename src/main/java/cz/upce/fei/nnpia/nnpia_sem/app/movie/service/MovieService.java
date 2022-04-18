package cz.upce.fei.nnpia.nnpia_sem.app.movie.service;

import cz.upce.fei.nnpia.nnpia_sem.app.genre.entity.Genre;
import cz.upce.fei.nnpia.nnpia_sem.app.genre.entity.GenreMovies;
import cz.upce.fei.nnpia.nnpia_sem.app.genre.service.GenreService;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.dto.*;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.entity.Movie;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.entity.MovieCast;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.entity.MovieCrew;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.entity.RandomMovieDto;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.repository.MovieCastRepository;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.repository.MovieCrewRepository;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.repository.MovieRepository;
import cz.upce.fei.nnpia.nnpia_sem.app.person.entity.Person;
import cz.upce.fei.nnpia.nnpia_sem.app.person.service.PersonService;
import cz.upce.fei.nnpia.nnpia_sem.app.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    private MovieCastRepository movieCastRepository;

    @Autowired
    private MovieCrewRepository movieCrewRepository;

    @Autowired
    private PersonService personService;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private GenreService genreService;

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
        return movieRepository.searchAll(search.toLowerCase(), pageable);
    }

    public Long addMovie(AddMovieDto movieDto) {
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
        return savedMovie.getId();
    }

    public RandomMovieDto findRandomMovie() {
        Long count = movieRepository.count();
        int index = (int) (Math.random() * count);
        Page<RandomMovieDto> movieDto = movieRepository.findRandom(PageRequest.of(index, 1));
        return movieDto.getContent().get(0);
    }

    public Long addMovieCast(AddMovieCastDto addMovieCastDto) {
        MovieCast movieCast = new MovieCast();
        Movie m = movieRepository.findById(addMovieCastDto.getMovie_id()).orElse(null);
        Person p = personService.findPerson(addMovieCastDto.getPerson_id());
        if (p != null && m != null) {
            movieCast.setMovie(m);
            movieCast.setPerson(p);
            movieCast.setCharacter(addMovieCastDto.getCharacter_name());
            movieCast.setOrder(addMovieCastDto.getCast_order());
            return movieCastRepository.save(movieCast).getId();
        }
        return null;
    }

    public Long addMovieCrew(AddMovieCrewDto addMovieCastDto) {
        MovieCrew movieCrew = new MovieCrew();
        Movie m = movieRepository.findById(addMovieCastDto.getMovie_id()).orElse(null);
        Person p = personService.findPerson(addMovieCastDto.getPerson_id());
        if (p != null && m != null) {
            movieCrew.setMovie(m);
            movieCrew.setPerson(p);
            movieCrew.setRole(addMovieCastDto.getRole());
            return movieCrewRepository.save(movieCrew).getId();
        }
        return null;
    }

    public List<MovieIdTitleDto> findAllMoviesIdsTitles() {
        return movieRepository.findAllIdsTitles();
    }

    public Long editMovie(EditMovieDto movieDto) {
        Movie movie = movieRepository.findById(movieDto.getId()).orElse(null);
        if (movie != null) {
            movie.setTitle(movieDto.getTitle());
            movie.setDescription(movieDto.getDescription());
            movie.setImg(movieDto.getImg());
            movie.setRelease_date(movieDto.getRelease_date());
            movie.setRuntime(movieDto.getRuntime());
            Movie savedMovie = movieRepository.saveAndFlush(movie);
            for (String id : movieDto.getGenres()) {
                Genre genre = genreService.getGenre(Long.parseLong(id));
                genreService.addGenreToMovie(genre, savedMovie);
            }
            for (GenreMovies genreMovies : savedMovie.getGenres()) {
                if (!movieDto.getGenres().contains(genreMovies.getGenre().getId().toString())) {
                    genreService.removeGenreFromMovie(genreMovies.getGenre().getId(), savedMovie.getId());
                }
            }
            return savedMovie.getId();
        }
        return null;
    }
}
