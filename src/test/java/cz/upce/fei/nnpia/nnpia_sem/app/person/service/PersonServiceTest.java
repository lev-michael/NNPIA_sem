package cz.upce.fei.nnpia.nnpia_sem.app.person.service;

import cz.upce.fei.nnpia.nnpia_sem.app.movie.entity.Movie;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.entity.MovieCast;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.entity.MovieCrew;
import cz.upce.fei.nnpia.nnpia_sem.app.person.dto.*;
import cz.upce.fei.nnpia.nnpia_sem.app.person.entity.Person;
import cz.upce.fei.nnpia.nnpia_sem.app.person.repository.PersonRepository;
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

@ContextConfiguration(classes = {PersonService.class})
@ExtendWith(SpringExtension.class)
class PersonServiceTest {
    @MockBean
    private PersonRepository personRepository;

    @Autowired
    private PersonService personService;

    /**
     * Method under test: {@link PersonService#getAllPersons(org.springframework.data.domain.Pageable)}
     */
    @Test
    void testGetAllPersons() {
        PageImpl<Person> pageImpl = new PageImpl<>(new ArrayList<>());
        when(this.personRepository.findAll((org.springframework.data.domain.Pageable) any())).thenReturn(pageImpl);
        assertSame(pageImpl, this.personService.getAllPersons(PageRequest.of(1, 3)));
        verify(this.personRepository).findAll((org.springframework.data.domain.Pageable) any());
    }

    /**
     * Method under test: {@link PersonService#getPerson(Long)}
     */
    @Test
    void testGetPerson() {
        Person person = new Person();
        person.setBiography("Biography");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        person.setBirthday(fromResult);
        ArrayList<MovieCast> movieCastList = new ArrayList<>();
        person.setCast_movies(movieCastList);
        person.setCrew_movies(new ArrayList<>());
        person.setGender("Gender");
        person.setId(123L);
        person.setImg("Img");
        person.setName("Name");
        Optional<Person> ofResult = Optional.of(person);
        when(this.personRepository.getById(any())).thenReturn(ofResult);
        PersonDetailDto actualPerson = this.personService.getPerson(123L);
        assertEquals("Biography", actualPerson.getBiography());
        assertEquals("Name", actualPerson.getName());
        assertEquals("Img", actualPerson.getImg());
        assertEquals(123L, actualPerson.getId().longValue());
        assertEquals("Gender", actualPerson.getGender());
        assertEquals(movieCastList, actualPerson.getCrew_movies());
        assertEquals(movieCastList, actualPerson.getCast_movies());
        assertSame(fromResult, actualPerson.getBirthday());
        verify(this.personRepository).getById(any());
    }

    /**
     * Method under test: {@link PersonService#getPerson(Long)}
     */
    @Test
    void testGetPerson2() {
        Movie movie = new Movie();
        movie.setActors(new ArrayList<>());
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

        Person person = new Person();
        person.setBiography("Biography");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        person.setBirthday(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        person.setCast_movies(new ArrayList<>());
        person.setCrew_movies(new ArrayList<>());
        person.setGender("Gender");
        person.setId(123L);
        person.setImg("Img");
        person.setName("Name");

        MovieCast movieCast = new MovieCast();
        movieCast.setCharacter("Character");
        movieCast.setId(123L);
        movieCast.setMovie(movie);
        movieCast.setOrder(1);
        movieCast.setPerson(person);

        ArrayList<MovieCast> movieCastList = new ArrayList<>();
        movieCastList.add(movieCast);

        Person person1 = new Person();
        person1.setBiography("Biography");
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult1 = Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant());
        person1.setBirthday(fromResult1);
        person1.setCast_movies(movieCastList);
        ArrayList<MovieCrew> movieCrewList = new ArrayList<>();
        person1.setCrew_movies(movieCrewList);
        person1.setGender("Gender");
        person1.setId(123L);
        person1.setImg("Img");
        person1.setName("Name");
        Optional<Person> ofResult = Optional.of(person1);
        when(this.personRepository.getById(any())).thenReturn(ofResult);
        PersonDetailDto actualPerson = this.personService.getPerson(123L);
        assertEquals("Biography", actualPerson.getBiography());
        assertEquals("Name", actualPerson.getName());
        assertEquals("Img", actualPerson.getImg());
        assertEquals(123L, actualPerson.getId().longValue());
        assertEquals("Gender", actualPerson.getGender());
        assertEquals(movieCrewList, actualPerson.getCrew_movies());
        List<PersonMovieDto> cast_movies = actualPerson.getCast_movies();
        assertEquals(1, cast_movies.size());
        assertSame(fromResult1, actualPerson.getBirthday());
        PersonMovieDto getResult = cast_movies.get(0);
        assertEquals(123L, getResult.getId().longValue());
        assertEquals("Dr", getResult.getTitle());
        assertEquals("Character", getResult.getRole());
        assertSame(fromResult, getResult.getRelease_date());
        assertEquals("Img", getResult.getImg());
        verify(this.personRepository).getById(any());
    }

    /**
     * Method under test: {@link PersonService#getPerson(Long)}
     */
    @Test
    void testGetPerson3() {
        Movie movie = new Movie();
        movie.setActors(new ArrayList<>());
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

        Person person = new Person();
        person.setBiography("Biography");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        person.setBirthday(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        person.setCast_movies(new ArrayList<>());
        person.setCrew_movies(new ArrayList<>());
        person.setGender("Gender");
        person.setId(123L);
        person.setImg("Img");
        person.setName("Name");

        MovieCrew movieCrew = new MovieCrew();
        movieCrew.setId(123L);
        movieCrew.setMovie(movie);
        movieCrew.setPerson(person);
        movieCrew.setRole("Role");

        ArrayList<MovieCrew> movieCrewList = new ArrayList<>();
        movieCrewList.add(movieCrew);

        Person person1 = new Person();
        person1.setBiography("Biography");
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult1 = Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant());
        person1.setBirthday(fromResult1);
        ArrayList<MovieCast> movieCastList = new ArrayList<>();
        person1.setCast_movies(movieCastList);
        person1.setCrew_movies(movieCrewList);
        person1.setGender("Gender");
        person1.setId(123L);
        person1.setImg("Img");
        person1.setName("Name");
        Optional<Person> ofResult = Optional.of(person1);
        when(this.personRepository.getById(any())).thenReturn(ofResult);
        PersonDetailDto actualPerson = this.personService.getPerson(123L);
        assertEquals("Biography", actualPerson.getBiography());
        assertEquals("Name", actualPerson.getName());
        assertEquals("Img", actualPerson.getImg());
        assertEquals(123L, actualPerson.getId().longValue());
        assertEquals("Gender", actualPerson.getGender());
        List<PersonMovieDto> crew_movies = actualPerson.getCrew_movies();
        assertEquals(1, crew_movies.size());
        assertEquals(movieCastList, actualPerson.getCast_movies());
        assertSame(fromResult1, actualPerson.getBirthday());
        PersonMovieDto getResult = crew_movies.get(0);
        assertEquals(123L, getResult.getId().longValue());
        assertEquals("Dr", getResult.getTitle());
        assertEquals("Role", getResult.getRole());
        assertSame(fromResult, getResult.getRelease_date());
        assertEquals("Img", getResult.getImg());
        verify(this.personRepository).getById(any());
    }

    /**
     * Method under test: {@link PersonService#getPerson(Long)}
     */
    @Test
    void testGetPerson4() {
        when(this.personRepository.getById(any())).thenReturn(Optional.empty());
        assertNull(this.personService.getPerson(123L));
        verify(this.personRepository).getById(any());
    }

    /**
     * Method under test: {@link PersonService#getPerson(Long)}
     */
    @Test
    void testGetPerson5() {
        Movie movie = new Movie();
        movie.setActors(new ArrayList<>());
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

        Person person = new Person();
        person.setBiography("Biography");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        person.setBirthday(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        person.setCast_movies(new ArrayList<>());
        person.setCrew_movies(new ArrayList<>());
        person.setGender("Gender");
        person.setId(123L);
        person.setImg("Img");
        person.setName("Name");

        MovieCast movieCast = new MovieCast();
        movieCast.setCharacter("Character");
        movieCast.setId(123L);
        movieCast.setMovie(movie);
        movieCast.setOrder(1);
        movieCast.setPerson(person);

        Movie movie1 = new Movie();
        movie1.setActors(new ArrayList<>());
        movie1.setCrew(new ArrayList<>());
        movie1.setDescription("The characteristics of someone or something");
        movie1.setGenres(new ArrayList<>());
        movie1.setId(123L);
        movie1.setImg("Img");
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult1 = Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant());
        movie1.setRelease_date(fromResult1);
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

        MovieCast movieCast1 = new MovieCast();
        movieCast1.setCharacter("Character");
        movieCast1.setId(123L);
        movieCast1.setMovie(movie1);
        movieCast1.setOrder(1);
        movieCast1.setPerson(person1);

        ArrayList<MovieCast> movieCastList = new ArrayList<>();
        movieCastList.add(movieCast1);
        movieCastList.add(movieCast);

        Person person2 = new Person();
        person2.setBiography("Biography");
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult2 = Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant());
        person2.setBirthday(fromResult2);
        person2.setCast_movies(movieCastList);
        ArrayList<MovieCrew> movieCrewList = new ArrayList<>();
        person2.setCrew_movies(movieCrewList);
        person2.setGender("Gender");
        person2.setId(123L);
        person2.setImg("Img");
        person2.setName("Name");
        Optional<Person> ofResult = Optional.of(person2);
        when(this.personRepository.getById(any())).thenReturn(ofResult);
        PersonDetailDto actualPerson = this.personService.getPerson(123L);
        assertEquals("Biography", actualPerson.getBiography());
        assertEquals("Name", actualPerson.getName());
        assertEquals("Img", actualPerson.getImg());
        assertEquals(123L, actualPerson.getId().longValue());
        assertEquals("Gender", actualPerson.getGender());
        assertEquals(movieCrewList, actualPerson.getCrew_movies());
        List<PersonMovieDto> cast_movies = actualPerson.getCast_movies();
        assertEquals(2, cast_movies.size());
        assertSame(fromResult2, actualPerson.getBirthday());
        PersonMovieDto getResult = cast_movies.get(1);
        assertEquals("Dr", getResult.getTitle());
        PersonMovieDto getResult1 = cast_movies.get(0);
        assertEquals("Dr", getResult1.getTitle());
        assertEquals("Character", getResult1.getRole());
        assertSame(fromResult1, getResult1.getRelease_date());
        assertEquals("Img", getResult1.getImg());
        assertEquals(123L, getResult1.getId().longValue());
        assertEquals("Character", getResult.getRole());
        assertSame(fromResult, getResult.getRelease_date());
        assertEquals("Img", getResult.getImg());
        assertEquals(123L, getResult.getId().longValue());
        verify(this.personRepository).getById(any());
    }

    /**
     * Method under test: {@link PersonService#getPerson(Long)}
     */
    @Test
    void testGetPerson6() {
        Movie movie = new Movie();
        movie.setActors(new ArrayList<>());
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

        Person person = new Person();
        person.setBiography("Biography");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        person.setBirthday(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        person.setCast_movies(new ArrayList<>());
        person.setCrew_movies(new ArrayList<>());
        person.setGender("Gender");
        person.setId(123L);
        person.setImg("Img");
        person.setName("Name");

        MovieCrew movieCrew = new MovieCrew();
        movieCrew.setId(123L);
        movieCrew.setMovie(movie);
        movieCrew.setPerson(person);
        movieCrew.setRole("Role");

        Movie movie1 = new Movie();
        movie1.setActors(new ArrayList<>());
        movie1.setCrew(new ArrayList<>());
        movie1.setDescription("The characteristics of someone or something");
        movie1.setGenres(new ArrayList<>());
        movie1.setId(123L);
        movie1.setImg("Img");
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult1 = Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant());
        movie1.setRelease_date(fromResult1);
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

        MovieCrew movieCrew1 = new MovieCrew();
        movieCrew1.setId(123L);
        movieCrew1.setMovie(movie1);
        movieCrew1.setPerson(person1);
        movieCrew1.setRole("Role");

        ArrayList<MovieCrew> movieCrewList = new ArrayList<>();
        movieCrewList.add(movieCrew1);
        movieCrewList.add(movieCrew);

        Person person2 = new Person();
        person2.setBiography("Biography");
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult2 = Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant());
        person2.setBirthday(fromResult2);
        ArrayList<MovieCast> movieCastList = new ArrayList<>();
        person2.setCast_movies(movieCastList);
        person2.setCrew_movies(movieCrewList);
        person2.setGender("Gender");
        person2.setId(123L);
        person2.setImg("Img");
        person2.setName("Name");
        Optional<Person> ofResult = Optional.of(person2);
        when(this.personRepository.getById(any())).thenReturn(ofResult);
        PersonDetailDto actualPerson = this.personService.getPerson(123L);
        assertEquals("Biography", actualPerson.getBiography());
        assertEquals("Name", actualPerson.getName());
        assertEquals("Img", actualPerson.getImg());
        assertEquals(123L, actualPerson.getId().longValue());
        assertEquals("Gender", actualPerson.getGender());
        List<PersonMovieDto> crew_movies = actualPerson.getCrew_movies();
        assertEquals(2, crew_movies.size());
        assertEquals(movieCastList, actualPerson.getCast_movies());
        assertSame(fromResult2, actualPerson.getBirthday());
        PersonMovieDto getResult = crew_movies.get(0);
        assertEquals("Dr", getResult.getTitle());
        PersonMovieDto getResult1 = crew_movies.get(1);
        assertEquals("Dr", getResult1.getTitle());
        assertEquals("Role", getResult1.getRole());
        assertSame(fromResult, getResult1.getRelease_date());
        assertEquals("Img", getResult1.getImg());
        assertEquals(123L, getResult1.getId().longValue());
        assertEquals("Role", getResult.getRole());
        assertSame(fromResult1, getResult.getRelease_date());
        assertEquals("Img", getResult.getImg());
        assertEquals(123L, getResult.getId().longValue());
        verify(this.personRepository).getById(any());
    }

    /**
     * Method under test: {@link PersonService#searchAllActors(org.springframework.data.domain.Pageable, String)}
     */
    @Test
    void testSearchAllActors() {
        PageImpl<PersonListItemDto> pageImpl = new PageImpl<>(new ArrayList<>());
        when(this.personRepository.searchAllActors(any(), any()))
                .thenReturn(pageImpl);
        assertSame(pageImpl, this.personService.searchAllActors(PageRequest.of(1, 3), "Text"));
        verify(this.personRepository).searchAllActors(any(), any());
    }

    /**
     * Method under test: {@link PersonService#getAllPersonsNamesAndIds()}
     */
    @Test
    void testGetAllPersonsNamesAndIds() {
        ArrayList<PersonIdNameDto> personIdNameDtoList = new ArrayList<>();
        when(this.personRepository.getAllPersonsNamesAndIds()).thenReturn(personIdNameDtoList);
        List<PersonIdNameDto> actualAllPersonsNamesAndIds = this.personService.getAllPersonsNamesAndIds();
        assertSame(personIdNameDtoList, actualAllPersonsNamesAndIds);
        assertTrue(actualAllPersonsNamesAndIds.isEmpty());
        verify(this.personRepository).getAllPersonsNamesAndIds();
    }

    /**
     * Method under test: {@link PersonService#findPerson(Long)}
     */
    @Test
    void testFindPerson() {
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
        Optional<Person> ofResult = Optional.of(person);
        when(this.personRepository.findById(any())).thenReturn(ofResult);
        assertSame(person, this.personService.findPerson(123L));
        verify(this.personRepository).findById(any());
    }

    /**
     * Method under test: {@link PersonService#addPerson(AddPersonDto)}
     */
    @Test
    void testAddPerson() {
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
        when(this.personRepository.save(any())).thenReturn(person);

        AddPersonDto addPersonDto = new AddPersonDto();
        addPersonDto.setBiography("Biography");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        addPersonDto.setBirthday(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        addPersonDto.setGender("Gender");
        addPersonDto.setImg("Img");
        addPersonDto.setName("Name");
        assertEquals(123L, this.personService.addPerson(addPersonDto).longValue());
        verify(this.personRepository).save(any());
    }

    /**
     * Method under test: {@link PersonService#editPerson(EditPersonDto)}
     */
    @Test
    void testEditPerson() {
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
        Optional<Person> ofResult = Optional.of(person);

        Person person1 = new Person();
        person1.setBiography("Biography");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        person1.setBirthday(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        person1.setCast_movies(new ArrayList<>());
        person1.setCrew_movies(new ArrayList<>());
        person1.setGender("Gender");
        person1.setId(123L);
        person1.setImg("Img");
        person1.setName("Name");
        when(this.personRepository.save(any())).thenReturn(person1);
        when(this.personRepository.getById(any())).thenReturn(ofResult);

        EditPersonDto editPersonDto = new EditPersonDto();
        editPersonDto.setBiography("Biography");
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        editPersonDto.setBirthday(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        editPersonDto.setGender("Gender");
        editPersonDto.setId(123L);
        editPersonDto.setImg("Img");
        editPersonDto.setName("Name");
        assertEquals(123L, this.personService.editPerson(editPersonDto).longValue());
        verify(this.personRepository).save(any());
        verify(this.personRepository).getById(any());
    }

    /**
     * Method under test: {@link PersonService#editPerson(EditPersonDto)}
     */
    @Test
    void testEditPerson2() {
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
        when(this.personRepository.save(any())).thenReturn(person);
        when(this.personRepository.getById(any())).thenReturn(Optional.empty());

        EditPersonDto editPersonDto = new EditPersonDto();
        editPersonDto.setBiography("Biography");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        editPersonDto.setBirthday(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        editPersonDto.setGender("Gender");
        editPersonDto.setId(123L);
        editPersonDto.setImg("Img");
        editPersonDto.setName("Name");
        assertNull(this.personService.editPerson(editPersonDto));
        verify(this.personRepository).getById(any());
    }
}

