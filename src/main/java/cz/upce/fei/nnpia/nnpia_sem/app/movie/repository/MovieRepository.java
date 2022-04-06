package cz.upce.fei.nnpia.nnpia_sem.app.movie.repository;

import cz.upce.fei.nnpia.nnpia_sem.app.movie.dto.ActorDto;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.dto.CrewDto;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.dto.MovieListDto;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long>, QueryByExampleExecutor<Movie> {

    @Query("select m from Movie m join fetch m.actors where m.id = :id")
    Optional<Movie> getById(Long id);

    @Query("SELECT cast.person.id as id, cast.character as role, cast.person.name as name, cast.person.img as img from movie_cast cast WHERE cast.movie.id = :id ORDER BY cast.order")
    List<ActorDto> findAllActorsByMovie(Long id);

    @Query("SELECT  crew.person.id as id, crew.role as role, crew.person.name as name, crew.person.img as img from movie_crew crew WHERE crew.movie.id = :id")
    List<CrewDto> findAllCrewByMovie(Long id);

    //@Query(value = "Select * from Movie where title like %?1% or description like %?1%", nativeQuery = true)
    @Query("SELECT m.id as id, m.img as img, m.title as title, AVG(r.score) as avgScore " +
            "from Movie m " +
            "LEFT OUTER JOIN Rating r On m.id = r.movie.id " +
            "group by m.id, m.title " +
            "having m.title like %:search% "
    )
    Page<MovieListDto> searchAll(String search, Pageable pageable);

    //List<MovieListDto> findAllByIds(List<Long> ids);
}
