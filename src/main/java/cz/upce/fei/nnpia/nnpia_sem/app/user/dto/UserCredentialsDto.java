package cz.upce.fei.nnpia.nnpia_sem.app.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserCredentialsDto {

    private String username, password;
}
