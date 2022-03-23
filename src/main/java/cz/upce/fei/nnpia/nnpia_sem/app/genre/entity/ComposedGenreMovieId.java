package cz.upce.fei.nnpia.nnpia_sem.app.genre.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ComposedGenreMovieId implements Serializable {
    private long movie;
    private long genre;
}
