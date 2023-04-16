package hotboys69.dat153.whosetweetisthatappthing.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import hotboys69.dat153.whosetweetisthatappthing.data.TweeterRepository;
import hotboys69.dat153.whosetweetisthatappthing.data.entities.Category;
import hotboys69.dat153.whosetweetisthatappthing.data.not_entities.TweeterCategories;

public class TweeterViewModel extends AndroidViewModel {

    private final TweeterRepository tweeterRepository;

    public TweeterViewModel(Application application)
    {
        super(application);
        tweeterRepository = new TweeterRepository(application);
    }

    public LiveData<List<TweeterCategories>> getCategoriesLiveData()
    {
        return tweeterRepository.getAllCategories();
        /*LiveData<List<Category>> categoriesLiveData = tweeterRepository.getAllCategories();

        return Transformations
                .map(categoriesLiveData, categories -> {
                    for (Category category : categories) {
                        category.tweeters = new ArrayList<>();
                        LiveData<List<Tweeter>> tweetersForCategoryLiveData = tweeterRepository
                                .getTweetersForCategory(category.categoryId);

                        //TODO make it work
                        tweetersForCategoryLiveData
                                .observeForever(tweeters -> category.tweeters = tweeters);
                    }

                    return categories;
                });*/
    }




    public void insert(Category category)
    {
        tweeterRepository.insertCategory(category);
    }

}
