package cz.upce.fei.nnpia.nnpia_sem.app.movie.repository;

import cz.upce.fei.nnpia.nnpia_sem.app.movie.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("select b from Movie b join fetch b.actors where b.id = :id")
    Optional<Movie> getById(Long id);


    @Query("SELECT cast.character, cast.person.name, cast.person.img from movie_cast cast WHERE cast.movie.id = :id ORDER BY cast.order")
    List<Object> findAllActorsByMovie(Long id);

    @Query("SELECT  crew.role, crew.person.name, crew.person.img from movie_crew crew WHERE crew.movie.id = :id")
    List<Object> findAllCrewByMovie(Long id);
}
