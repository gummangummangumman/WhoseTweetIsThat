package hotboys69.dat153.whosetweetisthatappthing;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.renderscript.ScriptGroup;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import android.net.http.*;
import android.widget.Button;

import com.squareup.picasso.Downloader;

import java.lang.ref.WeakReference;
import java.net.URL;


/**
 * Created by GuMMaN on 12.02.2018.
 */

public class ImageDownloader {


    final int IMAGE_BOUND_SIZE = 200;


    public static class DownloadPictureTask extends AsyncTask<String, Void, Drawable> {

        Button button;

        public DownloadPictureTask(Button button)
        {
            this.button = button;
        }

        @Override
        protected Drawable doInBackground(String...voids) {

            String url = voids[0];
            url = url.replaceFirst("(?i)^http://", "https://");

            try {
                InputStream is = (InputStream) new URL(url).getContent();
                Drawable d = Drawable.createFromStream(is, "lol");
                return d;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Drawable result) {
            if(result == null){
                return;
            }

            result.setBounds(0, 0, button.getHeight(), button.getHeight());
            button.setCompoundDrawables(result, null, null, null);
        }
    }
}
