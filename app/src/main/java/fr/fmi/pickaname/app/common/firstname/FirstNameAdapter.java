package fr.fmi.pickaname.app.common.firstname;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import fr.fmi.pickaname.databinding.FirstNameItemBinding;

public class FirstNameAdapter extends RecyclerView.Adapter<FirstNameViewHolder> {

    private final List<FirstNameViewModel> items;

    public FirstNameAdapter() {
        this.items = new ArrayList<>();
    }

    public void addFirstNames(final List<FirstNameViewModel> items) {
        this.items.clear();
        this.items.addAll(items);
    }

    @Override
    public FirstNameViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final FirstNameItemBinding binding = FirstNameItemBinding.inflate(inflater, parent, false);
        return new FirstNameViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(final FirstNameViewHolder holder, final int position) {
        holder.binding.setItem(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
