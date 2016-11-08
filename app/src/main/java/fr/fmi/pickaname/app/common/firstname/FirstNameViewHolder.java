package fr.fmi.pickaname.app.common.firstname;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import fr.fmi.pickaname.databinding.FirstNameItemBinding;

class FirstNameViewHolder extends RecyclerView.ViewHolder {

    public FirstNameItemBinding binding;

    public FirstNameViewHolder(final View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }
}
