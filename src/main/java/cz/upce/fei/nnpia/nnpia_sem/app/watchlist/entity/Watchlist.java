package cz.upce.fei.nnpia.nnpia_sem.app.watchlist.entity;

import cz.upce.fei.nnpia.nnpia_sem.app.movie.entity.Movie;
import cz.upce.fei.nnpia.nnpia_sem.app.rating.entity.ComposedUserMovieId;
import cz.upce.fei.nnpia.nnpia_sem.app.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;


@Entity
@IdClass(ComposedUserMovieId.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Watchlist {

    @ManyToOne(targetEntity = Movie.class)
    @Id
    private Movie movie;

    @ManyToOne(targetEntity = User.class)
    @Id
    private User user;

}

