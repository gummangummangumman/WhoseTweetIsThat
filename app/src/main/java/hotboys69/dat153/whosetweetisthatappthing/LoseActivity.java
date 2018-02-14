package hotboys69.dat153.whosetweetisthatappthing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoseActivity extends AppCompatActivity {

    int score;

    TextView scoreLoseText, gameOverText;

    Button playAgainButton, mainMenuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lose);

        scoreLoseText = findViewById(R.id.scoreLoseText);

        score = 0;

        scoreLoseText.setText("You got a score of: " + score);


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
        startActivity(menuIntent);
    }

    private void startGame() {
        Intent gameIntent = new Intent(this, GameActivity.class);
        startActivity(gameIntent);
    }
}
