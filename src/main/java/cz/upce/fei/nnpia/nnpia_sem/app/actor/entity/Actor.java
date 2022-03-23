package cz.upce.fei.nnpia.nnpia_sem.app.actor.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank
    private String firstName;

    @Column
    @NotBlank
    private String lastName;

    @Column
    @NotBlank
    private Date birthDate;

    @Column(length = 2500)
    private String about;
}
