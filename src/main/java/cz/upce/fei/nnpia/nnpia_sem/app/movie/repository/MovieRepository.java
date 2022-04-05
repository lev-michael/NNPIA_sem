package cz.upce.fei.nnpia.nnpia_sem.app.movie.repository;

import cz.upce.fei.nnpia.nnpia_sem.app.movie.dto.ActorDto;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.dto.CrewDto;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long>, QueryByExampleExecutor<Movie> {

    @Query("select b from Movie b join fetch b.actors where b.id = :id")
    Optional<Movie> getById(Long id);

    @Query("SELECT cast.character as role, cast.person.name as name, cast.person.img as img from movie_cast cast WHERE cast.movie.id = :id ORDER BY cast.order")
    List<ActorDto> findAllActorsByMovie(Long id);

    @Query("SELECT  crew.role as role, crew.person.name as name, crew.person.img as img from movie_crew crew WHERE crew.movie.id = :id")
    List<CrewDto> findAllCrewByMovie(Long id);

    @Query(value = "Select * from Movie where title like %?1% or description like %?1%", nativeQuery = true)
    Page<Movie> searchAll(String search, Pageable pageable);
}
