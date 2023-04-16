package hotboys69.dat153.whosetweetisthatappthing.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import hotboys69.dat153.whosetweetisthatappthing.data.entities.Category;
import hotboys69.dat153.whosetweetisthatappthing.data.entities.Tweeter;
import hotboys69.dat153.whosetweetisthatappthing.data.not_entities.TweeterCategories;

public class TweeterRepository {
    private final TweeterDao tweeterDao;
    private final LiveData<List<TweeterCategories>> categories;

    public TweeterRepository(Application application) {
        DataBase db = DataBase.getDatabase(application);
        tweeterDao = db.tweeterDao();
        categories = tweeterDao.getAll();
    }

    public LiveData<List<TweeterCategories>> getAllCategories() {
        return categories;
    }

    public void insertCategory(Category category) {
        DataBase.databaseWriteExecutor.execute(() -> tweeterDao.insertCategory(category));
    }

    public void insertTweeter(Tweeter tweeter) {
        DataBase.databaseWriteExecutor.execute(() -> tweeterDao.insertTweeter(tweeter));
    }

}
