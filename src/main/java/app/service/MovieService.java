package app.service;

import app.api.Movie;
import app.api.User;
import app.exception.MovieNotFoundException;
import app.repository.MoviesCustomRequestRepository;
import app.repository.MoviesRepository;
import app.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by a508708 on 21/02/2017.
 */
@Slf4j
@Service
public class MovieService {
    private final MoviesRepository moviesRepository;
    private final MoviesCustomRequestRepository moviesCustomRequestRepository;
    private final UserRepository userRepository;

    @Autowired
    public MovieService(MoviesRepository moviesRepository,MoviesCustomRequestRepository moviesCustomRequestRepository,
                        UserRepository userRepository) {
        this.moviesRepository = moviesRepository;
        this.moviesCustomRequestRepository = moviesCustomRequestRepository;
        this.userRepository = userRepository;
    }

    public List<Movie> findRentedMovie() {
        List<Movie> movies = moviesCustomRequestRepository.findRentedMovies();
        log.debug("movies"+movies.size());
        return movies;
    }


    public Movie rentMovie(String movieName, String userEmail) {
        Optional<Movie> movieOptional = moviesRepository.findByName(movieName);
        Optional<User> userOptional = userRepository.findByEmail(userEmail);
        if(movieOptional.isPresent() && userOptional.isPresent()){
            Movie movie = movieOptional.get();
            User user = userOptional.get();
            if (movie.getAvailable()) {
                if (user.getType().equals("registered")) {
                    movie.setExpiration_date(this.getDate(7));
                } else {
                    movie.setExpiration_date(this.getDate(3));

                }
                movie.setAvailable(false);
                movie.setRenter(user);
                log.info("movie:" + movie.toString());
                return moviesRepository.save(movie);
            }
        }
        return null;
    }

    public Date getDate(Integer nbDaysAllowed) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, nbDaysAllowed);
        return cal.getTime();
    }
}
