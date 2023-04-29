package hotboys69.dat153.whosetweetisthatappthing.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import hotboys69.dat153.whosetweetisthatappthing.data.entities.Category;
import hotboys69.dat153.whosetweetisthatappthing.data.entities.Tweeter;
import hotboys69.dat153.whosetweetisthatappthing.data.not_entities.TweeterCategory;

@Dao
public interface TweeterDao {

    @Query("SELECT * FROM category")
    LiveData<List<TweeterCategory>> getAll();

    @Insert
    void insertCategory(Category category);

    @Insert
    void insertTweeter(Tweeter tweeter);

    @Delete
    void deleteCategory(Category category);
}
