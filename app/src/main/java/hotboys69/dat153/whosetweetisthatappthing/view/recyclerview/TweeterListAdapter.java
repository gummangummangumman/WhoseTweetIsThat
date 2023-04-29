package hotboys69.dat153.whosetweetisthatappthing.view.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.List;

import hotboys69.dat153.whosetweetisthatappthing.R;
import hotboys69.dat153.whosetweetisthatappthing.data.not_entities.TweeterCategory;

public class TweeterListAdapter extends BaseExpandableListAdapter {
    private final List<TweeterCategory> categories;

    public TweeterListAdapter(List<TweeterCategory> dataList) {
        this.categories = dataList;
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
