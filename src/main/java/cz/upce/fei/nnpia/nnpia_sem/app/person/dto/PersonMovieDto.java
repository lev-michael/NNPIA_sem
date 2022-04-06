package cz.upce.fei.nnpia.nnpia_sem.app.person.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonMovieDto {
    private Long id;
    private String title, img;
    private Date release_date;
    private String role;
}
