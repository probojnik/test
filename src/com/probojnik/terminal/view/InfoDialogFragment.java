package com.probojnik.terminal.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import com.probojnik.terminal.R;

/**
 * @author Stanislav Shamji
 */

public class InfoDialogFragment extends DialogFragment implements OnClickListener {

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity()).setTitle("Пушкин. Стихи").setPositiveButton(R.string.infodialogfragment_exit, this)
                .setMessage(R.string.infodialogfragment_text).setCancelable(false);
        return adb.create();
    }


    public void onClick(DialogInterface dialog, int which) {
        int i = 0;
        switch (which) {
            case Dialog.BUTTON_POSITIVE:
                i = R.string.infodialogfragment_exit;
                break;
        }
        if (i > 0)
            System.out.println("Dialog 2: " + getResources().getString(i));
    }


    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        System.out.println("Dialog 2: onDismiss");
    }

    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        System.out.println("Dialog 2: onCancel");
    }
}