package cz.upce.fei.nnpia.nnpia_sem.app.person.repository;

import cz.upce.fei.nnpia.nnpia_sem.app.person.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
