package cz.upce.fei.nnpia.nnpia_sem.app.movie.repository;

import cz.upce.fei.nnpia.nnpia_sem.app.movie.dto.ActorDto;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.dto.CrewDto;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.dto.MovieIdTitleDto;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.dto.MovieListDto;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.entity.Movie;
import cz.upce.fei.nnpia.nnpia_sem.app.movie.entity.RandomMovieDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long>, QueryByExampleExecutor<Movie> {

    @Query("select m from Movie m where m.id = :id")
    Optional<Movie> getById(Long id);

    @Query("SELECT cast.person.id as id, cast.character as role, cast.person.name as name, cast.person.img as img from movie_cast cast WHERE cast.movie.id = :id ORDER BY cast.order")
    List<ActorDto> findAllActorsByMovie(Long id);

    @Query("SELECT  crew.person.id as id, crew.role as role, crew.person.name as name, crew.person.img as img from movie_crew crew WHERE crew.movie.id = :id")
    List<CrewDto> findAllCrewByMovie(Long id);

    @Query("SELECT m.id as id, m.img as img, m.title as title, m.release_date as release_date, AVG(r.score) as avgScore " +
            "from Movie m " +
            "LEFT OUTER JOIN Rating r On m.id = r.movie.id " +
            "group by m.id, m.title " +
            "having lower(m.title) like %:search% "
    )
    Page<MovieListDto> searchAll(String search, Pageable pageable);

    @Query("SELECT m.id as id, m.img as img, m.title as title, m.release_date as release_date, AVG(r.score) as avgScore, " +
            "m.description as description, m.runtime as runtime " +
            "from Movie m " +
            "LEFT OUTER JOIN Rating r On m.id = r.movie.id " +
            "group by m.id, m.title"
    )
    Page<RandomMovieDto> findRandom(Pageable pageable);

    @Query("Select m.id as id, m.title as title from Movie m")
    List<MovieIdTitleDto> findAllIds();
}
