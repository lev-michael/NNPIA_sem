package cz.upce.fei.nnpia.nnpia_sem.app.person.controller;

import cz.upce.fei.nnpia.nnpia_sem.app.person.entity.Person;
import cz.upce.fei.nnpia.nnpia_sem.app.person.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/list")
    private Page<Person> getAllPersons(Pageable pageable) {
        return personService.getAllPersons(pageable);
    }

    @GetMapping("/{id}")
    private Person getPerson(@PathVariable Long id) {
        return personService.getPerson(id);
    }
}
