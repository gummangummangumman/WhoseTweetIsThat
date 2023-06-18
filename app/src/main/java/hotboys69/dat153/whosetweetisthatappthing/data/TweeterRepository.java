package hotboys69.dat153.whosetweetisthatappthing.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import hotboys69.dat153.whosetweetisthatappthing.data.entities.Category;
import hotboys69.dat153.whosetweetisthatappthing.data.entities.Tweeter;
import hotboys69.dat153.whosetweetisthatappthing.data.not_entities.TweeterCategory;

public class TweeterRepository {
    private final TweeterDao tweeterDao;
    private final LiveData<List<TweeterCategory>> categories;
    private final LiveData<List<TweeterCategory>> activeCategories;

    public TweeterRepository(Application application)
    {
        DataBase db = DataBase.getDatabase(application);
        tweeterDao = db.tweeterDao();
        categories = tweeterDao.getAll();
        activeCategories = tweeterDao.getActiveCategories();
    }

    public LiveData<List<TweeterCategory>> getActiveCategories()
    {
        return activeCategories;
    }

    public LiveData<List<TweeterCategory>> getAllCategories()
    {
        return categories;
    }

    public void insertCategory(Category category)
    {
        DataBase.databaseWriteExecutor.execute(() -> tweeterDao.insertCategory(category));
    }

    public void insertTweeter(Tweeter tweeter)
    {
        DataBase.databaseWriteExecutor.execute(() -> tweeterDao.insertTweeter(tweeter));
    }

    public void deleteCategory(Category category)
    {
        DataBase.databaseWriteExecutor.execute(() -> {
                    tweeterDao.deleteAllTweetersWithCategoryId(category.categoryId);
                    tweeterDao.deleteCategory(category);
                }
        );
    }

    public void deleteTweeter(Tweeter tweeter)
    {
        DataBase.databaseWriteExecutor.execute(() -> tweeterDao.deleteTweeter(tweeter));
    }

    public void updateCategory(Category category)
    {
        DataBase.databaseWriteExecutor.execute(() -> tweeterDao.updateCategory(category));
    }

}
