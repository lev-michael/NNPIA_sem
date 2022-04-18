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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {MovieService.class})
@ExtendWith(SpringExtension.class)
class MovieServiceTest {
    @MockBean
    private GenreService genreService;

    @MockBean
    private MovieCastRepository movieCastRepository;

    @MockBean
    private MovieCrewRepository movieCrewRepository;

    @MockBean
    private MovieRepository movieRepository;

    @Autowired
    private MovieService movieService;

    @MockBean
    private PersonService personService;

    @MockBean
    private RatingService ratingService;

    /**
     * Method under test: {@link MovieService#findMovie}
     */
    @Test
    void testFindMovie() {
        when(this.ratingService.getAvgRating(any())).thenReturn(10.0d);

        Movie movie = new Movie();
        ArrayList<Person> personList = new ArrayList<>();
        movie.setActors(personList);
        movie.setCrew(new ArrayList<>());
        movie.setDescription("The characteristics of someone or something");
        movie.setGenres(new ArrayList<>());
        movie.setId(123L);
        movie.setImg("Img");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        movie.setRelease_date(fromResult);
        movie.setRuntime(1);
        movie.setScores(new ArrayList<>());
        movie.setTitle("Dr");
        Optional<Movie> ofResult = Optional.of(movie);
        when(this.movieRepository.findAllActorsByMovie(any())).thenReturn(new ArrayList<>());
        when(this.movieRepository.findAllCrewByMovie(any())).thenReturn(new ArrayList<>());
        when(this.movieRepository.getById(any())).thenReturn(ofResult);
        MovieDto actualFindMovieResult = this.movieService.findMovie(123L);
        assertEquals(personList, actualFindMovieResult.getActors());
        assertEquals("Dr", actualFindMovieResult.getTitle());
        assertEquals(1, actualFindMovieResult.getRuntime());
        assertSame(fromResult, actualFindMovieResult.getRelease_date());
        assertEquals("Img", actualFindMovieResult.getImg());
        assertEquals(123L, actualFindMovieResult.getId().longValue());
        assertEquals(personList, actualFindMovieResult.getGenres());
        assertEquals("The characteristics of someone or something", actualFindMovieResult.getDescription());
        assertEquals(personList, actualFindMovieResult.getCrew());
        assertEquals(10.0d, actualFindMovieResult.getAvgScore());
        verify(this.ratingService).getAvgRating(any());
        verify(this.movieRepository).findAllActorsByMovie(any());
        verify(this.movieRepository).findAllCrewByMovie(any());
        verify(this.movieRepository).getById(any());
    }

    /**
     * Method under test: {@link MovieService#findMovie}
     */
    @Test
    void testFindMovie2() {
        when(this.ratingService.getAvgRating(any())).thenReturn(10.0d);

        Genre genre = new Genre();
        genre.setId(123L);
        genre.setName("Name");

        Movie movie = new Movie();
        movie.setActors(new ArrayList<>());
        movie.setCrew(new ArrayList<>());
        movie.setDescription("The characteristics of someone or something");
        movie.setGenres(new ArrayList<>());
        movie.setId(123L);
        movie.setImg("Img");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        movie.setRelease_date(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        movie.setRuntime(1);
        movie.setScores(new ArrayList<>());
        movie.setTitle("Dr");

        GenreMovies genreMovies = new GenreMovies();
        genreMovies.setGenre(genre);
        genreMovies.setMovie(movie);

        ArrayList<GenreMovies> genreMoviesList = new ArrayList<>();
        genreMoviesList.add(genreMovies);

        Movie movie1 = new Movie();
        ArrayList<Person> personList = new ArrayList<>();
        movie1.setActors(personList);
        movie1.setCrew(new ArrayList<>());
        movie1.setDescription("The characteristics of someone or something");
        movie1.setGenres(genreMoviesList);
        movie1.setId(123L);
        movie1.setImg("Img");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant());
        movie1.setRelease_date(fromResult);
        movie1.setRuntime(1);
        movie1.setScores(new ArrayList<>());
        movie1.setTitle("Dr");
        Optional<Movie> ofResult = Optional.of(movie1);
        when(this.movieRepository.findAllActorsByMovie(any())).thenReturn(new ArrayList<>());
        when(this.movieRepository.findAllCrewByMovie(any())).thenReturn(new ArrayList<>());
        when(this.movieRepository.getById(any())).thenReturn(ofResult);
        MovieDto actualFindMovieResult = this.movieService.findMovie(123L);
        assertEquals(personList, actualFindMovieResult.getActors());
        assertEquals("Dr", actualFindMovieResult.getTitle());
        assertEquals(1, actualFindMovieResult.getRuntime());
        assertSame(fromResult, actualFindMovieResult.getRelease_date());
        assertEquals("Img", actualFindMovieResult.getImg());
        assertEquals(123L, actualFindMovieResult.getId().longValue());
        List<String> genres = actualFindMovieResult.getGenres();
        assertEquals(1, genres.size());
        assertEquals("Name", genres.get(0));
        assertEquals("The characteristics of someone or something", actualFindMovieResult.getDescription());
        assertEquals(personList, actualFindMovieResult.getCrew());
        assertEquals(10.0d, actualFindMovieResult.getAvgScore());
        verify(this.ratingService).getAvgRating(any());
        verify(this.movieRepository).findAllActorsByMovie(any());
        verify(this.movieRepository).findAllCrewByMovie(any());
        verify(this.movieRepository).getById(any());
    }

    /**
     * Method under test: {@link MovieService#findMovie}
     */
    @Test
    void testFindMovie3() {
        when(this.ratingService.getAvgRating(any())).thenReturn(10.0d);
        when(this.movieRepository.findAllActorsByMovie(any())).thenReturn(new ArrayList<>());
        when(this.movieRepository.findAllCrewByMovie(any())).thenReturn(new ArrayList<>());
        when(this.movieRepository.getById(any())).thenReturn(Optional.empty());
        assertNull(this.movieService.findMovie(123L));
        verify(this.movieRepository).getById(any());
    }

    /**
     * Method under test: {@link MovieService#findAllActors}
     */
    @Test
    void testFindAllActors() {
        ArrayList<ActorDto> actorDtoList = new ArrayList<>();
        when(this.movieRepository.findAllActorsByMovie(any())).thenReturn(actorDtoList);
        List<ActorDto> actualFindAllActorsResult = this.movieService.findAllActors(123L);
        assertSame(actorDtoList, actualFindAllActorsResult);
        assertTrue(actualFindAllActorsResult.isEmpty());
        verify(this.movieRepository).findAllActorsByMovie(any());
    }

    /**
     * Method under test: {@link MovieService#findAllCrew}
     */
    @Test
    void testFindAllCrew() {
        ArrayList<CrewDto> crewDtoList = new ArrayList<>();
        when(this.movieRepository.findAllCrewByMovie(any())).thenReturn(crewDtoList);
        List<CrewDto> actualFindAllCrewResult = this.movieService.findAllCrew(123L);
        assertSame(crewDtoList, actualFindAllCrewResult);
        assertTrue(actualFindAllCrewResult.isEmpty());
        verify(this.movieRepository).findAllCrewByMovie(any());
    }

    /**
     * Method under test: {@link MovieService#searchAllMovies(org.springframework.data.domain.Pageable, String)}
     */
    @Test
    void testSearchAllMovies() {
        PageImpl<MovieListDto> pageImpl = new PageImpl<>(new ArrayList<>());
        when(this.movieRepository.searchAll(any(), any()))
                .thenReturn(pageImpl);
        assertSame(pageImpl, this.movieService.searchAllMovies(PageRequest.of(1, 3), "Search"));
        verify(this.movieRepository).searchAll(any(), any());
    }

    /**
     * Method under test: {@link MovieService#addMovie(AddMovieDto)}
     */
    @Test
    void testAddMovie() {
        Movie movie = new Movie();
        movie.setActors(new ArrayList<>());
        movie.setCrew(new ArrayList<>());
        movie.setDescription("The characteristics of someone or something");
        movie.setGenres(new ArrayList<>());
        movie.setId(123L);
        movie.setImg("Img");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        movie.setRelease_date(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        movie.setRuntime(1);
        movie.setScores(new ArrayList<>());
        movie.setTitle("Dr");
        when(this.movieRepository.save(any())).thenReturn(movie);

        AddMovieDto addMovieDto = new AddMovieDto();
        addMovieDto.setDescription("The characteristics of someone or something");
        addMovieDto.setGenres(new ArrayList<>());
        addMovieDto.setImg("Img");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        addMovieDto.setRelease_date(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        addMovieDto.setRuntime(1);
        addMovieDto.setTitle("Dr");
        assertEquals(123L, this.movieService.addMovie(addMovieDto).longValue());
        verify(this.movieRepository).save(any());
    }

    /**
     * Method under test: {@link MovieService#findRandomMovie()}
     */
    @Test
    void testFindRandomMovie3() {
        ArrayList<RandomMovieDto> randomMovieDtoList = new ArrayList<>();
        randomMovieDtoList.add(null);
        PageImpl<RandomMovieDto> pageImpl = new PageImpl<>(randomMovieDtoList);
        when(this.movieRepository.count()).thenReturn(3L);
        when(this.movieRepository.findRandom(any())).thenReturn(pageImpl);
        assertNull(this.movieService.findRandomMovie());
        verify(this.movieRepository).count();
        verify(this.movieRepository).findRandom(any());
    }

    /**
     * Method under test: {@link MovieService#addMovieCast(AddMovieCastDto)}
     */
    @Test
    void testAddMovieCast() {
        Person person = new Person();
        person.setBiography("Biography");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        person.setBirthday(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        person.setCast_movies(new ArrayList<>());
        person.setCrew_movies(new ArrayList<>());
        person.setGender("Gender");
        person.setId(123L);
        person.setImg("Img");
        person.setName("Name");
        when(this.personService.findPerson(any())).thenReturn(person);

        Movie movie = new Movie();
        movie.setActors(new ArrayList<>());
        movie.setCrew(new ArrayList<>());
        movie.setDescription("The characteristics of someone or something");
        movie.setGenres(new ArrayList<>());
        movie.setId(123L);
        movie.setImg("Img");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        movie.setRelease_date(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        movie.setRuntime(1);
        movie.setScores(new ArrayList<>());
        movie.setTitle("Dr");
        Optional<Movie> ofResult = Optional.of(movie);
        when(this.movieRepository.findById(any())).thenReturn(ofResult);

        Movie movie1 = new Movie();
        movie1.setActors(new ArrayList<>());
        movie1.setCrew(new ArrayList<>());
        movie1.setDescription("The characteristics of someone or something");
        movie1.setGenres(new ArrayList<>());
        movie1.setId(123L);
        movie1.setImg("Img");
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        movie1.setRelease_date(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        movie1.setRuntime(1);
        movie1.setScores(new ArrayList<>());
        movie1.setTitle("Dr");

        Person person1 = new Person();
        person1.setBiography("Biography");
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        person1.setBirthday(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        person1.setCast_movies(new ArrayList<>());
        person1.setCrew_movies(new ArrayList<>());
        person1.setGender("Gender");
        person1.setId(123L);
        person1.setImg("Img");
        person1.setName("Name");

        MovieCast movieCast = new MovieCast();
        movieCast.setCharacter("Character");
        movieCast.setId(123L);
        movieCast.setMovie(movie1);
        movieCast.setOrder(1);
        movieCast.setPerson(person1);
        when(this.movieCastRepository.save(any())).thenReturn(movieCast);

        AddMovieCastDto addMovieCastDto = new AddMovieCastDto();
        addMovieCastDto.setCast_order(1);
        addMovieCastDto.setCharacter_name("Character name");
        addMovieCastDto.setMovie_id(1L);
        addMovieCastDto.setPerson_id(1L);
        assertEquals(123L, this.movieService.addMovieCast(addMovieCastDto).longValue());
        verify(this.personService).findPerson(any());
        verify(this.movieRepository).findById(any());
        verify(this.movieCastRepository).save(any());
    }

    /**
     * Method under test: {@link MovieService#addMovieCast(AddMovieCastDto)}
     */
    @Test
    void testAddMovieCast3() {
        Person person = new Person();
        person.setBiography("Biography");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        person.setBirthday(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        person.setCast_movies(new ArrayList<>());
        person.setCrew_movies(new ArrayList<>());
        person.setGender("Gender");
        person.setId(123L);
        person.setImg("Img");
        person.setName("Name");
        when(this.personService.findPerson(any())).thenReturn(person);
        when(this.movieRepository.findById(any())).thenReturn(Optional.empty());

        Movie movie = new Movie();
        movie.setActors(new ArrayList<>());
        movie.setCrew(new ArrayList<>());
        movie.setDescription("The characteristics of someone or something");
        movie.setGenres(new ArrayList<>());
        movie.setId(123L);
        movie.setImg("Img");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        movie.setRelease_date(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        movie.setRuntime(1);
        movie.setScores(new ArrayList<>());
        movie.setTitle("Dr");

        Person person1 = new Person();
        person1.setBiography("Biography");
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        person1.setBirthday(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        person1.setCast_movies(new ArrayList<>());
        person1.setCrew_movies(new ArrayList<>());
        person1.setGender("Gender");
        person1.setId(123L);
        person1.setImg("Img");
        person1.setName("Name");

        MovieCast movieCast = new MovieCast();
        movieCast.setCharacter("Character");
        movieCast.setId(123L);
        movieCast.setMovie(movie);
        movieCast.setOrder(1);
        movieCast.setPerson(person1);
        when(this.movieCastRepository.save(any())).thenReturn(movieCast);

        AddMovieCastDto addMovieCastDto = new AddMovieCastDto();
        addMovieCastDto.setCast_order(1);
        addMovieCastDto.setCharacter_name("Character name");
        addMovieCastDto.setMovie_id(1L);
        addMovieCastDto.setPerson_id(1L);
        assertNull(this.movieService.addMovieCast(addMovieCastDto));
        verify(this.personService).findPerson(any());
        verify(this.movieRepository).findById(any());
    }

    /**
     * Method under test: {@link MovieService#addMovieCrew(AddMovieCrewDto)}
     */
    @Test
    void testAddMovieCrew() {
        Person person = new Person();
        person.setBiography("Biography");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        person.setBirthday(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        person.setCast_movies(new ArrayList<>());
        person.setCrew_movies(new ArrayList<>());
        person.setGender("Gender");
        person.setId(123L);
        person.setImg("Img");
        person.setName("Name");
        when(this.personService.findPerson(any())).thenReturn(person);

        Movie movie = new Movie();
        movie.setActors(new ArrayList<>());
        movie.setCrew(new ArrayList<>());
        movie.setDescription("The characteristics of someone or something");
        movie.setGenres(new ArrayList<>());
        movie.setId(123L);
        movie.setImg("Img");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        movie.setRelease_date(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        movie.setRuntime(1);
        movie.setScores(new ArrayList<>());
        movie.setTitle("Dr");
        Optional<Movie> ofResult = Optional.of(movie);
        when(this.movieRepository.findById(any())).thenReturn(ofResult);

        Movie movie1 = new Movie();
        movie1.setActors(new ArrayList<>());
        movie1.setCrew(new ArrayList<>());
        movie1.setDescription("The characteristics of someone or something");
        movie1.setGenres(new ArrayList<>());
        movie1.setId(123L);
        movie1.setImg("Img");
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        movie1.setRelease_date(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        movie1.setRuntime(1);
        movie1.setScores(new ArrayList<>());
        movie1.setTitle("Dr");

        Person person1 = new Person();
        person1.setBiography("Biography");
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        person1.setBirthday(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        person1.setCast_movies(new ArrayList<>());
        person1.setCrew_movies(new ArrayList<>());
        person1.setGender("Gender");
        person1.setId(123L);
        person1.setImg("Img");
        person1.setName("Name");

        MovieCrew movieCrew = new MovieCrew();
        movieCrew.setId(123L);
        movieCrew.setMovie(movie1);
        movieCrew.setPerson(person1);
        movieCrew.setRole("Role");
        when(this.movieCrewRepository.save(any())).thenReturn(movieCrew);

        AddMovieCrewDto addMovieCrewDto = new AddMovieCrewDto();
        addMovieCrewDto.setMovie_id(1L);
        addMovieCrewDto.setPerson_id(1L);
        addMovieCrewDto.setRole("Role");
        assertEquals(123L, this.movieService.addMovieCrew(addMovieCrewDto).longValue());
        verify(this.personService).findPerson(any());
        verify(this.movieRepository).findById(any());
        verify(this.movieCrewRepository).save(any());
    }

    @Test
    void testAddMovieCrew3() {
        Person person = new Person();
        person.setBiography("Biography");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        person.setBirthday(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        person.setCast_movies(new ArrayList<>());
        person.setCrew_movies(new ArrayList<>());
        person.setGender("Gender");
        person.setId(123L);
        person.setImg("Img");
        person.setName("Name");
        when(this.personService.findPerson(any())).thenReturn(person);
        when(this.movieRepository.findById(any())).thenReturn(Optional.empty());

        Movie movie = new Movie();
        movie.setActors(new ArrayList<>());
        movie.setCrew(new ArrayList<>());
        movie.setDescription("The characteristics of someone or something");
        movie.setGenres(new ArrayList<>());
        movie.setId(123L);
        movie.setImg("Img");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        movie.setRelease_date(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        movie.setRuntime(1);
        movie.setScores(new ArrayList<>());
        movie.setTitle("Dr");

        Person person1 = new Person();
        person1.setBiography("Biography");
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        person1.setBirthday(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        person1.setCast_movies(new ArrayList<>());
        person1.setCrew_movies(new ArrayList<>());
        person1.setGender("Gender");
        person1.setId(123L);
        person1.setImg("Img");
        person1.setName("Name");

        MovieCrew movieCrew = new MovieCrew();
        movieCrew.setId(123L);
        movieCrew.setMovie(movie);
        movieCrew.setPerson(person1);
        movieCrew.setRole("Role");
        when(this.movieCrewRepository.save(any())).thenReturn(movieCrew);

        AddMovieCrewDto addMovieCrewDto = new AddMovieCrewDto();
        addMovieCrewDto.setMovie_id(1L);
        addMovieCrewDto.setPerson_id(1L);
        addMovieCrewDto.setRole("Role");
        assertNull(this.movieService.addMovieCrew(addMovieCrewDto));
        verify(this.personService).findPerson(any());
        verify(this.movieRepository).findById(any());
    }

    /**
     * Method under test: {@link MovieService#findAllMoviesIdsTitles()}
     */
    @Test
    void testFindAllMoviesIdsTitles() {
        ArrayList<MovieIdTitleDto> movieIdTitleDtoList = new ArrayList<>();
        when(this.movieRepository.findAllIdsTitles()).thenReturn(movieIdTitleDtoList);
        List<MovieIdTitleDto> actualFindAllMoviesIdsTitlesResult = this.movieService.findAllMoviesIdsTitles();
        assertSame(movieIdTitleDtoList, actualFindAllMoviesIdsTitlesResult);
        assertTrue(actualFindAllMoviesIdsTitlesResult.isEmpty());
        verify(this.movieRepository).findAllIdsTitles();
    }

    /**
     * Method under test: {@link MovieService#editMovie(EditMovieDto)}
     */
    @Test
    void testEditMovie() {
        Movie movie = new Movie();
        movie.setActors(new ArrayList<>());
        movie.setCrew(new ArrayList<>());
        movie.setDescription("The characteristics of someone or something");
        movie.setGenres(new ArrayList<>());
        movie.setId(123L);
        movie.setImg("Img");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        movie.setRelease_date(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        movie.setRuntime(1);
        movie.setScores(new ArrayList<>());
        movie.setTitle("Dr");
        Optional<Movie> ofResult = Optional.of(movie);

        Movie movie1 = new Movie();
        movie1.setActors(new ArrayList<>());
        movie1.setCrew(new ArrayList<>());
        movie1.setDescription("The characteristics of someone or something");
        movie1.setGenres(new ArrayList<>());
        movie1.setId(123L);
        movie1.setImg("Img");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        movie1.setRelease_date(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        movie1.setRuntime(1);
        movie1.setScores(new ArrayList<>());
        movie1.setTitle("Dr");
        when(this.movieRepository.saveAndFlush(any())).thenReturn(movie1);
        when(this.movieRepository.findById(any())).thenReturn(ofResult);

        EditMovieDto editMovieDto = new EditMovieDto();
        editMovieDto.setDescription("The characteristics of someone or something");
        editMovieDto.setGenres(new ArrayList<>());
        editMovieDto.setId(123L);
        editMovieDto.setImg("Img");
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        editMovieDto.setRelease_date(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        editMovieDto.setRuntime(1);
        editMovieDto.setTitle("Dr");
        assertEquals(123L, this.movieService.editMovie(editMovieDto).longValue());
        verify(this.movieRepository).saveAndFlush(any());
        verify(this.movieRepository).findById(any());
    }

    /**
     * Method under test: {@link MovieService#editMovie(EditMovieDto)}
     */
    @Test
    void testEditMovie2() {
        Movie movie = new Movie();
        movie.setActors(new ArrayList<>());
        movie.setCrew(new ArrayList<>());
        movie.setDescription("The characteristics of someone or something");
        movie.setGenres(new ArrayList<>());
        movie.setId(123L);
        movie.setImg("Img");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        movie.setRelease_date(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        movie.setRuntime(1);
        movie.setScores(new ArrayList<>());
        movie.setTitle("Dr");
        Optional<Movie> ofResult = Optional.of(movie);

        Genre genre = new Genre();
        genre.setId(123L);
        genre.setName("Name");

        Movie movie1 = new Movie();
        movie1.setActors(new ArrayList<>());
        movie1.setCrew(new ArrayList<>());
        movie1.setDescription("The characteristics of someone or something");
        movie1.setGenres(new ArrayList<>());
        movie1.setId(123L);
        movie1.setImg("Img");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        movie1.setRelease_date(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        movie1.setRuntime(1);
        movie1.setScores(new ArrayList<>());
        movie1.setTitle("Dr");

        GenreMovies genreMovies = new GenreMovies();
        genreMovies.setGenre(genre);
        genreMovies.setMovie(movie1);

        ArrayList<GenreMovies> genreMoviesList = new ArrayList<>();
        genreMoviesList.add(genreMovies);

        Movie movie2 = new Movie();
        movie2.setActors(new ArrayList<>());
        movie2.setCrew(new ArrayList<>());
        movie2.setDescription("The characteristics of someone or something");
        movie2.setGenres(genreMoviesList);
        movie2.setId(123L);
        movie2.setImg("Img");
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        movie2.setRelease_date(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        movie2.setRuntime(1);
        movie2.setScores(new ArrayList<>());
        movie2.setTitle("Dr");
        when(this.movieRepository.saveAndFlush(any())).thenReturn(movie2);
        when(this.movieRepository.findById(any())).thenReturn(ofResult);
        doNothing().when(this.genreService).removeGenreFromMovie(any(), any());

        EditMovieDto editMovieDto = new EditMovieDto();
        editMovieDto.setDescription("The characteristics of someone or something");
        editMovieDto.setGenres(new ArrayList<>());
        editMovieDto.setId(123L);
        editMovieDto.setImg("Img");
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        editMovieDto.setRelease_date(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        editMovieDto.setRuntime(1);
        editMovieDto.setTitle("Dr");
        assertEquals(123L, this.movieService.editMovie(editMovieDto).longValue());
        verify(this.movieRepository).saveAndFlush(any());
        verify(this.movieRepository).findById(any());
        verify(this.genreService).removeGenreFromMovie(any(), any());
    }
}

