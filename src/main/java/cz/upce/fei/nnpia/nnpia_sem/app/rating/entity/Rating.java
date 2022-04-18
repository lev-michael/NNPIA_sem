package cz.upce.fei.nnpia.nnpia_sem.app.rating.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.entity.Movie;
import cz.upce.fei.nnpia.nnpia_sem.app.user.entity.User;
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
    private int score;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @Id
    private Movie movie;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @Id
    private User user;

}
