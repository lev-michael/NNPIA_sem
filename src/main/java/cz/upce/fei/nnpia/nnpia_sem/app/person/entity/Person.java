package cz.upce.fei.nnpia.nnpia_sem.app.person.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.entity.MovieCast;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.entity.MovieCrew;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String gender;

    @Column
    private Date birthday;

    @Column
    private String img;

    @Column(length = 2500)
    private String biography;

    @JsonManagedReference
    @Column
    @OneToMany(mappedBy = "person", targetEntity = MovieCast.class, fetch = FetchType.LAZY)
    private List<MovieCast> cast_movies;

    @JsonManagedReference
    @Column
    @OneToMany(mappedBy = "person", targetEntity = MovieCrew.class, fetch = FetchType.LAZY)
    private List<MovieCrew> crew_movies;
}
