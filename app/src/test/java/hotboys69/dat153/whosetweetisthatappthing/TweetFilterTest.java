package hotboys69.dat153.whosetweetisthatappthing;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import hotboys69.dat153.whosetweetisthatappthing.util.TweetFilter;

public class TweetFilterTest {

    @Before
    public void setup()
    {
    }

    @Test
    public void linksNotAllowed()
    {
        String tweet = "check out this sick website http://gumman.one";
        String pitbull = "Not already signed up with @SIRIUSXM? No problem. Listen FREE now through February 7! Just click and listen to #Globalization. https://app.siriusxm.us/pitbull";
        String jaden = "http://smarturl.it/SYRE";

        assertFalse(TweetFilter.isValid(tweet));
        assertFalse(TweetFilter.isValid(pitbull));
        assertFalse(TweetFilter.isValid(jaden));
    }

    @Test
    public void normalTweetsAllowed()
    {
        String tweet = "hey this is a normal tweet";
        String tweet2 = "THIS is ALSO A NORMAL TWEET :D <3 <3";
        String tweet3 = "Let's take it to the top #TuesdayThoughts";

        assertTrue(TweetFilter.isValid(tweet));
        assertTrue(TweetFilter.isValid(tweet2));
        assertTrue(TweetFilter.isValid(tweet3));
    }

    @After
    public void cleanup()
    {
    }
}
