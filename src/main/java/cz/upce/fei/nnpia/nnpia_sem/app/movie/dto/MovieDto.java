package cz.upce.fei.nnpia.nnpia_sem.app.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class MovieDto {
    private Long id;
    private String title, description, img;
    private int runtime;
    private double avgRating;
    private Date release_date;
    private List<String> genres;
    private List<ActorDto> actors;
    private List<CrewDto> crew;

}
