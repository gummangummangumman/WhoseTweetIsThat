package hotboys69.dat153.whosetweetisthatappthing.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import hotboys69.dat153.whosetweetisthatappthing.data.entities.Category;
import hotboys69.dat153.whosetweetisthatappthing.data.entities.Tweeter;
import hotboys69.dat153.whosetweetisthatappthing.data.not_entities.TweeterCategories;

@Dao
public interface TweeterDao {

    @Query("SELECT * FROM category")
    LiveData<List<TweeterCategories>> getAll();

    @Insert
    void insertCategory(Category category);

    @Insert
    void insertTweeter(Tweeter tweeter);
}
