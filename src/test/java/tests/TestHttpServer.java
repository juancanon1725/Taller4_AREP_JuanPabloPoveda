package tests;


import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.example.Cache;

import java.util.ArrayList;
import java.util.HashMap;

public class TestHttpServer extends TestCase{
    public TestHttpServer(String testName){
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( TestHttpServer.class );
    }

    /**
     * Prueba que verifica la descripción con su respectivo título de película
     */
    public void testSearchTitles()
    {
        Cache.getInstance().clear();
        HashMap<String, String> responses = new HashMap<>();
        //responses.put("Batman", "[{\"Title\":\"Batman\",\"Year\":\"1989\",\"Rated\":\"PG-13\",\"Released\":\"23 Jun 1989\",\"Runtime\":\"126 min\",\"Genre\":\"Action, Adventure\",\"Director\":\"Tim Burton\",\"Writer\":\"Bob Kane, Sam Hamm, Warren Skaaren\",\"Actors\":\"Michael Keaton, Jack Nicholson, Kim Basinger\",\"Plot\":\"The Dark Knight of Gotham City begins his war on crime with his first major enemy being Jack Napier, a criminal who becomes the clownishly homicidal Joker.\",\"Language\":\"English, French, Spanish\",\"Country\":\"United States, United Kingdom\",\"Awards\":\"Won 1 Oscar. 10 wins & 26 nominations total\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BMTYwNjAyODIyMF5BMl5BanBnXkFtZTYwNDMwMDk2._V1_SX300.jpg\",\"Ratings\":[{\"Source\":\"Internet Movie Database\",\"Value\":\"7.5/10\"},{\"Source\":\"Rotten Tomatoes\",\"Value\":\"76%\"},{\"Source\":\"Metacritic\",\"Value\":\"69/100\"}],\"Metascore\":\"69\",\"imdbRating\":\"7.5\",\"imdbVotes\":\"392,450\",\"imdbID\":\"tt0096895\",\"Type\":\"movie\",\"DVD\":\"24 Jul 2014\",\"BoxOffice\":\"$251,409,241\",\"Production\":\"N/A\",\"Website\":\"N/A\",\"Response\":\"True\"}]");
        //responses.put("The avengers", "[{\"Title\":\"The Avengers\",\"Year\":\"2012\",\"Rated\":\"PG-13\",\"Released\":\"04 May 2012\",\"Runtime\":\"143 min\",\"Genre\":\"Action, Sci-Fi\",\"Director\":\"Joss Whedon\",\"Writer\":\"Joss Whedon, Zak Penn\",\"Actors\":\"Robert Downey Jr., Chris Evans, Scarlett Johansson\",\"Plot\":\"Earth's mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.\",\"Language\":\"English, Russian\",\"Country\":\"United States\",\"Awards\":\"Nominated for 1 Oscar. 38 wins & 80 nominations total\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BNDYxNjQyMjAtNTdiOS00NGYwLWFmNTAtNThmYjU5ZGI2YTI1XkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg\",\"Ratings\":[{\"Source\":\"Internet Movie Database\",\"Value\":\"8.0/10\"},{\"Source\":\"Rotten Tomatoes\",\"Value\":\"91%\"},{\"Source\":\"Metacritic\",\"Value\":\"69/100\"}],\"Metascore\":\"69\",\"imdbRating\":\"8.0\",\"imdbVotes\":\"1,426,217\",\"imdbID\":\"tt0848228\",\"Type\":\"movie\",\"DVD\":\"22 Jun 2014\",\"BoxOffice\":\"$623,357,910\",\"Production\":\"N/A\",\"Website\":\"N/A\",\"Response\":\"True\"}]");
        responses.put("Yocman", "[{\"Response\":\"False\",\"Error\":\"Movie not found!\"}]");

        ArrayList<ThreadTest> threadTests = new ArrayList<>();
        //threadTests.add(new ThreadTest("Batman"));
        //threadTests.add(new ThreadTest("The avengers"));
        threadTests.add(new ThreadTest("Yocman"));


        for(ThreadTest threadTest: threadTests){
            threadTest.start();
        }
        for(ThreadTest threadTest: threadTests){
            try {
                threadTest.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        for(ThreadTest threadTest: threadTests)
            assertEquals(responses.get(threadTest.getTitle()), threadTest.getResponse());
    }

    /**
     * Prueba que se esté guardando correctamente el caché
     */
    public void testCacheConcurrent(){
        Cache.getInstance().clear();
        ArrayList<ThreadTest> threadTests = new ArrayList<>();
        threadTests.add(new ThreadTest("Batman"));
        threadTests.add(new ThreadTest("Batman"));
        threadTests.add(new ThreadTest("Batman"));
        threadTests.add(new ThreadTest("Batman"));
        threadTests.add(new ThreadTest("Batman"));

        threadTests.add(new ThreadTest("Flash"));
        threadTests.add(new ThreadTest("Flash"));
        threadTests.add(new ThreadTest("Flash"));
        threadTests.add(new ThreadTest("Flash"));
        threadTests.add(new ThreadTest("Flash"));


        for(ThreadTest threadTest: threadTests){
            threadTest.start();
        }
        for(ThreadTest threadTest: threadTests){
            try {
                threadTest.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        assertEquals(2, Cache.getInstance().size());
    }

    public void testCacheConcurrentPersisten(){
        Cache cache = Cache.getInstance();
        cache.clear();
        HashMap<String, String> responses = new HashMap<>();
        Cache.getInstance().clear();
        ArrayList<ThreadTest> threadTests = new ArrayList<>();
        threadTests.add(new ThreadTest("Batman"));
        threadTests.add(new ThreadTest("Batman"));

        threadTests.add(new ThreadTest("Flash"));
        threadTests.add(new ThreadTest("Flash"));

        responses.put("Batman", "[{\"Title\":\"Batman\",\"Year\":\"1989\",\"Rated\":\"PG-13\",\"Released\":\"23 Jun 1989\",\"Runtime\":\"126 min\",\"Genre\":\"Action, Adventure\",\"Director\":\"Tim Burton\",\"Writer\":\"Bob Kane, Sam Hamm, Warren Skaaren\",\"Actors\":\"Michael Keaton, Jack Nicholson, Kim Basinger\",\"Plot\":\"The Dark Knight of Gotham City begins his war on crime with his first major enemy being Jack Napier, a criminal who becomes the clownishly homicidal Joker.\",\"Language\":\"English, French, Spanish\",\"Country\":\"United States, United Kingdom\",\"Awards\":\"Won 1 Oscar. 10 wins & 26 nominations total\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BMTYwNjAyODIyMF5BMl5BanBnXkFtZTYwNDMwMDk2._V1_SX300.jpg\",\"Ratings\":[{\"Source\":\"Internet Movie Database\",\"Value\":\"7.5/10\"},{\"Source\":\"Rotten Tomatoes\",\"Value\":\"76%\"},{\"Source\":\"Metacritic\",\"Value\":\"69/100\"}],\"Metascore\":\"69\",\"imdbRating\":\"7.5\",\"imdbVotes\":\"392,450\",\"imdbID\":\"tt0096895\",\"Type\":\"movie\",\"DVD\":\"24 Jul 2014\",\"BoxOffice\":\"$251,409,241\",\"Production\":\"N/A\",\"Website\":\"N/A\",\"Response\":\"True\"}]");
        responses.put("Flash", "[{\"Title\":\"Flash\",\"Year\":\"1997\",\"Rated\":\"NOT RATED\",\"Released\":\"21 Dec 1997\",\"Runtime\":\"90 min\",\"Genre\":\"Drama, Family\",\"Director\":\"Simon Wincer\",\"Writer\":\"Monte Merrick\",\"Actors\":\"Lucas Black, Brian Kerwin, Shawn Toovey, Tom Nowicki\",\"Plot\":\"A boy falls in love with a horse named Flash that's for sale. He gets a job to earn the money to buy the horse, but he's forced to sell when the family falls upon hard times. This ...\",\"Language\":\"English\",\"Country\":\"USA\",\"Awards\":\"4 nominations.\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BMTkwNzY4Mzg5N15BMl5BanBnXkFtZTcwNzE4MzEyMQ@@._V1_SX300.jpg\",\"Ratings\":[{\"Source\":\"Internet Movie Database\",\"Value\":\"7.1/10\"}],\"Metascore\":\"N/A\",\"imdbRating\":\"7.1\",\"imdbVotes\":\"694\",\"imdbID\":\"tt0136199\",\"Type\":\"movie\",\"DVD\":\"N/A\",\"BoxOffice\":\"N/A\",\"Production\":\"N/A\",\"Website\":\"N/A\",\"Response\":\"True\"}]\n");

        for(ThreadTest threadTest: threadTests){
            threadTest.start();
        }
        for(ThreadTest threadTest: threadTests){
            try {
                threadTest.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        for(ThreadTest threadTest: threadTests){
            assertEquals(threadTest.getResponse(), cache.getMovieDescription(threadTest.getTitle()));
        }

    }
}