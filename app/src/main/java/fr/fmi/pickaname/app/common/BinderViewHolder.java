package fr.fmi.pickaname.app.common;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class BinderViewHolder<T> extends RecyclerView.ViewHolder {

    public BinderViewHolder(final View itemView) {
        super(itemView);
    }

    // TODO remove
    @SuppressWarnings("unchecked")
    public final void bindObject(final Object object) {
        if (getClassType().isInstance(object)) {
            bind((T) object);
        }
    }

    protected abstract Class<T> getClassType();

    protected abstract void bind(final T object);
}
