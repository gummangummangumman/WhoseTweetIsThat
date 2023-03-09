package hotboys69.dat153.whosetweetisthatappthing.connect;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.Button;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


public class ImageDownloader {

    public static class DownloadPictureTask extends AsyncTask<String, Void, Drawable> {

        Button button;

        public DownloadPictureTask(Button button)
        {
            this.button = button;
        }

        @Override
        protected Drawable doInBackground(String... voids)
        {
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
        protected void onPostExecute(Drawable result)
        {
            if (result == null) {
                return;
            }

            result.setBounds(0, 0, button.getHeight(), button.getHeight());
            button.setCompoundDrawables(result, null, null, null);
        }
    }
}
