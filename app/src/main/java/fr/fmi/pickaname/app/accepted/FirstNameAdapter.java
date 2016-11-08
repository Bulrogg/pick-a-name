package fr.fmi.pickaname.app.accepted;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import fr.fmi.pickaname.databinding.FirstNameItemBinding;

class FirstNameAdapter extends RecyclerView.Adapter<FirstNameAdapter.FirstNameViewHolder> {

    private final List<FirstNameViewModel> items;

    FirstNameAdapter() {
        this.items = new ArrayList<>();
    }

    void addFirstNames(final List<FirstNameViewModel> items) {
        this.items.clear();
        this.items.addAll(items);
    }

    @Override
    public FirstNameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final FirstNameItemBinding binding = FirstNameItemBinding.inflate(inflater, parent, false);
        return new FirstNameViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(FirstNameViewHolder holder, int position) {
        holder.binding.setItem(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class FirstNameViewHolder extends RecyclerView.ViewHolder {

        FirstNameItemBinding binding;

        FirstNameViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
