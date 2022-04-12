package cz.upce.fei.nnpia.nnpia_sem.app.rating.dto;

import java.util.Date;

public interface MovieWithScoreDto {
    Long getId();

    String getTitle();

    String getImg();

    String getDescription();

    Date getRelease_date();

    Integer getScore();

    Integer getRuntime();
}
