package cz.upce.fei.nnpia.nnpia_sem.app.director.entity;

import cz.upce.fei.nnpia.nnpia_sem.app.actor.entity.Actor;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.entity.Movie;

import javax.persistence.*;

@Entity
public class DirectorsMovies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Actor.class)
    private Director director;

    @ManyToOne(targetEntity = Movie.class)
    private Movie movie;

}
