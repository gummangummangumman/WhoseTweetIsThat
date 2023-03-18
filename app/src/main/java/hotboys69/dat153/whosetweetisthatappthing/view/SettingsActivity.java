package hotboys69.dat153.whosetweetisthatappthing.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import hotboys69.dat153.whosetweetisthatappthing.R;
import hotboys69.dat153.whosetweetisthatappthing.data.Settings;

public class SettingsActivity extends AppCompatActivity {

    Button menuButton, resetScoreButton;
    SwitchCompat soundSwitch;
    TextView highscoreText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        highscoreText = findViewById(R.id.highscore_text);
        highscoreText.setText(getString(R.string.highscore, Settings.getHighScore()));

        soundSwitch = findViewById(R.id.sound_switch);

        //show it as what it is in the settings
        soundSwitch.setChecked(Settings.soundEnabled);

        soundSwitch.setOnCheckedChangeListener((compoundButton, b) -> {
            Settings.soundEnabled = b;
            Settings.saveSettings(getBaseContext());
        });


        menuButton = findViewById(R.id.back_to_menu_button);
        menuButton.setOnClickListener(view -> mainMenu());

        resetScoreButton = findViewById(R.id.reset_high_score_button);
        resetScoreButton.setOnClickListener(view -> requestResetHighScoreConfirmation());
    }


    private void mainMenu()
    {
        Intent menuIntent = new Intent(this, MainActivity.class);
        menuIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        menuIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        this.finish();
        startActivity(menuIntent);
    }


    public void requestResetHighScoreConfirmation()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.reset_confirmation);
        builder.setTitle(R.string.reset_highscore);
        builder.setPositiveButton(R.string.reset_highscore, (dialogInterface, i) -> resetHighScore());
        builder.setNegativeButton(R.string.cancel, (dialogInterface, i) -> {
            //
        });

        builder.create().show();
    }


    private void resetHighScore()
    {
        Settings.resetHighScore();
        Settings.saveSettings(getBaseContext());

        highscoreText.setText(getString(R.string.highscore, Settings.getHighScore()));

        Toast.makeText(this, getString(R.string.highscore_has_been_reset), Toast.LENGTH_SHORT).show();
    }
}
