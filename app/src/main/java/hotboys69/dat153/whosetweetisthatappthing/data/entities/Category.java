package hotboys69.dat153.whosetweetisthatappthing.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "CATEGORY")
public class Category {

    @PrimaryKey(autoGenerate = true)
    public int categoryId;

    public boolean active;

    public Category()
    {
        this.active = true;
    }
}
