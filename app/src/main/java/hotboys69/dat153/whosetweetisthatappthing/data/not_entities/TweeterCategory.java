package hotboys69.dat153.whosetweetisthatappthing.data.not_entities;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;
import java.util.stream.Collectors;

import hotboys69.dat153.whosetweetisthatappthing.data.entities.Category;
import hotboys69.dat153.whosetweetisthatappthing.data.entities.Tweeter;

public class TweeterCategory {
    @Embedded
    public Category category;
    @Relation(
            parentColumn = "categoryId",
            entityColumn = "categoryId"
    )
    public List<Tweeter> tweeters;

    public boolean getEffectivelyActive()
    {
        return category.active && tweeters.size() >= 4;
    }


    @NonNull
    @Override
    public String toString()
    {
        return tweeters.stream()
                .map(tweeter -> tweeter.name)
                .collect(Collectors.toList())
                .toString();
    }
}
