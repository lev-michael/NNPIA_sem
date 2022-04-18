package cz.upce.fei.nnpia.nnpia_sem.app.genre.service;

import cz.upce.fei.nnpia.nnpia_sem.app.genre.entity.ComposedGenreMovieId;
import cz.upce.fei.nnpia.nnpia_sem.app.genre.entity.Genre;
import cz.upce.fei.nnpia.nnpia_sem.app.genre.entity.GenreMovies;
import cz.upce.fei.nnpia.nnpia_sem.app.genre.repository.GenreMovieRepository;
import cz.upce.fei.nnpia.nnpia_sem.app.genre.repository.GenreRepository;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private GenreMovieRepository genreMovieRepository;

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public Genre getGenre(Long id) {
        return genreRepository.findById(id).orElse(null);
    }

    public void addGenreToMovie(Genre genre, Movie movie) {
        GenreMovies genreMovies = new GenreMovies();
        genreMovies.setGenre(genre);
        genreMovies.setMovie(movie);
        genreMovieRepository.saveAndFlush(genreMovies);
    }

    public void removeGenreFromMovie(Long genreId, Long movieId) {
        ComposedGenreMovieId id = new ComposedGenreMovieId();
        id.setGenre(genreId);
        id.setMovie(movieId);
        genreMovieRepository.deleteById(id);
        genreMovieRepository.flush();
    }
}
