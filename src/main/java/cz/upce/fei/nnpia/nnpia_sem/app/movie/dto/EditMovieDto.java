package cz.upce.fei.nnpia.nnpia_sem.app.movie.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class EditMovieDto {
    private Long id;
    private int runtime;
    private Date release_date;
    private String title, description, img;
    private List<String> genres;
}
