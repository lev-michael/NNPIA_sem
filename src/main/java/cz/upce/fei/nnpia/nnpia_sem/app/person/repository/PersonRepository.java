package cz.upce.fei.nnpia.nnpia_sem.app.person.repository;

import cz.upce.fei.nnpia.nnpia_sem.app.person.dto.PersonIdNameDto;
import cz.upce.fei.nnpia.nnpia_sem.app.person.dto.PersonListItemDto;
import cz.upce.fei.nnpia.nnpia_sem.app.person.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("SELECT p.id as id, p.img as img, p.name as name, p.birthday as birthday " +
            "from Person p " +
            "where lower(p.name) like %:search% "
    )
    Page<PersonListItemDto> searchAllActors(Pageable pageable, String search);

    @Query("select p from Person p where p.id = :id")
    Optional<Person> getById(Long id);

    @Query("select p.id as id, p.name as name from Person p")
    List<PersonIdNameDto> getAllPersonsNamesAndIds();
}
