package cz.upce.fei.nnpia.nnpia_sem.app.person.controller;

import cz.upce.fei.nnpia.nnpia_sem.app.config.ApiResponse;
import cz.upce.fei.nnpia.nnpia_sem.app.config.StausEnum;
import cz.upce.fei.nnpia.nnpia_sem.app.person.dto.*;
import cz.upce.fei.nnpia.nnpia_sem.app.person.entity.Person;
import cz.upce.fei.nnpia.nnpia_sem.app.person.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/person")
@CrossOrigin
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/list")
    public ApiResponse<Page<Person>> getAllPersons(Pageable pageable) {
        Page<Person> allPersons = personService.getAllPersons(pageable);
        return new ApiResponse<>(HttpStatus.OK.value(), StausEnum.SUCCESS, allPersons);
    }

    @GetMapping("/ids")
    public ApiResponse<List<PersonIdNameDto>> getAllPersonsNamesAndIds() {
        List<PersonIdNameDto> allPersonsNamesAndIds = personService.getAllPersonsNamesAndIds();
        return new ApiResponse<>(HttpStatus.OK.value(), StausEnum.SUCCESS, allPersonsNamesAndIds);
    }

    @GetMapping("/{id}")
    public ApiResponse<PersonDetailDto> getPerson(@PathVariable Long id) {
        PersonDetailDto person = personService.getPerson(id);
        return new ApiResponse<>(HttpStatus.OK.value(), person != null ? StausEnum.SUCCESS : StausEnum.NOT_FOUND, person);
    }

    @GetMapping("/actors/list")
    public ApiResponse<Page<PersonListItemDto>> searchAllActors(@RequestParam(required = false) String query, Pageable pageable) {
        Page<PersonListItemDto> personListItemDtos = personService.searchAllActors(pageable, query != null ? query : "");
        return new ApiResponse<>(HttpStatus.OK.value(), StausEnum.SUCCESS, personListItemDtos);
    }

    @PostMapping("/add")
    public ApiResponse<Long> addPerson(@RequestBody AddPersonDto addPersonDto) {
        Long id = personService.addPerson(addPersonDto);
        return new ApiResponse<>(HttpStatus.OK.value(), StausEnum.CREATED, id);
    }

    @PostMapping("/edit")
    public ApiResponse<Long> editPerson(@RequestBody EditPersonDto editPersonDto) {
        Long id = personService.editPerson(editPersonDto);
        return new ApiResponse<>(HttpStatus.OK.value(), id != null ? StausEnum.SUCCESS : StausEnum.NOT_FOUND, id);
    }

    @ExceptionHandler({Exception.class})
    public StausEnum handleException() {
        return StausEnum.NOT_FOUND;
    }
}
