package cz.upce.fei.nnpia.nnpia_sem.app.rating.entity;

import cz.upce.fei.nnpia.nnpia_sem.app.movie.entity.Movie;
import cz.upce.fei.nnpia.nnpia_sem.app.user.entity.User;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@IdClass(ComposedUserMovieId.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating {

    @Column
    @NotNull
    @Min(1)
    @Max(10)
    private int score;

    @ManyToOne
    @Id
    private Movie movie;

    @ManyToOne
    @Id
    private User user;

}
