package cz.upce.fei.nnpia.nnpia_sem.app.movie.entity;

import java.util.Date;

public interface RandomMovieDto {
    Long getId();

    String getTitle();

    String getImg();

    String getDescription();

    Integer getRuntime();

    Double getAvgScore();

    Date getRelease_date();
}

