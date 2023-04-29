package hotboys69.dat153.whosetweetisthatappthing.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ExpandableListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;

import hotboys69.dat153.whosetweetisthatappthing.R;
import hotboys69.dat153.whosetweetisthatappthing.data.Tweeters;
import hotboys69.dat153.whosetweetisthatappthing.data.entities.Category;
import hotboys69.dat153.whosetweetisthatappthing.data.not_entities.TweeterCategory;
import hotboys69.dat153.whosetweetisthatappthing.view.recyclerview.TweeterListAdapter;
import hotboys69.dat153.whosetweetisthatappthing.viewmodel.TweeterViewModel;

public class TweeterActivity extends AppCompatActivity {

    ExpandableListView expandableListView;

    Button addListButton;

    TweeterViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweeter);

        viewModel = new ViewModelProvider(this).get(TweeterViewModel.class);

        expandableListView = findViewById(R.id.recycler_view);
        TweeterListAdapter adapter = new TweeterListAdapter(Tweeters.getAsCategories());
        expandableListView.setAdapter(adapter);


        addListButton = findViewById(R.id.add_list_button);
        addListButton.setOnClickListener(view -> viewModel.insert(new Category()));


        viewModel.getCategoriesLiveData().observe(this, value -> {
            List<TweeterCategory> tweeters = new ArrayList<>(Tweeters.getAsCategories());
            tweeters.addAll(value);

            TweeterListAdapter a = new TweeterListAdapter(tweeters);
            expandableListView.setAdapter(a);
        });
    }
}