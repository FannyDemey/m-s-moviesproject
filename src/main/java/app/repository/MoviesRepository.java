package app.repository;

import app.api.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

/**
 * Created by a508708 on 19/02/2017.
 */
@RepositoryRestResource(collectionResourceRel = "movie", path = "movie")
public interface MoviesRepository extends MongoRepository<Movie, String> {

    public Optional<Movie> findByName(@Param("name") String name);
    public List<Movie> findByGenre(@Param("genre") String genre);

    @Query("{ 'expiration_date' : { \"$gte\" : ?0 } }")
    public List<Movie> findRentedMovies(@Param("date")String date);
}