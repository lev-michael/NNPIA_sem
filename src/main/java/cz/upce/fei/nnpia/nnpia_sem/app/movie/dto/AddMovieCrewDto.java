package cz.upce.fei.nnpia.nnpia_sem.app.movie.dto;

import lombok.Data;

@Data
public class AddMovieCrewDto {
    private Long movie_id, person_id;
    private String role;
}
