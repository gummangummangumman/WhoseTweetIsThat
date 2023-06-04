package hotboys69.dat153.whosetweetisthatappthing.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import hotboys69.dat153.whosetweetisthatappthing.data.entities.Category;
import hotboys69.dat153.whosetweetisthatappthing.data.entities.Tweeter;
import hotboys69.dat153.whosetweetisthatappthing.data.not_entities.TweeterCategory;

@Dao
public interface TweeterDao {

    @Query("SELECT * FROM category")
    LiveData<List<TweeterCategory>> getAll();

    @Query("SELECT * FROM category c WHERE active is 1 AND (SELECT COUNT(*) FROM tweeter t where c. categoryId=t.categoryId) >= 4")
    LiveData<List<TweeterCategory>> getActiveCategories();

    @Insert
    void insertCategory(Category category);

    @Insert
    void insertTweeter(Tweeter tweeter);

    @Delete
    void deleteCategory(Category category);

    @Delete
    void deleteTweeter(Tweeter tweeter);

    @Update
    void updateCategory(Category category);
}
