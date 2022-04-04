package cz.upce.fei.nnpia.nnpia_sem.app.movie.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import cz.upce.fei.nnpia.nnpia_sem.app.person.entity.Person;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity(name = "movie_cast")
@Data
public class MovieCast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne()
    private Movie movie;

    @JsonBackReference
    @ManyToOne()
    private Person person;

    @Column(name = "character_name", length = 500)
    @NotBlank
    private String character;

    @Column(name = "cast_order")
    @NotBlank
    @Min(0)
    private int order;

}
