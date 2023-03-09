package hotboys69.dat153.whosetweetisthatappthing.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Settings {

    public static boolean soundEnabled = false;

    private static int highScore = 0;

    public static boolean loaded = false;


    public static void loadSettings(Context context)
    {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);

        soundEnabled = pref.getBoolean("soundenabled", true);
        highScore = pref.getInt("highscore", 0);

        loaded = true;
    }

    public static void saveSettings(Context context)
    {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefEditor = pref.edit();

        prefEditor.putInt("highscore", highScore);
        prefEditor.putBoolean("soundenabled", soundEnabled);
        prefEditor.apply();
    }


    /**
     * will only update the high-score if it's higher than the current one
     * returns true if there is a new high-score
     */
    public static boolean postScore(int score)
    {
        if (score > highScore) {
            highScore = score;
            return true;
        } else {
            return false;
        }
    }

    /**
     * resets the score
     */
    public static void resetHighScore()
    {
        highScore = 0;
    }

    public static int getHighScore()
    {
        return highScore;
    }

}
