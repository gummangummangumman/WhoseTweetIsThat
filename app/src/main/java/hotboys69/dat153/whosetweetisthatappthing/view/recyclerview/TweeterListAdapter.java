package hotboys69.dat153.whosetweetisthatappthing.view.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hotboys69.dat153.whosetweetisthatappthing.R;

public class TweeterListAdapter extends RecyclerView.Adapter<TweeterListViewHolder> {
    private final List<List<String>> tweeters;

    public TweeterListAdapter(List<List<String>> dataList) {
        this.tweeters = dataList;
    }

    @NonNull
    @Override
    public TweeterListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new TweeterListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TweeterListViewHolder holder, int position) {
        String data = tweeters.get(position).toString();
        holder.title.setText(data);
    }

    @Override
    public int getItemCount() {
        return tweeters.size();
    }
}
