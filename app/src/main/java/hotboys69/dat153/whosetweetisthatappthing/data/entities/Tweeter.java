package hotboys69.dat153.whosetweetisthatappthing.data.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "TWEETER",
        foreignKeys = @ForeignKey
                (entity = Category.class,
                        parentColumns = "categoryId",
                        childColumns = "categoryId"))
public class Tweeter {

    @PrimaryKey(autoGenerate = true)
    public int tweeterId;
    @NonNull
    public final String name;
    public final int categoryId;

    public Tweeter(@NonNull Integer categoryId, @NonNull String name)
    {
        this.name = name;
        this.categoryId = categoryId;
    }
}
