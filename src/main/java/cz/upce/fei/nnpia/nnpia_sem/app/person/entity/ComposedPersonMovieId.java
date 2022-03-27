package cz.upce.fei.nnpia.nnpia_sem.app.person.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComposedPersonMovieId implements Serializable {
    private long movie;
    private long person;
}
