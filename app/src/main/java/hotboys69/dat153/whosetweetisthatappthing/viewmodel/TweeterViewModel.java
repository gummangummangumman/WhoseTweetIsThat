package hotboys69.dat153.whosetweetisthatappthing.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

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

    public void insertTweeter(TweeterCategory category)
    {
        tweeterRepository.insertTweeter(new Tweeter(category.category.categoryId, ""));
    }

    public void deleteTweeter(Tweeter tweeter)
    {
        if (tweeter.tweeterId < 0) {
            return;
        }
        tweeterRepository.deleteTweeter(tweeter);
    }

}
