package com.xushuangshuang.graduation.cherry.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.CustomViewAbove;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.xushuangshuang.graduation.cherry.fragment.GameFragment;
import com.xushuangshuang.graduation.cherry.fragment.MenuLeftManageFragment;
import com.xushuangshuang.graduation.cherry.fragment.MenuRightPersonalFragment;
import com.xushuangshuang.graduation.cherry.fragment.MyProductFragment;
import com.xushuangshuang.graduation.cherry.fragment.NewFragment;
import com.xushuangshuang.graduation.cherry.fragment.WeatherFragment;
import com.xushuangshuang.graduation.cherry.R;

public class MainActivity extends SlidingFragmentActivity implements ViewPager.OnPageChangeListener {

    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments = new ArrayList<Fragment>();
    private ImageView imageViewTabLine;
    private int currentIndex;
    private int screenWidth;
    private TextView mTabChatTv, mTabContactsTv, mTabFriendTv, mTabGameTv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        // 初始化SlideMenu
        initRightMenu();
        initViewPager();
        initTabLineWidth();

    }

    private void initViewPager() {
        imageViewTabLine = (ImageView) findViewById(R.id.id_tab_line_iv);
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
        mTabContactsTv = (TextView) this.findViewById(R.id.id_contacts_tv);
        mTabChatTv = (TextView) this.findViewById(R.id.id_chat_tv);
        mTabFriendTv = (TextView) this.findViewById(R.id.id_friend_tv);
        mTabGameTv = (TextView) findViewById(R.id.id_contacts_tv_1);

        MyProductFragment myProductFragment = new MyProductFragment();
        NewFragment newFragment = new NewFragment();
        WeatherFragment weatherFragment = new WeatherFragment();
        GameFragment gameFragment = new GameFragment();
        mFragments.add(myProductFragment);
        mFragments.add(newFragment);
        mFragments.add(weatherFragment);
        mFragments.add(gameFragment);
        /**
         * 初始化Adapter
         */
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                return mFragments.get(arg0);
            }
        };
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);
    }

    private void initRightMenu() {

        Fragment leftMenuFragment = new MenuLeftManageFragment();
        setBehindContentView(R.layout.left_menu_frame);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.id_left_menu_frame, leftMenuFragment).commit();
        SlidingMenu menu = getSlidingMenu();
        menu.setMode(SlidingMenu.LEFT_RIGHT);
        // 设置触摸屏幕的模式
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.shadow);
        // 设置滑动菜单视图的宽度
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
//		menu.setBehindWidth()
        // 设置渐入渐出效果的值
        menu.setFadeDegree(0.35f);
        // menu.setBehindScrollScale(1.0f);
        menu.setSecondaryShadowDrawable(R.drawable.shadow);
        //设置右边（二级）侧滑菜单
        menu.setSecondaryMenu(R.layout.right_menu_frame);
        Fragment rightMenuFragment = new MenuRightPersonalFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.id_right_menu_frame, rightMenuFragment).commit();
    }

    public void showLeftMenu(View view) {
        getSlidingMenu().showMenu();
    }

    public void showRightMenu(View view) {
        getSlidingMenu().showSecondaryMenu();
    }

    @Override
    public void onPageScrolled(int position, float offset, int positionOffsetPixels) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imageViewTabLine.getLayoutParams();

        if (currentIndex == 0 && position == 0)// 0->1
        {
            layoutParams.leftMargin = (int) (offset * (screenWidth * 1.0 / 3) + currentIndex
                    * (screenWidth / 3));

        } else if (currentIndex == 1 && position == 0) // 1->0
        {
            layoutParams.leftMargin = (int) (-(1 - offset)
                    * (screenWidth * 1.0 / 3) + currentIndex
                    * (screenWidth / 3));

        } else if (currentIndex == 1 && position == 1) // 1->2
        {
            layoutParams.leftMargin = (int) (offset * (screenWidth * 1.0 / 3) + currentIndex
                    * (screenWidth / 3));
        } else if (currentIndex == 2 && position == 1) // 2->1
        {
            layoutParams.leftMargin = (int) (-(1 - offset)
                    * (screenWidth * 1.0 / 3) + currentIndex
                    * (screenWidth / 3));
        }
        imageViewTabLine.setLayoutParams(layoutParams);
    }

    @Override
    public void onPageSelected(int position) {
        resetTextView();
        switch (position) {
            case 0:
                mTabChatTv.setTextColor(R.color.blue);
                break;
            case 1:
                mTabFriendTv.setTextColor(R.color.blue);
                break;
            case 2:
                mTabContactsTv.setTextColor(R.color.blue);
                break;
            case 3:
                mTabGameTv.setTextColor(R.color.blue);
                break;
        }
        currentIndex = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    /**
     * 设置滑动条的宽度为屏幕的1/3(根据Tab的个数而定)
     */
    private void initTabLineWidth() {
        DisplayMetrics dpMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay()
                .getMetrics(dpMetrics);
        screenWidth = dpMetrics.widthPixels;
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) imageViewTabLine
                .getLayoutParams();
        lp.width = screenWidth / 3;
        imageViewTabLine.setLayoutParams(lp);
    }

    /**
     * 重置颜色
     */
    private void resetTextView() {
        mTabChatTv.setTextColor(R.color.black);
        mTabFriendTv.setTextColor(R.color.black);
        mTabContactsTv.setTextColor(R.color.black);
    }
}
