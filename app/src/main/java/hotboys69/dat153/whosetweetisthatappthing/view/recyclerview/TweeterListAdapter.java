package hotboys69.dat153.whosetweetisthatappthing.view.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import java.util.List;

import hotboys69.dat153.whosetweetisthatappthing.R;
import hotboys69.dat153.whosetweetisthatappthing.data.not_entities.TweeterCategory;
import hotboys69.dat153.whosetweetisthatappthing.viewmodel.TweeterViewModel;

public class TweeterListAdapter extends BaseExpandableListAdapter {
    private final List<TweeterCategory> categories;

    TweeterViewModel viewModel;

    public TweeterListAdapter(List<TweeterCategory> dataList, TweeterViewModel viewModel) {
        this.categories = dataList;
        this.viewModel = viewModel;
    }

    @Override
    public int getGroupCount()
    {
        return categories.size();
    }

    @Override
    public int getChildrenCount(int groupPosition)
    {
        return categories.get(groupPosition).tweeters.size();
    }

    @Override
    public Object getGroup(int groupPosition)
    {
        return categories.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition)
    {
        return categories.get(groupPosition).tweeters.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition)
    {
        return categories.get(0).category.categoryId;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition)
    {
        return categories.get(groupPosition).tweeters.get(childPosition).hashCode();
    }

    @Override
    public boolean hasStableIds()
    {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                             ViewGroup parent)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_group, parent, false);
        String data = categories.get(groupPosition).toString();
        TextView textView = view.findViewById(R.id.title);
        textView.setText(data);
        if (isExpanded && groupPosition > 1) {
            Button deleteButton = view.findViewById(R.id.delete_button);
            deleteButton.setVisibility(View.VISIBLE);
            deleteButton.setOnClickListener(button -> {
                        boolean deleted = viewModel.deleteCategory(categories.get(groupPosition));
                        if (deleted) {
                            Toast.makeText(view.getContext(),
                                    R.string.tweeters_category_deleted, Toast.LENGTH_SHORT)
                                    .show();
                        } else {
                            Toast.makeText(view.getContext(),
                                    R.string.tweeters_category_not_deleted, Toast.LENGTH_SHORT)
                                    .show();
                        }
            });
        }
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_child, parent, false);
        if (childPosition % 2 == 0) {
            view.setBackgroundColor(ContextCompat
                    .getColor(parent.getContext(), R.color.twitterOfficial));
        }
        String data = categories.get(groupPosition).tweeters.get(childPosition).name;
        TextView textView = view.findViewById(R.id.title);
        textView.setText(data);
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition)
    {
        return false;
    }
}
