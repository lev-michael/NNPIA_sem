package cz.upce.fei.nnpia.nnpia_sem.app.person.dto;

import java.util.Date;

public interface PersonListItemDto {
    Long getId();

    String getName();

    String getImg();

    Date getBirthday();
}
