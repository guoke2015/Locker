package com.lwx.locker.custom.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
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

public class Mydialog extends ProgressDialog {
    private TextView dialogMessage;
    private ImageView loadingImg;
    private AnimationDrawable animation;

    public Mydialog(Context context) {
        super(context,R.style.dialogTransparent);
        setCanceledOnTouchOutside(false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_dialog);

        init();
    }

    private void init() {
        loadingImg = findViewById(R.id.dialog_img);
        dialogMessage = findViewById(R.id.dialog_message);

        loadingImg.setBackgroundResource(R.drawable.frame_dialog);
        animation = (AnimationDrawable) loadingImg.getBackground();
        loadingImg.post(new Runnable() {
            @Override
            public void run() {
                animation.start();
            }
        });
    }

    @Override
    public void dismiss() {
        if (animation.isRunning()) {
            animation.stop();
        }
        super.dismiss();
    }

    public void setMessage(String message) {
        dialogMessage.setText(message);
    }

    //
    public void setTouchOutside(boolean isCannot) {
        setCanceledOnTouchOutside(isCannot);
    }
}
