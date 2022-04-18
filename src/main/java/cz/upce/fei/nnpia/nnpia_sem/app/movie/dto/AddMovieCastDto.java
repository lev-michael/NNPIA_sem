package cz.upce.fei.nnpia.nnpia_sem.app.movie.dto;

import lombok.Data;

@Data
public class AddMovieCastDto {
    private Long movie_id, person_id;
    private String character_name;
    private int cast_order;
}
