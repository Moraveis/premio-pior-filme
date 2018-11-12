package com.premiopiorfilme.premiopiorfilme;

import com.premiopiorfilme.premiopiorfilme.model.Movie;
import com.premiopiorfilme.premiopiorfilme.service.MovieService;
import java.util.Arrays;
import junit.framework.Assert;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author joao
 */
@SpringBootTest(
        classes = {PremioPiorFilmeApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class MovieControllerIntegrationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private MovieService movieService;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Before
    public void setUp() {
        Movie mockMovieWinner1 = new Movie(1, 1982, "Inchon",
                Arrays.asList("MGM"),
                Arrays.asList("Mitsuharu Ishii"), Boolean.TRUE);

        Movie mockMovieWinner3 = new Movie(2, 1986, "Howard the Duck",
                Arrays.asList("Universal Studios"),
                Arrays.asList("Gloria Katz"), Boolean.TRUE);

        Movie mockMovieWinner4 = new Movie(3, 1986, "Under the Cherry Moon",
                Arrays.asList("Warner Bros."),
                Arrays.asList("Bob Cavallo", "Joe Ruffalo and Steve Fargnoli"), Boolean.TRUE);

        Movie mockMovieWinner2 = new Movie(4, 1980, "Can't Stop the Music",
                Arrays.asList("Associated Film Distribution"),
                Arrays.asList("Allan Carr"), Boolean.FALSE);

        Movie mockMovieWinner5 = new Movie(5, 2001, "Driven",
                Arrays.asList("Warner Bros.", "Franchise Pictures"),
                Arrays.asList("Renny Harlin", "Elie Samaha and Sylvester Stallone"), Boolean.TRUE);

        movieService.getMovies().clear();
        movieService.getMovies().add(mockMovieWinner1);
        movieService.getMovies().add(mockMovieWinner2);
        movieService.getMovies().add(mockMovieWinner3);
        movieService.getMovies().add(mockMovieWinner4);
        movieService.getMovies().add(mockMovieWinner5);
    }

    @Test
    public void testGetWinnersByYear() throws JSONException {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/winners/1982"),
                HttpMethod.GET, entity, String.class);

        String expected = "[{\"id\":1,\"year\":1982,\"title\":\"Inchon\",\"winner\":true}]";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    public void testGetYearsOverTwoWinners() throws JSONException {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/years"),
                HttpMethod.GET, entity, String.class);

        String expected = "{\"years\":[{\"year\":1986,\"winnerCount\":2}]}";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    public void testGetStudiosOrderedCountVictory() throws JSONException {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/studios"),
                HttpMethod.GET, entity, String.class);

        String expected = "{\"studios\": ["
                + "{\"name\": \"Warner Bros.\",\"winCount\": 2},"
                + "{\"name\": \"MGM\",\"winCount\": 1},"
                + "{\"name\": \"Universal Studios\",\"winCount\": 1},"
                + "{\"name\": \"Franchise Pictures\",\"winCount\": 1},"
                + "{\"name\": \"Associated Film Distribution\",\"winCount\": 0}"
                + "]}";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    public void testGetProducersStatistics() throws JSONException {

    }

    @Test
    public void testRemoveMovie() throws JSONException {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<Object> response = restTemplate.exchange(
                createURLWithPort("/movies/4"),
                HttpMethod.DELETE, entity, Object.class);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
