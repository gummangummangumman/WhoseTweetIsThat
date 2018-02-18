package hotboys69.dat153.whosetweetisthatappthing;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoseActivity extends AppCompatActivity {

    int score;

    TextView scoreLoseText;

    Button playAgainButton, mainMenuButton;

    MediaPlayer highScoreSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lose);

        scoreLoseText = findViewById(R.id.scoreLoseText);

        score = (int)getIntent().getExtras().get("score");

        boolean newHighscore = Settings.postScore(score);


        if(newHighscore){
            scoreLoseText.setText("New highscore!\nHighscore: " + Settings.getHighScore());
            Settings.saveSettings(getBaseContext());
            highScoreSound = MediaPlayer.create(this, R.raw.fanfare);
            highScoreSound.start();
        }
        else{
            scoreLoseText.setText("You got a score of: " + score + "\nHighscore: " + Settings.getHighScore());
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
