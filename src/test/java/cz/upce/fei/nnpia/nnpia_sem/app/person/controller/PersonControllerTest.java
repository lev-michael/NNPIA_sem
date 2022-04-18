package cz.upce.fei.nnpia.nnpia_sem.app.person.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.upce.fei.nnpia.nnpia_sem.app.person.dto.AddPersonDto;
import cz.upce.fei.nnpia.nnpia_sem.app.person.dto.EditPersonDto;
import cz.upce.fei.nnpia.nnpia_sem.app.person.dto.PersonDetailDto;
import cz.upce.fei.nnpia.nnpia_sem.app.person.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {PersonController.class})
@ExtendWith(SpringExtension.class)
class PersonControllerTest {
    @Autowired
    private PersonController personController;

    @MockBean
    private PersonService personService;

    /**
     * Method under test: {@link PersonController#getAllPersonsNamesAndIds()}
     */
    @Test
    void testGetAllPersonsNamesAndIds() throws Exception {
        when(this.personService.getAllPersonsNamesAndIds()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/person/ids");
        MockMvcBuilders.standaloneSetup(this.personController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":200,\"status\":\"SUCCESS\",\"result\":[]}"));
    }


    /**
     * Method under test: {@link PersonController#getPerson(Long)}
     */
    @Test
    void testGetPerson() throws Exception {
        PersonDetailDto personDetailDto = new PersonDetailDto(
                123L,
                "name",
                "MALE",
                null,
                "img",
                "biography",
                new ArrayList<>(),
                new ArrayList<>()
        );
        when(this.personService.getPerson(any())).thenReturn(personDetailDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/person/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.personController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"code\":200,\"status\":\"SUCCESS\",\"result\":{\"id\":123,\"name\":\"name\",\"gender\":\"MALE\",\"birthday\":null,\"img\""
                                        + ":\"img\",\"biography\":\"biography\",\"crew_movies\":[],\"cast_movies\":[]}}"));
    }

    /**
     * Method under test: {@link PersonController#getPerson(Long)}
     */
    @Test
    void testGetPersonNotFound() throws Exception {
        when(this.personService.getPerson(any())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/person/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.personController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":200,\"status\":\"NOT_FOUND\",\"result\":null}"));
    }


    /**
     * Method under test: {@link PersonController#addPerson(AddPersonDto)}
     */
    @Test
    void testAddPerson() throws Exception {
        when(this.personService.addPerson(any())).thenReturn(1L);

        AddPersonDto addPersonDto = new AddPersonDto();
        addPersonDto.setBiography("Biography");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        addPersonDto.setBirthday(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        addPersonDto.setGender("Gender");
        addPersonDto.setImg("Img");
        addPersonDto.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(addPersonDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/person/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.personController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":200,\"status\":\"CREATED\",\"result\":1}"));
    }

    /**
     * Method under test: {@link PersonController#editPerson(EditPersonDto)}
     */
    @Test
    void testEditPerson() throws Exception {
        when(this.personService.editPerson((EditPersonDto) any())).thenReturn(1L);

        EditPersonDto editPersonDto = new EditPersonDto();
        editPersonDto.setBiography("Biography");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        editPersonDto.setBirthday(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        editPersonDto.setGender("Gender");
        editPersonDto.setId(123L);
        editPersonDto.setImg("Img");
        editPersonDto.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(editPersonDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/person/edit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.personController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":200,\"status\":\"SUCCESS\",\"result\":1}"));
    }

    /**
     * Method under test: {@link PersonController#editPerson(EditPersonDto)}
     */
    @Test
    void testEditPersonNotFound() throws Exception {
        when(this.personService.editPerson((EditPersonDto) any())).thenReturn(null);

        EditPersonDto editPersonDto = new EditPersonDto();
        editPersonDto.setBiography("Biography");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        editPersonDto.setBirthday(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        editPersonDto.setGender("Gender");
        editPersonDto.setId(123L);
        editPersonDto.setImg("Img");
        editPersonDto.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(editPersonDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/person/edit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.personController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":200,\"status\":\"NOT_FOUND\",\"result\":null}"));
    }
}

