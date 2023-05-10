package hotboys69.dat153.whosetweetisthatappthing.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import hotboys69.dat153.whosetweetisthatappthing.data.TweeterRepository;
import hotboys69.dat153.whosetweetisthatappthing.data.entities.Category;
import hotboys69.dat153.whosetweetisthatappthing.data.entities.Tweeter;
import hotboys69.dat153.whosetweetisthatappthing.data.not_entities.TweeterCategory;

public class TweeterViewModel extends AndroidViewModel {

    private final TweeterRepository tweeterRepository;

    public TweeterViewModel(Application application)
    {
        super(application);
        tweeterRepository = new TweeterRepository(application);
    }

    public LiveData<List<TweeterCategory>> getCategoriesLiveData()
    {
        return tweeterRepository.getAllCategories();
    }

    public void insert(Category category)
    {
        tweeterRepository.insertCategory(category);
    }

    public boolean deleteCategory(TweeterCategory category)
    {
        if (!category.tweeters.isEmpty()) {
            return false;
        }
        tweeterRepository.deleteCategory(category.category);
        return true;
    }

    public boolean insertTweeter(TweeterCategory category, String name)
    {
        if (name.contains(",")) {
            List<String> validHandles = Arrays.stream(name.split(","))
                    .map(handle -> handle.replaceAll("@", ""))
                    .map(String::trim)
                    .filter(this::handleIsValid)
                    .collect(Collectors.toList());

            if (validHandles.isEmpty()) {
                return false;
            }

            validHandles.forEach(handle -> insertTweeter(category, handle));
            return true;
        }

        if (!handleIsValid(name)) {
            return false;
        }

        tweeterRepository.insertTweeter(new Tweeter(category.category.categoryId, name));
        return true;
    }

    private boolean handleIsValid(String handle)
    {
        return handle.length() >= 4 && handle.length() <= 15;
    }

    public void deleteTweeter(Tweeter tweeter)
    {
        if (tweeter.tweeterId < 0) {
            return;
        }
        tweeterRepository.deleteTweeter(tweeter);
    }

}
