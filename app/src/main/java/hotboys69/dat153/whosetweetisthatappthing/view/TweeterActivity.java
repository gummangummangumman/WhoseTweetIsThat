package hotboys69.dat153.whosetweetisthatappthing.view;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import hotboys69.dat153.whosetweetisthatappthing.R;
import hotboys69.dat153.whosetweetisthatappthing.data.Tweeters;
import hotboys69.dat153.whosetweetisthatappthing.view.recyclerview.TweeterListAdapter;

public class TweeterActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    Button addListButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweeter);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        TweeterListAdapter adapter = new TweeterListAdapter(Tweeters.tweeters);
        recyclerView.setAdapter(adapter);


        addListButton = findViewById(R.id.add_list_button);
        addListButton.setOnClickListener(view -> System.out.println("no"));
    }
}