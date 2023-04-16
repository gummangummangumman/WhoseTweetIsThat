package hotboys69.dat153.whosetweetisthatappthing.data.not_entities;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

import hotboys69.dat153.whosetweetisthatappthing.data.entities.Category;
import hotboys69.dat153.whosetweetisthatappthing.data.entities.Tweeter;

public class TweeterCategories {
    @Embedded public Category category;
    @Relation(
            parentColumn = "categoryId",
            entityColumn = "categoryId"
    )
    public List<Tweeter> tweeters;
}
