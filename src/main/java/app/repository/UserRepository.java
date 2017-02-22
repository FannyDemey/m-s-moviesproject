package app.repository;

import app.api.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

/**
 * Created by a508708 on 19/02/2017.
 */
@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository  extends MongoRepository<User, String> {
    Optional<User> findByEmail(@Param("email") String email);

}