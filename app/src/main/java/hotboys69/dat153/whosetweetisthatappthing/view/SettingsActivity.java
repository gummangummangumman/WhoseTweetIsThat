package hotboys69.dat153.whosetweetisthatappthing.view;

import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import hotboys69.dat153.whosetweetisthatappthing.R;
import hotboys69.dat153.whosetweetisthatappthing.data.Settings;

public class SettingsActivity extends AppCompatActivity {

    Button menuButton, resetScoreButton;
    Switch soundSwitch;
    TextView highscoreText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        highscoreText = findViewById(R.id.highscoreText);
        highscoreText.setText(getString(R.string.highscore, Settings.getHighScore()));

        soundSwitch = findViewById(R.id.soundSwitch);

        //show it as what it is in the settings
        soundSwitch.setChecked(Settings.soundEnabled);


        soundSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Settings.soundEnabled = b;
                Settings.saveSettings(getBaseContext());
            }
        });



        menuButton = findViewById(R.id.backToMenuButton);
        menuButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mainMenu();
            }
        });

        resetScoreButton = findViewById(R.id.resetHighScoreButton);
        resetScoreButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                requestResetHighScoreConfirmation();
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


    public void requestResetHighScoreConfirmation(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.reset_confirmation);
        builder.setTitle(R.string.reset_highscore);
        builder.setPositiveButton(R.string.reset_highscore, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                resetHighScore();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //
            }
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
