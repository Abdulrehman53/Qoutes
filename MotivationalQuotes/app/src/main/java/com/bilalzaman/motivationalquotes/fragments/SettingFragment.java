package com.bilalzaman.motivationalquotes.fragments;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bilalzaman.motivationalquotes.R;
import com.bilalzaman.motivationalquotes.databinding.FragmentSettingBinding;
import com.bilalzaman.motivationalquotes.helpers.UIHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {

    private Context context;

    private MySettingHandlers handlers;
    private FragmentSettingBinding binding;

    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_setting, container, false);
        context = container.getContext();
        View view = binding.getRoot();
        handlers = new MySettingHandlers(context);
        binding.setHandlers(handlers);
        return view;


    }

    public class MySettingHandlers {
        Context context;

        public MySettingHandlers(Context context) {
            this.context = context;
        }

        public void onShare(View view) {
           UIHelper.shareApp(context);
        }

        public void onMore(View view){
           UIHelper.moreApplication(context);
        }

        public void onRate(View view) {
            UIHelper.rateUs(context);
        }
    }
}