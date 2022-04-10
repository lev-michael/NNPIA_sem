package cz.upce.fei.nnpia.nnpia_sem.app.person.controller;

import cz.upce.fei.nnpia.nnpia_sem.app.movie.dto.SearchDto;
import cz.upce.fei.nnpia.nnpia_sem.app.person.dto.PersonDetailDto;
import cz.upce.fei.nnpia.nnpia_sem.app.person.dto.PersonListItemDto;
import cz.upce.fei.nnpia.nnpia_sem.app.person.entity.Person;
import cz.upce.fei.nnpia.nnpia_sem.app.person.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/person")
@CrossOrigin
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/list")
    private Page<Person> getAllPersons(Pageable pageable) {
        return personService.getAllPersons(pageable);
    }

    @GetMapping("/{id}")
    private PersonDetailDto getPerson(@PathVariable Long id) {
        return personService.getPerson(id);
    }

    @PostMapping("/actors/list")
    private Page<PersonListItemDto> searchAllActors(@RequestBody(required = false) SearchDto searchDto, Pageable pageable) {
        return personService.searchAllActors(pageable, searchDto == null ? "" : searchDto.getQuery());
    }
}
