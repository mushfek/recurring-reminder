package com.mushfek.recurringreminder.util.builder;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import com.mushfek.recurringreminder.R;

/**
 * @author Mushfekur Rahman
 * @since 1.0
 */

public class DialogBuilder {
    public static Dialog buildOkDialog(Context context, String titleText, String messageText,
                                       boolean cancelable, DialogInterface.OnClickListener onOkButtonClickListener) {
        return new AlertDialog.Builder(context)
                .setTitle(titleText)
                .setMessage(messageText)
                .setPositiveButton(R.string.ok, onOkButtonClickListener)
                .setCancelable(cancelable)
                .create();
    }
}
