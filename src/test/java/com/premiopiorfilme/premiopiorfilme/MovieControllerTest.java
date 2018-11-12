package com.premiopiorfilme.premiopiorfilme;

import com.premiopiorfilme.premiopiorfilme.controller.MovieController;
import com.premiopiorfilme.premiopiorfilme.model.Movie;
import com.premiopiorfilme.premiopiorfilme.service.MovieService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 *
 * @author joao
 *
 * Unit Tests
 */
@RunWith(SpringRunner.class)
@WebMvcTest(MovieController.class)
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    /**
     * Test of getMovies method, of class MovieController.
     */
    @Test
    public void testGetMovies() throws Exception {
    }

    /**
     * Test of getWinners method, of class MovieController.
     */
    @Test
    public void testGetWinners() {
    }

    /**
     * Test of getWinnersByYear method, of class MovieController.
     */
    @Test
    public void testGetWinnersByYear() throws Exception {
        Movie mockMovieWinner = new Movie(1, 1980, "Can't Stop the Music",
                Arrays.asList("Associated Film Distribution"),
                Arrays.asList("Allan Carr"), Boolean.TRUE);

        List<Movie> list = new ArrayList<>();
        list.add(mockMovieWinner);

        Mockito.when(movieService.getMovies()).thenReturn(list);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/winners/years/1980")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "[{\"id\":1,\"year\":1980,\"title\":\"Can't Stop the Music\",\"winner\":true}]";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    /**
     * Test of getYearsOverTwoWinners method, of class MovieController.
     */
    @Test
    public void testGetYearsOverTwoWinners() throws Exception {
        Movie mockMovieWinner1 = new Movie(1, 1986, "Howard the Duck",
                Arrays.asList("Universal Studios"),
                Arrays.asList("Gloria Katz"), Boolean.TRUE);

        Movie mockMovieWinner2 = new Movie(2, 1986, "Under the Cherry Moon",
                Arrays.asList("Warner Bros."),
                Arrays.asList("Bob Cavallo", "Joe Ruffalo and Steve Fargnoli"), Boolean.TRUE);

        List<Movie> list = new ArrayList<>();
        list.add(mockMovieWinner1);
        list.add(mockMovieWinner2);

        Mockito.when(movieService.getMovies()).thenReturn(list);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/years")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{\"years\":[{\"year\":1986,\"winnerCount\":2}]}";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    /**
     * Test of getStudiosOrderedCountVictory method, of class MovieController.
     */
    @Test
    public void testGetStudiosOrderedCountVictory() throws Exception {
        Movie mockMovieWinner1 = new Movie(1, 1982, "Inchon",
                Arrays.asList("MGM"),
                Arrays.asList("Mitsuharu Ishii"), Boolean.TRUE);

        Movie mockMovieWinner2 = new Movie(2, 1995, "Showgirls",
                Arrays.asList("MGM", "United Artists"),
                Arrays.asList("Charles Evans and Alan Marshall", "Joe Ruffalo and Steve Fargnoli"), Boolean.TRUE);

        Movie mockMovieWinner3 = new Movie(3, 2001, "Driven",
                Arrays.asList("Warner Bros.", "Franchise Pictures"),
                Arrays.asList("Renny Harlin", "Elie Samaha and Sylvester Stallone"), Boolean.FALSE);

        List<Movie> list = new ArrayList<>();
        list.add(mockMovieWinner1);
        list.add(mockMovieWinner2);
        list.add(mockMovieWinner3);

        Mockito.when(movieService.getMovies()).thenReturn(list);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/studios")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{\"studios\": ["
                + "{\"name\": \"MGM\",\"winCount\": 2},"
                + "{\"name\": \"United Artists\",\"winCount\": 1},"
                + "{\"name\": \"Warner Bros.\",\"winCount\": 0},"
                + "{\"name\": \"Franchise Pictures\",\"winCount\": 0}"
                + "]}";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    /**
     * Test of getProducersStatistics method, of class MovieController.
     */
    @Test
    public void testGetProducersStatistics() {
    }

    /**
     * Test of removeMovie method, of class MovieController.
     */
    @Test
    public void testRemoveMovie() throws Exception {
        Movie mockMovieWinner1 = new Movie(1, 1982, "Inchon",
                Arrays.asList("MGM"),
                Arrays.asList("Mitsuharu Ishii"), Boolean.TRUE);

        Movie mockMovieWinner2 = new Movie(2, 1995, "Showgirls",
                Arrays.asList("MGM", "United Artists"),
                Arrays.asList("Charles Evans and Alan Marshall", "Joe Ruffalo and Steve Fargnoli"), Boolean.TRUE);

        Movie mockMovieWinner3 = new Movie(3, 2001, "Driven",
                Arrays.asList("Warner Bros.", "Franchise Pictures"),
                Arrays.asList("Renny Harlin", "Elie Samaha and Sylvester Stallone"), Boolean.FALSE);

        List<Movie> list = new ArrayList<>();
        list.add(mockMovieWinner1);
        list.add(mockMovieWinner2);
        list.add(mockMovieWinner3);

        Mockito.when(movieService.getMovies()).thenReturn(list);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/movies/3")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

    }

}
