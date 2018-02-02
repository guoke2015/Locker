package com.lwx.locker.custom.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.widget.TextView;

import com.lwx.locker.R;

/**
 * <pre>
 *     @author : liwx
 *     e-mail : xxx@xx
 *     time   : 2018/02/02
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class Mydialog extends AlertDialog {
    private TextView dialogMessage;

    public Mydialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_dialog);

        init();
    }

    private void init() {
        dialogMessage = findViewById(R.id.dialog_message);
    }

    public void setMessage(String message) {
        dialogMessage.setText(message);
    }

    public void setTouchOutside(boolean isCannot) {
        setCanceledOnTouchOutside(isCannot);
    }
}
