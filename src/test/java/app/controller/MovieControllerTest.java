package app.controller;

import app.Application;
import app.api.Movie;
import app.api.User;
import app.repository.MoviesCustomRequestRepository;
import app.repository.MoviesRepository;
import app.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
/**
 * Created by a508708 on 22/02/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class MovieControllerTest {

    private MockMvc mockMvc;

    @Autowired
    MoviesCustomRequestRepository moviesCustomRequestRepository;

    @Autowired
    MoviesRepository moviesRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private List<Movie> movies;
    private User user;

    @Before
    public void setup(){
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

        this.moviesRepository.deleteAll();
        user = userRepository.save(User.builder().id(1).email("john.doe@gmail.com").type("regular").build());
        moviesRepository.save(Movie.builder().id(20).name("Good Morning England").genre("Comedy")
                .year("2001").available(true).build());
        moviesRepository.save(Movie.builder().id(30).name("Dirty Dancing").genre("Comedy")
                .year("1995").available(true).build());
        moviesRepository.save(Movie.builder().id(40).name("L'auberge espagnole").genre("Comedy")
                .year("2007").available(true).build());
        moviesRepository.save(Movie.builder().id(50).name("Shutter island").genre("Thriller")
                .year("2011").available(false).renter(user).available(false).expiration_date(new Date()).build());
        movies = moviesRepository.findAll();
    }

    @Test
    public void rentMovie() throws Exception{
        mockMvc.perform(put("/movies?movieName=Dirty Dancing&userEmail=john.doe@gmail.com"))
                .andExpect(status().isCreated());
    }

}