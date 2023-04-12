package hotboys69.dat153.whosetweetisthatappthing.data;

import java.util.ArrayList;
import java.util.List;

public class Tweeters {


    /**
     * Singleton collection of possible lists of tweeters to guess from
     */
    public static List<List<String>> tweeters;

    public static ArrayList<String> non_musicians;
    public static ArrayList<String> musicians;
    static {
        tweeters = new ArrayList<>();
        tweeters.add(musicians);
        tweeters.add(non_musicians);
    }


    public static boolean defaultValues = true;//not fetched from API

    public static void repopulateDefaultLists()
    {
        tweeters.set(0, non_musicians);
        tweeters.set(1, musicians);
    }
}
