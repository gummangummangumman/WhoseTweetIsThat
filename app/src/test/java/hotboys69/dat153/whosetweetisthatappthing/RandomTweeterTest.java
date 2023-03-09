package hotboys69.dat153.whosetweetisthatappthing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import hotboys69.dat153.whosetweetisthatappthing.data.Tweeters;
import hotboys69.dat153.whosetweetisthatappthing.util.TweeterRandomiser;

public class RandomTweeterTest {

    public ArrayList<String> tweeters;

    @Before
    public void setup()
    {
        Tweeters.musicians = new ArrayList<>(Arrays.asList(
                "rihanna",
                "pitbull",
                "officialjaden",
                "billieeilish",
                "katyperry",
                "justinbieber",
                "taylorswift13",
                "britneyspears",
                "BrunoMars",
                "PostMalone"));
        Tweeters.nonmusicians = new ArrayList<>(Arrays.asList(
                "realDonaldTrump",
                "HillaryClinton",
                "BillGates",
                "elonmusk",
                "BarackObama",
                "DalaiLama",
                "neiltyson",
                "Pontifex"));
        tweeters = TweeterRandomiser.getRandomTweeters();
    }

    @Test
    public void theSizeIsFour()
    {
        tweeters = TweeterRandomiser.getRandomTweeters();
        assertEquals(tweeters.size(), 4);
    }

    @Test
    public void usernameExists()
    {
        String username = TweeterRandomiser.getRandomTweeter(tweeters);
        assertTrue(Tweeters.musicians.contains(username)
                || Tweeters.nonmusicians.contains(username));
    }

    @After
    public void cleanup()
    {
        tweeters = null;
    }

}
