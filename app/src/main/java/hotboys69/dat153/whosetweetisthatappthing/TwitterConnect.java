package hotboys69.dat153.whosetweetisthatappthing;


import android.util.Log;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.StatusesService;

import java.lang.ref.WeakReference;
import java.util.List;

import retrofit2.Call;

/**
 * Created by GuMMaN on 09.02.2018.
 */

public class TwitterConnect {

    /**
     *
     * @param username the @ of the user
     * @param view GameActivity
     */
    public static void setUserInformation(String username, GameActivity view) {
        final WeakReference<GameActivity> callback = new WeakReference<GameActivity>(view);
        TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
        StatusesService statusesService = twitterApiClient.getStatusesService();
        Call<List<Tweet>> call = statusesService.userTimeline(null, username, null, null, null, null, null, null, null);
        call.enqueue(new Callback<List<Tweet>>() {
            @Override
            public void success(Result<List<Tweet>> result) {

                if(result.data.get(0).user.screenName.equals(callback.get().correctUserName)){
                    String randomTweet = result.data.get((int) Math.floor(Math.random() * result.data.size())).text;
                    callback.get().setTweet(randomTweet);
                }

                callback.get().setUserInformation(result.data.get(0).user.name, result.data.get(0).user.profileImageUrl, result.data.get(0).user.screenName);
            }

            @Override
            public void failure(TwitterException exception) {
                Log.w("fail", "it failz");
                callback.get().setTweet("We could not load any tweets. There might have been made too many calls to twitter and you might have to wait until you can " +
                        "fetch any more. \nThis can take up to 15 minutes.");
            }
        });
    }

}
