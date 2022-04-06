package cz.upce.fei.nnpia.nnpia_sem.app.person.service;

import cz.upce.fei.nnpia.nnpia_sem.app.movie.service.MovieService;
import cz.upce.fei.nnpia.nnpia_sem.app.person.dto.PersonDetailDto;
import cz.upce.fei.nnpia.nnpia_sem.app.person.dto.PersonListItemDto;
import cz.upce.fei.nnpia.nnpia_sem.app.person.dto.PersonMovieDto;
import cz.upce.fei.nnpia.nnpia_sem.app.person.entity.Person;
import cz.upce.fei.nnpia.nnpia_sem.app.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MovieService movieService;

    public Page<Person> getAllPersons(Pageable pageable) {
        return personRepository.findAll(pageable);
    }

    public PersonDetailDto getPerson(Long id) {
        Optional<Person> res = personRepository.getById(id);
        if (res.isPresent()) {
            Person person = res.get();
            List<PersonMovieDto> cast_movies = new ArrayList<>();
            List<PersonMovieDto> crew_movies = new ArrayList<>();
            person.getCast_movies().forEach(m -> cast_movies.add(new PersonMovieDto(m.getMovie().getId(), m.getMovie().getTitle(), m.getMovie().getImg(), m.getMovie().getRelease_date(), m.getCharacter())));
            person.getCrew_movies().forEach(m -> crew_movies.add(new PersonMovieDto(m.getMovie().getId(), m.getMovie().getTitle(), m.getMovie().getImg(), m.getMovie().getRelease_date(), m.getRole())));
            return new PersonDetailDto(person.getId(), person.getName(), person.getGender(), person.getBirthday(), person.getImg(), person.getBiography(), crew_movies, cast_movies);
        }
        return null;
    }

    public Page<PersonListItemDto> searchAllActors(Pageable pageable, String text) {
        return personRepository.searchAllActors(pageable, text);
    }
}
