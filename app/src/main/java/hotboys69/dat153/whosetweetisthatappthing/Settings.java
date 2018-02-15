package hotboys69.dat153.whosetweetisthatappthing;

/**
 * Created by Trygve on 14.02.2018.
 */

public class Settings {

    public static boolean soundEnabled = false;

    private static int highScore = 0;


    /**
     * will only update the high-score if it's higher than the current one
     * returns true if there is a new high-score
     */
    public static boolean postScore(int score){
        if(score>highScore){
            highScore=score;
            return true;
        }else{
            return false;
        }
    }

    public static int getHighScore(){
        return highScore;
    }

}
