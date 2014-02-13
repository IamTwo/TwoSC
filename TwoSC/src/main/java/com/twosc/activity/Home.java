package com.twosc.activity;

import android.os.Bundle;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.twosc.R;
import com.twosc.fragment.HomeFragment;
import com.twosc.fragment.MenuFragment;
import com.twosc.fragment.HomeFragment.OnSettingClickListener;

public class Home extends TwoFragmentActivity{
    private SlidingMenu menu;
    private HomeFragment mHomeFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_frame);
        initSlidingMenu();
        setListener();
    }

    private void initSlidingMenu() {
        mHomeFragment = HomeFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,
                mHomeFragment).commit();
        menu = new SlidingMenu(this);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.shadow);
        menu.setBehindOffsetRes(R.dimen.sliding_menu_offset);
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        menu.setMenu(R.layout.menu_frame);
        getSupportFragmentManager().beginTransaction().replace(R.id.menu_frame,
                MenuFragment.newInstance()).commit();
    }

    public void setListener() {
        mHomeFragment.setSettingClickListener(new OnSettingClickListener() {
            @Override
            public void showMenu() {
                menu.toggle();
            }
        });
    }
}
