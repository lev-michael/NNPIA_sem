package cz.upce.fei.nnpia.nnpia_sem.app.movie.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import cz.upce.fei.nnpia.nnpia_sem.app.genre.entity.GenreMovies;
import cz.upce.fei.nnpia.nnpia_sem.app.person.entity.Person;
import cz.upce.fei.nnpia.nnpia_sem.app.rating.entity.Rating;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank
    private String title;

    @Column(length = 2500)
    @NotBlank()
    private String description;

    @Column
    private String img;

    @Column
    @Min(1)
    private int runtime;

    @Column
    @NotBlank
    private Date release_date;

    //@JsonManagedReference
    @JsonIgnore
    @OneToMany(mappedBy = "movie", targetEntity = MovieCast.class, fetch = FetchType.LAZY)
    private List<Person> actors;

    // @JsonManagedReference
    @JsonIgnore
    @OneToMany(mappedBy = "movie", targetEntity = MovieCrew.class, fetch = FetchType.LAZY)
    private List<Person> crew;

    @JsonManagedReference
    @OneToMany(mappedBy = "movie", targetEntity = Rating.class, fetch = FetchType.LAZY)
    private List<Rating> scores;

    @JsonManagedReference
    @OneToMany(mappedBy = "movie", targetEntity = GenreMovies.class, fetch = FetchType.LAZY)
    private List<GenreMovies> genres;

    @Override
    public String toString() {
        return id + "";
    }
}
