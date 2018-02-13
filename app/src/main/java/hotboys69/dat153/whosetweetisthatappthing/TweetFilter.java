package hotboys69.dat153.whosetweetisthatappthing;

/**
 * Created by Trygve on 13.02.2018.
 */

public class TweetFilter {


    /**
     * checks a number of things for the tweet to be shown or not
     *
     * @param tweet the tweet in question
     * @return true for valid, false for not
     */
    public static boolean isValid(String tweet){
        return (isNotARetweet(tweet) && doesNotContainLinks(tweet));
    }


    /**
     * checks if it's a retweet. The two first characters will be 'RT @'
     */
    private static boolean isNotARetweet(String tweet){
        boolean valid = true;

        char[] chars = tweet.toCharArray();
        if(chars.length>4){
            if(chars[0]=='R' && chars[1]=='T' && chars[3]=='@'){
                valid = false;
            }
        }

        return valid;
    }

    /**
     * checks if it contains a link
     */
    private static boolean doesNotContainLinks(String tweet){
        boolean valid = true;

        if(tweet.contains("http")){
            valid = false;
        }

        return valid;
    }
}
