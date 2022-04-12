package cz.upce.fei.nnpia.nnpia_sem.app.movie.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class AddMovieDto {
    private String title, description, img;
    private Date release_date;
    private int runtime;
    private List<String> genres;
}
