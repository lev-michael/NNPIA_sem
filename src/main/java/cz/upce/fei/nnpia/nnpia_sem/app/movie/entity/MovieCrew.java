package cz.upce.fei.nnpia.nnpia_sem.app.movie.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import cz.upce.fei.nnpia.nnpia_sem.app.person.entity.Person;
import lombok.Data;

import javax.persistence.*;

@Entity(name = "movie_crew")
@Data
public class MovieCrew {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Person person;

    @Column
    private String role;

}
