package hotboys69.dat153.whosetweetisthatappthing.util;

import android.content.res.Resources;
import android.util.Log;

import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

public class RandomTweetPicker
{
    /**
     *
     * @param tweets
     * @return one valid tweet
     */
    public static String getRandomTweet (List<Tweet> tweets) throws Resources.NotFoundException
    {
        int number = (int) Math.floor(Math.random() * tweets.size());
        String randomTweet = tweets.get(number).text;

        final int FIRST_NUMBER = number;

        //we should keep looking as long as the tweet isn't valid
        boolean keepLooking = !TweetFilter.isValid(randomTweet);

        while(keepLooking){
            if(number == tweets.size() -1)
            {
                number = 0;
            } else {
                number++;
            }
            randomTweet = tweets.get(number).text;
            keepLooking = !TweetFilter.isValid(randomTweet);

            if (keepLooking)
            {
                Log.w("FILTERED_TWEET", randomTweet);
            }

            //if it has looped all the way around, just give up
            if (number == FIRST_NUMBER)
            {
                Log.w("INVALID_TWEETS", "all the tweets from " + tweets.get(0).user.screenName + " were invalid. Giving up.");
                keepLooking = false;
                throw new Resources.NotFoundException("Could not find valid tweet");
            }
        }
        return randomTweet;
    }
}
