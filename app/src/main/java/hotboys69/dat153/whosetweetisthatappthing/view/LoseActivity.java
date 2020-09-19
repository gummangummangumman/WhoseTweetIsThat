package hotboys69.dat153.whosetweetisthatappthing.view;

import android.content.Intent;
import android.media.MediaPlayer;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import hotboys69.dat153.whosetweetisthatappthing.R;
import hotboys69.dat153.whosetweetisthatappthing.data.Settings;

public class LoseActivity extends AppCompatActivity {

    int score;

    TextView scoreLoseText, gameOverText;

    Button playAgainButton, mainMenuButton;

    MediaPlayer highScoreSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lose);

        scoreLoseText = findViewById(R.id.scoreLoseText);

        score = (int)getIntent().getExtras().get("score");

        boolean newHighscore = Settings.postScore(score);


        if(newHighscore)
        {
            gameOverText = findViewById(R.id.gameOverText);
            gameOverText.setText(getString(R.string.new_highscore));
            scoreLoseText.setText(getString(R.string.highscore, Settings.getHighScore()));
            Settings.saveSettings(getBaseContext());
            highScoreSound = MediaPlayer.create(this, R.raw.fanfare);
            highScoreSound.start();
        } else {
            scoreLoseText.setText(getString(R.string.score_and_highscore, score, Settings.getHighScore()));
        }


        playAgainButton = findViewById(R.id.playAgainButton);
        mainMenuButton = findViewById(R.id.mainMenuButton);

        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame();
            }
        });

        mainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainMenu();
            }
        });
    }

    private void mainMenu() {
        Intent menuIntent = new Intent(this, MainActivity.class);
        menuIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        menuIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        this.finish();
        startActivity(menuIntent);
    }

    private void startGame() {
        Intent gameIntent = new Intent(this, GameActivity.class);
        this.finish();
        startActivity(gameIntent);
    }
}
