package hotboys69.dat153.whosetweetisthatappthing.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import hotboys69.dat153.whosetweetisthatappthing.data.not_entities.TweeterCategory;

public class TweeterRandomiser {

    /**
     * We need 4 random, unique usernames from one of our lists of tweeters
     * shuffles the list we choose from and chooses the first 4 elements
     *
     * @return the usernames of 4 random tweeters
     */
    public static List<String> getRandomTweeters(List<TweeterCategory> categories)
    {
        List<String> tweetersToGuessFrom = new ArrayList<>();

        List<String> tweeterList = categories
                .get((int) Math.floor(categories.size() * Math.random())).tweeters.stream()
                .map(tweeter -> tweeter.name)
                .collect(Collectors.toList());

        Collections.shuffle(tweeterList);
        for (int i = 0; i < 4; i++) {
            tweetersToGuessFrom.add(tweeterList.get(i));
        }

        return tweetersToGuessFrom;
    }


    /**
     * chooses the tweeter to choose a tweet from
     *
     * @param tweeters the 4 tweeters to guess from
     * @return one of their usernames
     */
    public static String getRandomTweeter(List<String> tweeters)
    {
        return (tweeters.get((int) Math.floor(Math.random() * tweeters.size())));
    }

}
