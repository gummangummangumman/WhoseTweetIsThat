package hotboys69.dat153.whosetweetisthatappthing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by GuMMaN on 12.02.2018.
 */

public class RandomTweeters {


    /**
     * We need 4 random, unique usernames from one of our lists of tweeters
     * shuffles the list we choose from and chooses the first 4 elements
     * @return the usernames of 4 random tweeters
     */
    public static ArrayList<String> getRandomTweeters()
    {
        ArrayList<String> tweetersToGuessFrom = new ArrayList<String>();

        if(Math.random() > 0.5) //50% chance of musicians
        {
            Collections.shuffle(Data.musicians);
            for(int i=0;i<4;i++){
                tweetersToGuessFrom.add(Data.musicians.get(i));
            }
        }else{
            Collections.shuffle(Data.users);
            for(int i=0;i<4;i++){
                tweetersToGuessFrom.add(Data.users.get(i));
            }
        }

        return tweetersToGuessFrom;
    }


    /**
     * chooses the tweeter to choose a tweet from
     * @param tweeters the 4 tweeters to guess from
     * @return one of their usernames
     */
    public static String getRandomTweeter(ArrayList<String> tweeters)
    {
        return (tweeters.get((int) Math.floor(Math.random() * tweeters.size())));
    }

}
