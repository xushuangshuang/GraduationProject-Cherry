package com.xushuangshuang.graduation.cherry.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xushuangshuang.graduation.cherry.R;

public class NewFragment extends Fragment
{

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View messageLayout = inflater.inflate(R.layout.new_fragment, container, false);
		return messageLayout;
	}

}
