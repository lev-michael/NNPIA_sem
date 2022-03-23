package cz.upce.fei.nnpia.nnpia_sem.app.genre.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank
    private String name;

}
