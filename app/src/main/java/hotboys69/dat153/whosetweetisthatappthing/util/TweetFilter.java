package hotboys69.dat153.whosetweetisthatappthing.util;

public class TweetFilter {


    /**
     * checks a number of things for the tweet to be shown or not
     *
     * @param tweet the tweet in question
     * @return true for valid, false for not
     */
    public static boolean isValid(String tweet){
        return (containsMoreThanALink(tweet));
    }

    /**
     * checks if it contains more text than a link
     */
    private static boolean containsMoreThanALink (String tweet){
        boolean valid = true;

        if(tweet.contains("http")){
            if(!tweet.contains("t.co/")){
                valid = false;
            }else if(tweet.split("http")[0].length() < 5){
                valid = false;
            }
        }

        return valid;
    }
}
