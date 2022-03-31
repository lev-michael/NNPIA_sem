package cz.upce.fei.nnpia.nnpia_sem.app.person.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.entity.Movie;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
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
    @NotBlank
    private String gender;

    @Column
    @NotBlank
    private Date birthDate;

    @Column()
    private String img;

    @Column(length = 1000)
    private String biography;

    @Column
    @JsonIgnoreProperties({"actors", "crew"})
    @OneToMany(mappedBy = "id")
    private List<Movie> cast_movies;

    @Column
    @JsonIgnoreProperties({"actors", "crew"})
    @OneToMany(mappedBy = "id")
    private List<Movie> crew_movies;
}
