package hotboys69.dat153.whosetweetisthatappthing.data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hotboys69.dat153.whosetweetisthatappthing.data.entities.Category;
import hotboys69.dat153.whosetweetisthatappthing.data.entities.Tweeter;
import hotboys69.dat153.whosetweetisthatappthing.data.not_entities.TweeterCategory;

/**
 * Should be refactored away.
 * Should be put into database and purely based on {@link TweeterCategory}
 */
public class Tweeters {


    /**
     * Singleton collection of possible lists of tweeters to guess from.
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

    public static List<TweeterCategory> getAsCategories()
    {
        return tweeters.stream()
                .map(cat -> {
                    TweeterCategory category = new TweeterCategory();
                    category.category = new Category();
                    category.category.categoryId = -1;
                    category.tweeters = cat.stream()
                            .map(tweeter -> new Tweeter(-1, tweeter))
                            .collect(Collectors.toList());

                    return category;
                })
                .collect(Collectors.toList());
    }
}
