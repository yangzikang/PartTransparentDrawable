package com.example.yangzikang.demo;

import android.app.Activity;
import android.graphics.Path;
import android.graphics.Rect;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

public class MyPopupWindow extends PopupWindow {

    ImageView imageView;
    HoleDrawable backgroud;
    RelativeLayout layout;

    public MyPopupWindow(Activity activity, int marginLeftPx) {

        View contentView = LayoutInflater.from(activity).inflate(R.layout.popup_view, null);
        contentView.findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        imageView = contentView.findViewById(R.id.crop_image_cover_view_hole);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
        layoutParams.setMargins(marginLeftPx, 0, 0, layoutParams.bottomMargin);


        ImageView ivTrue = contentView.findViewById(R.id.true_image);
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) ivTrue.getLayoutParams();
        layoutParams2.setMargins(marginLeftPx, 0, 0, layoutParams.bottomMargin);

        layout = contentView.findViewById(R.id.outer_layout);
        backgroud =new HoleDrawable(layout.getBackground());
        layout.setBackground(backgroud);
        layout.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                Path path = new Path();
                path.addOval(imageView.getLeft(), imageView.getTop(), imageView.getRight(), imageView.getBottom(), Path.Direction.CW);
                backgroud.setSrcPath(path);
            }
        });


        setContentView(contentView);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        View rootView = activity.getWindow().getDecorView();
        //setAnimationStyle(R.style.AnimBottom);
        showAtLocation(rootView, Gravity.BOTTOM, 0, 0);
    }

}

