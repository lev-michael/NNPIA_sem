package cz.upce.fei.nnpia.nnpia_sem.app.actor.entity;

import cz.upce.fei.nnpia.nnpia_sem.app.movie.entity.Movie;

import javax.persistence.*;

@Entity
public class ActorsInMovies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Actor.class)
    private Actor actor;

    @ManyToOne(targetEntity = Movie.class)
    private Movie movie;

}
