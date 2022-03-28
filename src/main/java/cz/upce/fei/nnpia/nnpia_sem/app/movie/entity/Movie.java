package cz.upce.fei.nnpia.nnpia_sem.app.movie.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import cz.upce.fei.nnpia.nnpia_sem.app.genre.entity.Genre;
import cz.upce.fei.nnpia.nnpia_sem.app.person.entity.Person;
import cz.upce.fei.nnpia.nnpia_sem.app.rating.entity.Rating;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Data
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank()
    private String title;

    @Column(length = 2500)
    @NotBlank()
    private String description;

    @Column
    @Min(1)
    private int runtime;

    @Column
    @NotBlank
    private Date release_date;

    @OneToMany(mappedBy = "id")
    @JsonIgnoreProperties({"cast_movies", "crew_movies"})
    private Set<Person> actors;

    @OneToMany(mappedBy = "id")
    @JsonIgnoreProperties({"cast_movies", "crew_movies"})
    private Set<Person> crew;

    @OneToMany(mappedBy = "movie")
    private Set<Rating> scores;

    @OneToMany(mappedBy = "id")
    private Set<Genre> genres;
}
