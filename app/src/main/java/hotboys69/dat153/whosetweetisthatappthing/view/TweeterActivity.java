package hotboys69.dat153.whosetweetisthatappthing.view;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hotboys69.dat153.whosetweetisthatappthing.R;
import hotboys69.dat153.whosetweetisthatappthing.data.Tweeters;
import hotboys69.dat153.whosetweetisthatappthing.data.entities.Category;
import hotboys69.dat153.whosetweetisthatappthing.view.recyclerview.TweeterListAdapter;
import hotboys69.dat153.whosetweetisthatappthing.viewmodel.TweeterViewModel;

public class TweeterActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    Button addListButton;

    TweeterViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweeter);

        viewModel = new ViewModelProvider(this).get(TweeterViewModel.class);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        TweeterListAdapter adapter = new TweeterListAdapter(Tweeters.tweeters);
        recyclerView.setAdapter(adapter);


        addListButton = findViewById(R.id.add_list_button);
        addListButton.setOnClickListener(view -> viewModel.insert(new Category()));


        viewModel.getCategoriesLiveData().observe(this, value -> {
            List<List<String>> tweeters = new ArrayList<>(Tweeters.tweeters);

            List<List<String>> dataBaseTweeters = value.stream()
                    .map(category -> category.tweeters.stream()
                            .map(tweeter -> tweeter.name)
                            .collect(Collectors.toList()))
                    .collect(Collectors.toList());

            tweeters.addAll(dataBaseTweeters);

            TweeterListAdapter a = new TweeterListAdapter(tweeters);
            recyclerView.setAdapter(a);
        });
    }
}