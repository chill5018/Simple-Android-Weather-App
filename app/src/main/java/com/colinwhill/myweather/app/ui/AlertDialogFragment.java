package com.colinwhill.myweather.app.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import com.colinwhill.myweather.app.R;

/**
 * Created by colinhill on 11/23/15.
 */
public class AlertDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Context context = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
            .setTitle(R.string.error_title)
            .setMessage(R.string.error_message)
            .setPositiveButton(R.string.error_OK_button, null);

        AlertDialog dialog = builder.create();
        return dialog;
    }
}
