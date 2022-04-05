package cz.upce.fei.nnpia.nnpia_sem.app.watchlist.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.entity.Movie;
import cz.upce.fei.nnpia.nnpia_sem.app.rating.entity.ComposedUserMovieId;
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
public class Watchlist {

    @JsonBackReference
    @ManyToOne(targetEntity = Movie.class)
    @Id
    private Movie movie;

    @JsonBackReference
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @Id
    private User user;

    @Override
    public String toString() {
        return movie.toString() + "";
    }
}

