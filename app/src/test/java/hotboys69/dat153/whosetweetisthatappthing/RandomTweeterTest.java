package hotboys69.dat153.whosetweetisthatappthing;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by GuMMaN on 12.02.2018.
 */

public class RandomTweeterTest {

    public ArrayList<String> tweeters;

    @Before
    public void setup(){
        tweeters = RandomTweeters.getRandomTweeters();
    }

    @Test
    public void theSizeIsFour() {
        tweeters = RandomTweeters.getRandomTweeters();
        assertEquals(tweeters.size(), 4);
    }

    @Test
    public void usernameExists(){
        String username = RandomTweeters.getRandomTweeter(tweeters);
        assertTrue(Data.musicians.contains(username) || Data.nonmusicians.contains(username));
    }

    @After
    public void cleanup(){
        tweeters = null;
    }

}
