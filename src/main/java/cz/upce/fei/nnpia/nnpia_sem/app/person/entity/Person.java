package cz.upce.fei.nnpia.nnpia_sem.app.person.entity;

import cz.upce.fei.nnpia.nnpia_sem.app.movie.entity.Movie;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column
    private String name;

    @Column
    @OneToMany(mappedBy = "id")
    private List<Movie> cast_movies;

    @Column
    @OneToMany(mappedBy = "id")
    private List<Movie> crew_movies;
}
