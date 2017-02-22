package app.repository;

import app.api.Rental;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by a508708 on 19/02/2017.
 */
@RepositoryRestResource(collectionResourceRel = "rental", path = "rental")

public interface RentalsRepository extends MongoRepository<Rental, String> {

  //  @Query("{ 'rentalDate' : { \"$gte\" : new Date(ISODate().getTime() - 1000 * 86400 * 3) } }")
 //   public Rental findByName();

}
