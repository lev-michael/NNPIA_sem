package cz.upce.fei.nnpia.nnpia_sem.app.genre.repository;

import cz.upce.fei.nnpia.nnpia_sem.app.genre.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
