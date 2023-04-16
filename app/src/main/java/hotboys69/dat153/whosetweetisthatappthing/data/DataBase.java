package hotboys69.dat153.whosetweetisthatappthing.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import hotboys69.dat153.whosetweetisthatappthing.data.entities.Category;
import hotboys69.dat153.whosetweetisthatappthing.data.entities.Tweeter;

@Database(entities = {Tweeter.class, Category.class}, version = 1)
public abstract class DataBase extends RoomDatabase {

    public abstract TweeterDao tweeterDao();

    private static volatile DataBase INSTANCE;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(4);

    public static DataBase getDatabase(final Context context)
    {
        if (INSTANCE == null) {
            synchronized (DataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    DataBase.class, "database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
