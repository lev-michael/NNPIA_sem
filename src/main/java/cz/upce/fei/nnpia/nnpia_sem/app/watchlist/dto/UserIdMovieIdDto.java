package cz.upce.fei.nnpia.nnpia_sem.app.watchlist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserIdMovieIdDto {
    private Long userId, movieId;
}
