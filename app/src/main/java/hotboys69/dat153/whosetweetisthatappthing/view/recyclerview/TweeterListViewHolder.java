package hotboys69.dat153.whosetweetisthatappthing.view.recyclerview;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import hotboys69.dat153.whosetweetisthatappthing.R;

public class TweeterListViewHolder extends RecyclerView.ViewHolder {
    TextView title;

    public TweeterListViewHolder(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
    }
}
