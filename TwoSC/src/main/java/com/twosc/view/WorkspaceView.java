package com.twosc.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by Jie Xiang on 1/15/14.
 */
public class WorkspaceView extends ViewGroup {
    private static final int SCROLLING_UP = 1;
    private static final int SCROLLING = 2;
    private static final int SNAP_VOLOCITY = 600;

    private VelocityTracker mVelocityTracker;
    private Scroller mScroller;
    private float mLastMotionX;
    private float mLastMotionY;
    private int mTouchMode;
    private int mCurScreen;

    public WorkspaceView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mScroller = new Scroller(context);
        mCurScreen = 0;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int childLeft = 0;
        int childCount = getChildCount();

        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);

            if (childView != null && childView.getVisibility() != View.GONE) {
                int childWidth = childView.getMeasuredWidth();
                childView.layout(childLeft, 0, childWidth, childView.getMeasuredHeight());
                childLeft += childWidth;
            }
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }

        if (mVelocityTracker != null)
            mVelocityTracker.addMovement(event);

        int action = event.getAction();
        float x = event.getX();
        float y = event.getY();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = (int) (mLastMotionX - x);
                int deltaY = (int) (mLastMotionY - y);
                mLastMotionX = x;
                mLastMotionY = y;

                if (Math.abs(deltaX) > 5 || Math.abs(deltaY) > 5) {
                    if (Math.abs(deltaX) > Math.abs(deltaY)) {
                        if (mTouchMode == SCROLLING_UP)
                            break;
                        scrollBy(deltaX, 0);
                        mTouchMode = SCROLLING;
                    } else {
                        if (mTouchMode == SCROLLING)
                            break;
                        mTouchMode = SCROLLING_UP;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                VelocityTracker velocityTracker = mVelocityTracker;
                velocityTracker.computeCurrentVelocity(1000);
                int velocityX = (int) velocityTracker.getXVelocity();

                if (mTouchMode == SCROLLING) {
                    if (velocityX > SNAP_VOLOCITY) {
                        snapToScreen(mCurScreen - 1);
                    } else if(velocityX < -SNAP_VOLOCITY) {
                        snapToScreen(mCurScreen + 1);
                    } else {
                        snapToDestination();
                    }
                }

                if (mVelocityTracker != null) {
                    mVelocityTracker.recycle();
                    mVelocityTracker = null;
                }
        }

        if (mTouchMode == SCROLLING)
            return true;
        return false;
    }

    /**
     * Snap to screen of id.
     *
     * @param whichScreen
     */
    public void snapToScreen(int whichScreen) {
        whichScreen = Math.max(0, Math.min(whichScreen, getChildCount() - 1));
        if(getScrollX() != whichScreen * getWidth()) {
            int delta = whichScreen * getWidth() - getScrollX();
            mScroller.startScroll(getScrollX(), 0, delta, 0, Math.abs(delta) * 2);
            mCurScreen = whichScreen;
            invalidate();
        }
    }

    /**
     * Check if snap to next screen or not.
     */
    public void snapToDestination() {
        int screenWidth = getWidth();
        int desScreen = (getScrollX() + screenWidth / 2) / screenWidth;
        snapToScreen(desScreen);
    }
}
