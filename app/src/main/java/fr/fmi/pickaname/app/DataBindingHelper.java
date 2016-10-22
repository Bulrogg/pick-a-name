package fr.fmi.pickaname.app;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.support.annotation.StringRes;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ViewFlipper;

import static android.content.Context.INPUT_METHOD_SERVICE;

@SuppressWarnings("unused")
public class DataBindingHelper {
    private DataBindingHelper() {

    }

    @BindingAdapter("bind:displayedChild")
    public static void setDisplayedChild(final ViewFlipper viewFlipper, final int whichChild) {
        if (viewFlipper.getDisplayedChild() != whichChild) {
            viewFlipper.setDisplayedChild(whichChild);
        }
    }

    @BindingAdapter("bind:shouldHideKeyboard")
    public static void shouldHideKeyboard(final View view, final boolean shouldHideKeyboard) {
        if (shouldHideKeyboard) {
            InputMethodManager imm = (InputMethodManager) view.getContext()
                                                              .getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @BindingAdapter("bind:toast")
    public static void toast(final View view, @StringRes final int resId) {
        if (resId != 0) {
            Toast.makeText(view.getContext(), resId, Toast.LENGTH_SHORT).show();
        }
    }

    @BindingAdapter("bind:toast")
    public static void toast(final View view, final String message) {
        if (message != null) {
            Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
        }
    }

    @BindingAdapter(value = {
            "bind:selectedValue",
            "bind:selectedValueAttrChanged"
    }, requireAll = false)
    public static void bindSpinnerData(
            final Spinner spinner,
            final String newSelectedValue,
            final InverseBindingListener newTextAttrChanged
    ) {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                newTextAttrChanged.onChange();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        if (newSelectedValue != null) {
            int pos = ((ArrayAdapter<String>) spinner.getAdapter()).getPosition(newSelectedValue);
            spinner.setSelection(pos, true);
        }
    }

    @InverseBindingAdapter(attribute = "bind:selectedValue", event = "bind:selectedValueAttrChanged")
    public static String captureSelectedValue(final Spinner spinner) {
        return (String) spinner.getSelectedItem();
    }
}
