package com.bilalzaman.motivationalquotes.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bilalzaman.motivationalquotes.R;
import com.bilalzaman.motivationalquotes.activities.ContactusActivity;
import com.bilalzaman.motivationalquotes.activities.LoginActivity;
import com.bilalzaman.motivationalquotes.helpers.UIHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment implements View.OnClickListener{

    private ConstraintLayout notificationLayout, loginLayout, shareLayout, rateLayout, contactLayout;
    private Context context;
    private TextView txtTitle;

    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        txtTitle = view.findViewById(R.id.txtTitle);
        txtTitle.setText("Setting");
        txtTitle.setVisibility(View.VISIBLE);
        notificationLayout = view.findViewById(R.id.notification_layout);
        loginLayout = view.findViewById(R.id.login_layout);
        shareLayout = view.findViewById(R.id.share_layout);
        rateLayout = view.findViewById(R.id.rate_layout);
        contactLayout = view.findViewById(R.id.contact_layout);

        notificationLayout.setOnClickListener(this);
        loginLayout.setOnClickListener(this);
        shareLayout.setOnClickListener(this);
        rateLayout.setOnClickListener(this);
        contactLayout.setOnClickListener(this);
        
        return view ;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.notification_layout:{
               // UIHelper.openActivity(getActivity(),NotificationActivity.class);
                break;
            }
            case R.id.login_layout:{
                UIHelper.openActivity(getActivity(),LoginActivity.class);
                break;
            }
            case R.id.share_layout:{

                break;
            }
            case R.id.rate_layout:{

                break;
            }
            case R.id.contact_layout:{
                UIHelper.openActivity(getActivity(),ContactusActivity.class);
                break;
            }
            default:{
                UIHelper.showLongToastInCenter(getActivity(),"Please choose correct option");
            }
        }
    }
}
