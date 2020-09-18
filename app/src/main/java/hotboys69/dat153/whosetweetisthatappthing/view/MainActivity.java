package hotboys69.dat153.whosetweetisthatappthing.view;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;

import hotboys69.dat153.whosetweetisthatappthing.data.Tweeters;
import hotboys69.dat153.whosetweetisthatappthing.R;
import hotboys69.dat153.whosetweetisthatappthing.data.Settings;
import hotboys69.dat153.whosetweetisthatappthing.connect.TweeterListFetcher;


public class MainActivity extends AppCompatActivity {

    Button playButton;
    Button settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(Tweeters.musicians==null){
            Tweeters.musicians = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.music)));
            Tweeters.defaultValues = true;
        }
        if(Tweeters.nonmusicians ==null){
            Tweeters.nonmusicians = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.politics)));
            Tweeters.defaultValues = true;
        }

        if(Tweeters.defaultValues){
            new TweeterListFetcher.FetchTweeters().execute(getResources().getString(R.string.APIurl));
        }


        if(!Settings.loaded){
            Settings.loadSettings(getBaseContext());
        }

        playButton = findViewById(R.id.playButton);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame();
            }
        });


        settingsButton = findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSettings();
            }
        });
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
