package com.bilalzaman.motivationalquotes.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bilalzaman.motivationalquotes.R;


public class CustomAlertDialog extends DialogFragment {

    private static final int BUTTON_COLOR = Color.parseColor("#FF0000");
    private static final int Negative_BUTTON_COLOR = Color.parseColor("#00aeef");
    private static final float BUTTON_TEXT_SIZE = 16f;
    private static final String ALERT_DIALOG_TITLE_DEFAULT_TEXT = "No Title";
    private static final String ALERT_DIALOG_MESSAGE_DEFAULT_TEXT = "No Meesage Found . . . \n\nPlease Set The Title & Message First";
    private TextView mAlertDialogTitle;
    private TextView mAlertDialogMessage;
    private String mTitle;
    private String mMessage;
    private String mPositiveButtonText;
    private DialogInterface.OnClickListener mPositiveButtonListner;
    private String mNegativeButtonText;
    private DialogInterface.OnClickListener mNegativeButtonListner;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public String getPositiveButtonText() {
        return mPositiveButtonText;
    }

    public void setPositiveButtonText(String positiveButtonText) {
        mPositiveButtonText = positiveButtonText;
    }

    public DialogInterface.OnClickListener getPositiveButtonListner() {
        return mPositiveButtonListner;
    }

    public void setPositiveButtonListner(DialogInterface.OnClickListener positiveButtonListner) {
        mPositiveButtonListner = positiveButtonListner;
    }

    public String getNegativeButtonText() {
        return mNegativeButtonText;
    }

    public void setNegativeButtonText(String negativeButtonText) {
        mNegativeButtonText = negativeButtonText;
    }

    public DialogInterface.OnClickListener getNegativeButtonListner() {
        return mNegativeButtonListner;
    }

    public void setNegativeButtonListner(DialogInterface.OnClickListener negativeButtonListner) {
        mNegativeButtonListner = negativeButtonListner;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_alert_dialog,
                null,
                false);
        builder.setView(view);
        mAlertDialogTitle = view.findViewById(R.id.alert_dialog_title);
        mAlertDialogMessage = view.findViewById(R.id.alert_dialog_message);

        if (getTitle() == null) {
            mAlertDialogTitle.setText(ALERT_DIALOG_TITLE_DEFAULT_TEXT);
        } else {
            mAlertDialogTitle.setText(getTitle());
        }

        if (getMessage() == null) {
            mAlertDialogMessage.setText(ALERT_DIALOG_MESSAGE_DEFAULT_TEXT);
        } else {
            mAlertDialogMessage.setText(getMessage());
        }

        if (getPositiveButtonText() != null) {
            builder.setPositiveButton(getPositiveButtonText(), getPositiveButtonListner());
        }

        if (getNegativeButtonText() != null) {
            builder.setNegativeButton(getNegativeButtonText(), getNegativeButtonListner());
        }

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        // Get Positive Button and Override Some Properties
        Button positiveButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        positiveButton.setTextColor(BUTTON_COLOR);
        positiveButton.setTextSize(BUTTON_TEXT_SIZE);

        // Get Negative Button and Override Some Properties
        Button negativeButton = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        negativeButton.setTextColor(Negative_BUTTON_COLOR);
        negativeButton.setTextSize(BUTTON_TEXT_SIZE);

        // Prevent Closing a Dialog Box From Back Button or On Touch Outside
        if (getPositiveButtonText() == null && getNegativeButtonText() == null) {
            alertDialog.setCanceledOnTouchOutside(true);
            setCancelable(true);
        } else if (getPositiveButtonText() != null && getNegativeButtonText() == null) {
            alertDialog.setCanceledOnTouchOutside(false);
            setCancelable(false);
        } else if (getNegativeButtonText() != null && getPositiveButtonText() == null) {
            alertDialog.setCanceledOnTouchOutside(false);
            setCancelable(false);
        } else {
            alertDialog.setCanceledOnTouchOutside(false);
            setCancelable(false);
        }

        return alertDialog;
    }
}