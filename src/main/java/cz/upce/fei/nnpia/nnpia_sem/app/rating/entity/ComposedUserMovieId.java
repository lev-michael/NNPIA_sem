package cz.upce.fei.nnpia.nnpia_sem.app.rating.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComposedUserMovieId implements Serializable {
    private long movie;
    private long user;
}
