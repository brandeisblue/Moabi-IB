package com.ivorybridge.moabi.ui.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.view.MotionEvent;
import android.view.View;

import com.ivorybridge.moabi.R;

public class TouchView extends View {
    Bitmap bgr;
    Bitmap overlayDefault;
    Bitmap overlay;
    Paint pTouch;
    int X = -100;
    int Y = -100;
    Canvas c2;

    public TouchView(Context context) {
        super(context);

        bgr = BitmapFactory.decodeResource(getResources(),R.drawable.ic_img_human_body_man);
        overlayDefault = BitmapFactory.decodeResource(getResources(), R.drawable.ic_img_human_body_man_colored_primary);
        overlay = BitmapFactory.decodeResource(getResources(),R .drawable.ic_img_human_body_man_colored_primary).copy(Bitmap.Config.ARGB_8888, true);
        c2 = new Canvas(overlay);

        pTouch = new Paint(Paint.ANTI_ALIAS_FLAG);
        pTouch.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
        pTouch.setColor(Color.TRANSPARENT);
        pTouch.setMaskFilter(new BlurMaskFilter(15, BlurMaskFilter.Blur.NORMAL));


    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {

            case MotionEvent.ACTION_DOWN: {

                X = (int) ev.getX();
                Y = (int) ev.getY();
                invalidate();

                break;
            }

            case MotionEvent.ACTION_MOVE: {

                X = (int) ev.getX();
                Y = (int) ev.getY();
                invalidate();
                break;

            }

            case MotionEvent.ACTION_UP:

                break;

        }
        return true;
    }


    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);

        //draw background
        canvas.drawBitmap(bgr, 0, 0, null);
        //copy the default overlay into temporary overlay and punch a hole in it
        c2.drawBitmap(overlayDefault, 0, 0, null); //exclude this line to show all as you draw
        c2.drawCircle(X, Y, 80, pTouch);
        //draw the overlay over the background
        canvas.drawBitmap(overlay, 0, 0, null);
    }
}
