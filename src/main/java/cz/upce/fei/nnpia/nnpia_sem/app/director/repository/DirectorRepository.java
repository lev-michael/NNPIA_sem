package cz.upce.fei.nnpia.nnpia_sem.app.director.repository;

import cz.upce.fei.nnpia.nnpia_sem.app.director.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorRepository extends JpaRepository<Director, Long> {
}
