package com.xushuangshuang.graduation.cherry.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;

import com.xushuangshuang.graduation.cherry.R;

/**
 * Created by Administrator on 2015/5/8.
 */
public class GameFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View messageLayout = inflater.inflate(R.layout.game_fragment_layout, container, false);
        return messageLayout;
    }
}
