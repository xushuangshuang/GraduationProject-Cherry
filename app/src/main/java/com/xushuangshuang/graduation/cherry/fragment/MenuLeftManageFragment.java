package com.xushuangshuang.graduation.cherry.fragment;

import java.util.Arrays;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.xushuangshuang.graduation.cherry.R;

public class MenuLeftManageFragment extends Fragment {
    private View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.left_menu, container, false);
    }

//	private void initView(LayoutInflater inflater, ViewGroup container)
//	{
//
//
//		mCategories = (ListView) mView
//				.findViewById(R.id.id_listview_categories);
//		mAdapter = new ArrayAdapter<String>(getActivity(),
//				android.R.layout.simple_list_item_1, mDatas);
//		mCategories.setAdapter(mAdapter);
//	}
}
