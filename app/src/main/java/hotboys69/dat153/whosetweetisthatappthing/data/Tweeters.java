package hotboys69.dat153.whosetweetisthatappthing.data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import hotboys69.dat153.whosetweetisthatappthing.data.entities.Category;
import hotboys69.dat153.whosetweetisthatappthing.data.entities.Tweeter;
import hotboys69.dat153.whosetweetisthatappthing.data.not_entities.TweeterCategory;

/**
 * Very ugly class with static singletons!
 * Should be refactored away, put into database and purely based on {@link TweeterCategory}
 */
public class Tweeters {


    /**
     * Singleton collection of possible lists of tweeters to guess from.
     */
    public static List<List<String>> tweeters;

    public static ArrayList<String> non_musicians;
    public static ArrayList<String> musicians;

    public static boolean non_musicians_active = true;
    public static boolean musicians_active = true;

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

    public static void setActive(int id, boolean active)
    {
        switch (id) {
            case -1:
                non_musicians_active = active;
                break;
            case -2:
            default:
                musicians_active = active;
                break;
        }
    }

    private static boolean getActive(int id)
    {
        switch (id) {
            case -1:
                return non_musicians_active;
            case -2:
            default:
                return musicians_active;
        }
    }

    public static List<TweeterCategory> getAsCategories()
    {
        AtomicInteger categoryId = new AtomicInteger(-1);
        return tweeters.stream()
                .map(cat -> {
                    TweeterCategory category = new TweeterCategory();
                    category.category = new Category();
                    category.category.categoryId = categoryId.get();
                    category.category.active = getActive(categoryId.get());
                    category.tweeters = cat.stream()
                            .map(tweeter -> new Tweeter(categoryId.get(), tweeter))
                            .collect(Collectors.toList());

                    categoryId.getAndDecrement();
                    return category;
                })
                .collect(Collectors.toList());
    }

    public static List<TweeterCategory> getActiveCategories()
    {
        return getAsCategories().stream()
                .filter(TweeterCategory::getEffectivelyActive)
                .collect(Collectors.toList());
    }
}
