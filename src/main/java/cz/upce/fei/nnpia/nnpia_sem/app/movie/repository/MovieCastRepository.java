package cz.upce.fei.nnpia.nnpia_sem.app.movie.repository;

import cz.upce.fei.nnpia.nnpia_sem.app.movie.entity.MovieCast;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieCastRepository extends JpaRepository<MovieCast, Long> {
}
