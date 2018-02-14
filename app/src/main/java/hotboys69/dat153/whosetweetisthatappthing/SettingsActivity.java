package hotboys69.dat153.whosetweetisthatappthing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.util.Set;

public class SettingsActivity extends AppCompatActivity {

    Button menuButton;
    Switch soundSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);



        soundSwitch = findViewById(R.id.soundSwitch);

        //show it as what it is in the settings
        soundSwitch.setChecked(Settings.soundEnabled);


        soundSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Settings.soundEnabled = b;
            }
        });



        menuButton = findViewById(R.id.backToMenuButton);
        menuButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mainMenu();
            }
        });
    }


    private void mainMenu() {
        Intent menuIntent = new Intent(this, MainActivity.class);
        startActivity(menuIntent);
    }
}
