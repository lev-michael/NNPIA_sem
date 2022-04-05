package cz.upce.fei.nnpia.nnpia_sem.app.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MovieListDto {
    private Long id;
    private String title, img;
    private double avgScore;
}
