package cz.upce.fei.nnpia.nnpia_sem.app.actor.repository;

import cz.upce.fei.nnpia.nnpia_sem.app.actor.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Long> {
}
