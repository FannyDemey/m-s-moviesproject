package app.controller;

import app.Application;
import app.api.Movie;
import app.api.User;
import app.repository.MoviesCustomRequestRepository;
import app.repository.MoviesRepository;
import app.repository.UserRepository;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Calendar;
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

    private HttpMessageConverter mappingJackson2HttpMessageConverter;
    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));


    private List<Movie> movies;
    private User user;

    @Before
    public void setup(){
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 7);
        this.moviesRepository.deleteAll();
        user = userRepository.save(User.builder().id(1).email("john.doe@gmail.com").type("regular").build());
        moviesRepository.save(Movie.builder().id(20).name("Good Morning England").genre("Comedy")
                .year("2001").available(true).build());
        moviesRepository.save(Movie.builder().id(30).name("Dirty Dancing").genre("Comedy")
                .year("1995").available(true).build());
        moviesRepository.save(Movie.builder().id(40).name("L'auberge espagnole").genre("Comedy")
                .year("2007").available(true).build());
        moviesRepository.save(Movie.builder().id(50).name("Shutter island").genre("Thriller")
                .year("2011").available(false).renter(user).available(false).expiration_date(cal.getTime()).build());
        movies = moviesRepository.findAll();
    }

    @Test
    public void rentMovie() throws Exception{
        mockMvc.perform(put("/movies/Dirty Dancing")
                .content(this.json(user))
                .contentType(contentType))
                .andExpect(status().isCreated());
    }

    @Test
    public void rentMovieNotAvailable() throws Exception{
        mockMvc.perform(put("/movies/Shutter island")
                .content(this.json(user))
                .contentType(contentType))
                .andExpect(status().isUnauthorized());
    }


    @Test
    public void getRentedMovies() throws Exception{
        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$",hasSize(1)));
    }


    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }

}