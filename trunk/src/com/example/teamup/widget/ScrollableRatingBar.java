package com.example.teamup.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RatingBar;

public class ScrollableRatingBar extends RatingBar {

    public ScrollableRatingBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public ScrollableRatingBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollableRatingBar(Context context) {
        super(context);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        final int action = ev.getAction();
        switch (action & MotionEvent.ACTION_MASK) {
        case MotionEvent.ACTION_DOWN: {
            return true;

        }
        case MotionEvent.ACTION_MOVE: {
            return true;
        }
        }
        return super.dispatchTouchEvent(ev);

    }

}