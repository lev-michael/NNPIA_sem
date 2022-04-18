package cz.upce.fei.nnpia.nnpia_sem.app.movie.repository;

import cz.upce.fei.nnpia.nnpia_sem.app.movie.entity.MovieCrew;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieCrewRepository extends JpaRepository<MovieCrew, Long> {
}
