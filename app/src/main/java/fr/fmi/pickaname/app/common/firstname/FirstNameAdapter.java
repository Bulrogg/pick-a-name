package fr.fmi.pickaname.app.common.firstname;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import fr.fmi.pickaname.R;

public class FirstNameAdapter extends RecyclerView.Adapter<FirstNameViewHolder> {

    private static final int LAYOUT = R.layout.first_name_item;

    private final List<FirstNameViewModel> items;

    public FirstNameAdapter() {
        this.items = new ArrayList<>();
    }

    @Override
    public FirstNameViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(LAYOUT, parent, false);
        return FirstNameViewHolder.newInstance(view);
    }

    @Override
    public void onBindViewHolder(final FirstNameViewHolder holder, final int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addFirstNames(final List<FirstNameViewModel> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }
}
