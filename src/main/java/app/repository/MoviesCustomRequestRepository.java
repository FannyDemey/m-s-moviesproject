package app.repository;

import app.api.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by a508708 on 22/02/2017.
 */
@Repository
public class MoviesCustomRequestRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    public List<Movie> findRentedMovies(){
        return mongoTemplate.find(Query.query(new Criteria().where("expiration_date").gt(new Date())), Movie.class);
    }
}
