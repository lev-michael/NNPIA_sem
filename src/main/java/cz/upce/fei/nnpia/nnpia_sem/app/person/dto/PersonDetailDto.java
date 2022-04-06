package cz.upce.fei.nnpia.nnpia_sem.app.person.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDetailDto {
    private Long id;
    private String name;
    private String gender;
    private Date birthday;
    private String img;
    private String biography;
    private List<PersonMovieDto> crew_movies;
    private List<PersonMovieDto> cast_movies;

}
