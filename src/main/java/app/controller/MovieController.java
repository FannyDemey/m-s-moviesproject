package app.controller;

import app.api.Movie;
import app.api.User;
import app.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Fanny Demey on 21/02/2017.
 */

@RestController
@RequestMapping("/movies")
@Slf4j
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    @RequestMapping(value = "/{movieName}", method = RequestMethod.PUT)
    ResponseEntity<?> rent(@PathVariable(value = "movieName") String movieName, @RequestBody User user,
                           HttpServletResponse response) {
        log.debug("Renting {} for user {}", movieName, user.getEmail());
        Movie movie = movieService.rentMovie(movieName, user.getEmail());
        if (movie != null) {
            response.setStatus(HttpServletResponse.SC_CREATED);
            return ResponseEntity.ok("Movie rented");
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return ResponseEntity.noContent().build();

        }
    }

    @RequestMapping(method = RequestMethod.GET)
    List<Movie> getRentedMovies() {
        return movieService.findRentedMovie();
    }
}
