package cz.upce.fei.nnpia.nnpia_sem.app.movie.dto;


import java.util.Date;

public interface MovieListDto {
    Long getId();

    String getTitle();

    String getImg();

    Double getAvgScore();

    Date getRelease_date();
}
