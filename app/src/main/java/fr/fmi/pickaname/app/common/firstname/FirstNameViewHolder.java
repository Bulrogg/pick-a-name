package fr.fmi.pickaname.app.common.firstname;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.fmi.pickaname.R;
import fr.fmi.pickaname.app.common.BinderViewHolder;

public class FirstNameViewHolder extends BinderViewHolder<FirstNameViewModel> {

    @BindView(R.id.first_name_view) TextView firstNameView;
    @BindView(R.id.last_name_view) TextView lastNameView;

    private FirstNameViewHolder(final View itemView) {
        super(itemView);
    }

    public static FirstNameViewHolder newInstance(final View itemView) {
        final FirstNameViewHolder viewHolder = new FirstNameViewHolder(itemView);
        ButterKnife.bind(viewHolder, itemView);
        return viewHolder;
    }

    @Override
    protected Class<FirstNameViewModel> getClassType() {
        return FirstNameViewModel.class;
    }

    @Override
    protected void bind(final FirstNameViewModel firstNameViewModel) {
        firstNameView.setText(firstNameViewModel.firstName);
        lastNameView.setText(firstNameViewModel.lastName);
    }
}
