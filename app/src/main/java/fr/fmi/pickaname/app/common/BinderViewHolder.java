package fr.fmi.pickaname.app.common;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class BinderViewHolder<T> extends RecyclerView.ViewHolder {

    public BinderViewHolder(final View itemView) {
        super(itemView);
    }

    protected abstract void bind(final T object);
}
