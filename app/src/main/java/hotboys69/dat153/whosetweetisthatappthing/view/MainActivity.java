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

    Button playButton, tweeterButton, settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupAppState();

        playButton = findViewById(R.id.play_button);
        playButton.setOnClickListener(view -> startGame());

        tweeterButton = findViewById(R.id.tweeter_button);
        tweeterButton.setOnClickListener(view -> goToTweeterScreen());

        settingsButton = findViewById(R.id.settings_button);
        settingsButton.setOnClickListener(view -> goToSettings());
    }


    public void startGame()
    {
        Intent gameIntent = new Intent(this, GameActivity.class);
        startActivity(gameIntent);
    }

    public void goToTweeterScreen()
    {
        Intent tweeterIntent = new Intent(this, TweeterActivity.class);
        startActivity(tweeterIntent);
    }

    public void goToSettings()
    {
        Intent settingsIntent = new Intent(this, SettingsActivity.class);
        startActivity(settingsIntent);
    }


    /**
     * Useful for first time opening the app
     */
    private void setupAppState()
    {
        if (Tweeters.musicians == null) {
            Tweeters.musicians = new ArrayList<>
                    (Arrays.asList(getResources().getStringArray(R.array.music)));
            Tweeters.defaultValues = true;
            Tweeters.repopulateDefaultLists();
        }

        if (Tweeters.non_musicians == null) {
            Tweeters.non_musicians = new ArrayList<>
                    (Arrays.asList(getResources().getStringArray(R.array.politics)));
            Tweeters.defaultValues = true;
            Tweeters.repopulateDefaultLists();
        }

        if (Tweeters.defaultValues) {
            new TweeterListFetcher.FetchTweeters()
                    .execute(getResources().getString(R.string.APIurl));
        }
        if (!Settings.loaded) {
            Settings.loadSettings(getBaseContext());
        }
    }
}
