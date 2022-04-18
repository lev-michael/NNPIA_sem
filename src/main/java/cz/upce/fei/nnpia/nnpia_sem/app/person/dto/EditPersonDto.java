package cz.upce.fei.nnpia.nnpia_sem.app.person.dto;

import lombok.Data;

import java.util.Date;

@Data
public class EditPersonDto {
    private Long id;
    private String name, img, biography, gender;
    private Date birthday;
}
