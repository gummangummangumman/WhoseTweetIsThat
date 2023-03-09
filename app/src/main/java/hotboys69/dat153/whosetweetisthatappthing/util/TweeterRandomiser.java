package hotboys69.dat153.whosetweetisthatappthing.util;

import java.util.ArrayList;
import java.util.Collections;

import hotboys69.dat153.whosetweetisthatappthing.data.Tweeters;

public class TweeterRandomiser {

    /**
     * We need 4 random, unique usernames from one of our lists of tweeters
     * shuffles the list we choose from and chooses the first 4 elements
     *
     * @return the usernames of 4 random tweeters
     */
    public static ArrayList<String> getRandomTweeters()
    {
        ArrayList<String> tweetersToGuessFrom = new ArrayList<>();

        if (Math.random() > 0.5) //50% chance of musicians
        {
            Collections.shuffle(Tweeters.musicians);
            for (int i = 0; i < 4; i++) {
                tweetersToGuessFrom.add(Tweeters.musicians.get(i));
            }
        } else {
            Collections.shuffle(Tweeters.nonmusicians);
            for (int i = 0; i < 4; i++) {
                tweetersToGuessFrom.add(Tweeters.nonmusicians.get(i));
            }
        }

        return tweetersToGuessFrom;
    }


    /**
     * chooses the tweeter to choose a tweet from
     *
     * @param tweeters the 4 tweeters to guess from
     * @return one of their usernames
     */
    public static String getRandomTweeter(ArrayList<String> tweeters)
    {
        return (tweeters.get((int) Math.floor(Math.random() * tweeters.size())));
    }

}
