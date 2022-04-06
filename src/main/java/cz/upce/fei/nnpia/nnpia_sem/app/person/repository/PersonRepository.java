package cz.upce.fei.nnpia.nnpia_sem.app.person.repository;

import cz.upce.fei.nnpia.nnpia_sem.app.person.dto.PersonListItemDto;
import cz.upce.fei.nnpia.nnpia_sem.app.person.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("SELECT distinct(p.id) as id, p.img as img, p.name as name " +
            "from Person p " +
            "JOIN movie_cast c On p.id = c.person.id " +
            "group by p.id, p.name " +
            "having p.name like %:search% "
    )
    Page<PersonListItemDto> searchAllActors(Pageable pageable, String search);

    @EntityGraph(attributePaths = "cast_movies")
    @Query("select p from Person p where p.id = :id")
    Optional<Person> getById(Long id);
}
