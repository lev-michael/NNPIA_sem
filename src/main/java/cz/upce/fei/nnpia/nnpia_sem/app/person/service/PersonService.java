package cz.upce.fei.nnpia.nnpia_sem.app.person.service;

import cz.upce.fei.nnpia.nnpia_sem.app.person.dto.PersonDto;
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

    public Page<Person> getAllPersons(Pageable pageable) {
        return personRepository.findAll(pageable);
    }

    public PersonDto getPerson(Long id) {
        Optional<Person> res = personRepository.findById(id);
        if (res.isPresent()) {
            Person p = res.get();
            List<String> movies = new ArrayList();
            //p.getCast_movies().forEach(m -> System.out.println(m));
            return new PersonDto(p.getId(), p.getName(), p.getBiography(), p.getGender(), p.getImg(), p.getBirthday(), movies);
        }
        return null;
    }
}
