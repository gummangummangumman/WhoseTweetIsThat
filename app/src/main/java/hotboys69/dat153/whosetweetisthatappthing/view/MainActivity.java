package hotboys69.dat153.whosetweetisthatappthing.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

import hotboys69.dat153.whosetweetisthatappthing.R;
import hotboys69.dat153.whosetweetisthatappthing.connect.TweeterListFetcher;
import hotboys69.dat153.whosetweetisthatappthing.data.Settings;
import hotboys69.dat153.whosetweetisthatappthing.data.Tweeters;


public class MainActivity extends AppCompatActivity {

    Button playButton;
    Button settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (Tweeters.musicians == null) {
            Tweeters.musicians = new ArrayList<>
                    (Arrays.asList(getResources().getStringArray(R.array.music)));
            Tweeters.defaultValues = true;
        }
        if (Tweeters.nonmusicians == null) {
            Tweeters.nonmusicians = new ArrayList<>
                    (Arrays.asList(getResources().getStringArray(R.array.politics)));
            Tweeters.defaultValues = true;
        }

        if (Tweeters.defaultValues) {
            new TweeterListFetcher.FetchTweeters()
                    .execute(getResources().getString(R.string.APIurl));
        }


        if (!Settings.loaded) {
            Settings.loadSettings(getBaseContext());
        }

        playButton = findViewById(R.id.play_button);
        playButton.setOnClickListener(view -> startGame());


        settingsButton = findViewById(R.id.settings_button);
        settingsButton.setOnClickListener(view -> goToSettings());
    }


    public void startGame()
    {
        Intent gameIntent = new Intent(this, GameActivity.class);
        startActivity(gameIntent);
    }

    public void goToSettings()
    {
        Intent settingsIntent = new Intent(this, SettingsActivity.class);
        startActivity(settingsIntent);
    }
}
