package hotboys69.dat153.whosetweetisthatappthing.view.recyclerview;

import android.app.AlertDialog;
import android.content.Context;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import java.util.List;

import hotboys69.dat153.whosetweetisthatappthing.R;
import hotboys69.dat153.whosetweetisthatappthing.data.entities.Tweeter;
import hotboys69.dat153.whosetweetisthatappthing.data.not_entities.TweeterCategory;
import hotboys69.dat153.whosetweetisthatappthing.viewmodel.TweeterViewModel;

public class TweeterListAdapter extends BaseExpandableListAdapter {
    private final List<TweeterCategory> categories;

    TweeterViewModel viewModel;

    public TweeterListAdapter(List<TweeterCategory> dataList, TweeterViewModel viewModel)
    {
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
        return getGroup(groupPosition).tweeters.size() + 1;
    }

    @Override
    public TweeterCategory getGroup(int groupPosition)
    {
        return categories.get(groupPosition);
    }

    @Override
    public Tweeter getChild(int groupPosition, int childPosition)
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
        String data = getGroup(groupPosition).toString();
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

        if (isLastChild) {
            return groupPosition > 1
                    ? getAddTweeterView(parent, categories.get(groupPosition))
                    : new View(view.getContext());
        }
        if (childPosition % 2 == 0) {
            view.setBackgroundColor(ContextCompat
                    .getColor(parent.getContext(), R.color.twitterOfficial));
        }

        Tweeter tweeter = getChild(groupPosition, childPosition);
        TextView textView = view.findViewById(R.id.title);
        textView.setText(tweeter.name);

        Button deleteButton = view.findViewById(R.id.delete_button);
        if (groupPosition < 2) {
            deleteButton.setVisibility(View.GONE);
        } else {
            deleteButton.setOnClickListener(button -> {
                viewModel.deleteTweeter(tweeter);
                Toast.makeText(view.getContext(),
                                R.string.tweeters_tweeter_deleted, Toast.LENGTH_SHORT)
                        .show();
            });
        }

        return view;
    }

    private View getAddTweeterView(ViewGroup parent, TweeterCategory category)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_child_add_tweeter, parent, false);

        AlertDialog addTweeterDialog = addTweeterDialog(parent.getContext(), category);

        view.findViewById(R.id.add_button)
                .setOnClickListener(button -> addTweeterDialog.show());

        return view;
    }

    private AlertDialog addTweeterDialog(Context context, TweeterCategory category)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.tweeters_add_dialog_title);

        final EditText input = new EditText(context);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setHint(R.string.tweeters_add_dialog_hint);
        builder.setView(input);
        builder.setPositiveButton(R.string.ok, (dialog, which) -> {
            String text = input.getText()
                    .toString()
                    .replaceAll("@", "")
                    .trim();
            boolean deleted = viewModel.insertTweeter(category, text);
            if (!deleted) {
                Toast.makeText(context,
                                R.string.tweeters_tweeter_not_added, Toast.LENGTH_SHORT)
                        .show();
            }
        });

        return builder.create();
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition)
    {
        return false;
    }
}
