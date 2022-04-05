package cz.upce.fei.nnpia.nnpia_sem.app.rating.dto;

import lombok.Data;

@Data
public class CreateUpdateRatingDto {
    int score;
    Long movieId, userId;
}
