package hotboys69.dat153.whosetweetisthatappthing;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import hotboys69.dat153.whosetweetisthatappthing.data.Tweeters;
import hotboys69.dat153.whosetweetisthatappthing.util.TweeterRandomiser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by GuMMaN on 12.02.2018.
 */

public class RandomTweeterTest {

    public ArrayList<String> tweeters;

    @Before
    public void setup(){
        tweeters = TweeterRandomiser.getRandomTweeters();
    }

    @Test
    public void theSizeIsFour() {
        tweeters = TweeterRandomiser.getRandomTweeters();
        assertEquals(tweeters.size(), 4);
    }

    @Test
    public void usernameExists(){
        String username = TweeterRandomiser.getRandomTweeter(tweeters);
        assertTrue(Tweeters.musicians.contains(username) || Tweeters.nonmusicians.contains(username));
    }

    @After
    public void cleanup(){
        tweeters = null;
    }

}
