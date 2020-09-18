package hotboys69.dat153.whosetweetisthatappthing.connect;


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

import hotboys69.dat153.whosetweetisthatappthing.util.TweetFilter;
import hotboys69.dat153.whosetweetisthatappthing.view.GameActivity;
import retrofit2.Call;

public class TwitterConnect {

    /**
     * @param username the @ of the user
     * @param view GameActivity
     */
    public static void setUserInformation(String username, GameActivity view)
    {
        final WeakReference<GameActivity> callback = new WeakReference<GameActivity>(view);
        TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
        StatusesService statusesService = twitterApiClient.getStatusesService();
        Call<List<Tweet>> call = statusesService.userTimeline(null, username, null, null, null, null, true, null, false);
        call.enqueue(new Callback<List<Tweet>>() {
            @Override
            public void success(Result<List<Tweet>> result) {

                if(result.data.get(0).user.screenName.equals(callback.get().correctUserName)){
                    int number = (int) Math.floor(Math.random() * result.data.size());
                    String randomTweet = result.data.get(number).text;

                    final int FIRST_NUMBER = number;

                    //we should keep looking as long as the tweet isn't valid
                    boolean keepLooking = !TweetFilter.isValid(randomTweet);

                    while(keepLooking){
                        if(number==19){
                            number = 0;
                        }else{
                            number++;
                        }
                        randomTweet = result.data.get(number).text;
                        keepLooking = !TweetFilter.isValid(randomTweet);

                        if(keepLooking){
                            Log.w("FILTERED_TWEET", randomTweet);
                        }



                        //if it has looped all the way around, just give up
                        if (number == FIRST_NUMBER)
                        {
                            Log.w("INVALID_TWEETS", "all the tweets from " + result.data.get(0).user.screenName + " were invalid. Giving up.");
                            keepLooking = false;
                        }
                    }

                    callback.get().setTweet(randomTweet);
                }

                callback.get().setUserInformation(result.data.get(0).user.name, result.data.get(0).user.profileImageUrl, result.data.get(0).user.screenName);
            }

            @Override
            public void failure(TwitterException exception) {
                callback.get().setFailed();
            }
        });
    }

}
