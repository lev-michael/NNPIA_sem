package cz.upce.fei.nnpia.nnpia_sem.app.movie.repository;

import cz.upce.fei.nnpia.nnpia_sem.app.movie.entity.MovieCast;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieCastRepository extends JpaRepository<MovieCast, Long> {
    MovieCast findByMovie_idAndPerson_id(Long movie_id, Long person_id);
}
