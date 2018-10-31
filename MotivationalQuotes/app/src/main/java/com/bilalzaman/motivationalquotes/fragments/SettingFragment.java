package com.bilalzaman.motivationalquotes.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bilalzaman.motivationalquotes.R;
import com.bilalzaman.motivationalquotes.helpers.UIHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment implements View.OnClickListener {

    private ConstraintLayout notificationLayout, loginLayout, shareLayout, rateLayout, contactLayout;
    private Context context;
    private TextView txtTitle;
    private CardView contact_cardView, login_cardView;
    private ImageButton imgCancel, loginCancel;

    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        context = container.getContext();
        txtTitle = view.findViewById(R.id.txtTitle);
        txtTitle.setText("Setting");
        txtTitle.setVisibility(View.VISIBLE);
        notificationLayout = view.findViewById(R.id.notification_layout);
        loginLayout = view.findViewById(R.id.login_layout);
        shareLayout = view.findViewById(R.id.share_layout);
        rateLayout = view.findViewById(R.id.rate_layout);
        contactLayout = view.findViewById(R.id.contact_layout);
        contact_cardView = view.findViewById(R.id.contact_cardView);
        imgCancel = view.findViewById(R.id.imgcancel);
        login_cardView = view.findViewById(R.id.login_cardView);
        loginCancel = view.findViewById(R.id.cancel);

        imgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation slideDown = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_down);
                contact_cardView.startAnimation(slideDown);
                contact_cardView.setVisibility(View.GONE);

            }
        });

        loginCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation slideDown = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_right);
                login_cardView.startAnimation(slideDown);
                login_cardView.setVisibility(View.GONE);
            }
        });

        notificationLayout.setOnClickListener(this);
        loginLayout.setOnClickListener(this);
        shareLayout.setOnClickListener(this);
        rateLayout.setOnClickListener(this);
        contactLayout.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.notification_layout: {
                // UIHelper.openActivity(getActivity(),NotificationActivity.class);
                break;
            }
            case R.id.login_layout: {
                // UIHelper.openActivity(getActivity(),LoginActivity.class);
                Animation slideRight = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_left);

                if (login_cardView.getVisibility() == View.GONE) {
                    login_cardView.startAnimation(slideRight);
                    login_cardView.setVisibility(View.VISIBLE);
                }
                break;
            }
            case R.id.share_layout: {
                Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Motivation Quotes");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "");
                context.startActivity(Intent.createChooser(shareIntent, context.getResources().getString(R.string.share_using)));
                break;
            }
            case R.id.rate_layout: {

                break;
            }
            case R.id.contact_layout: {
                //   UIHelper.openActivity(getActivity(), ContactusActivity.class);
                Animation slideUp = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_up);

                if (contact_cardView.getVisibility() == View.GONE) {
                    contact_cardView.startAnimation(slideUp);
                    contact_cardView.setVisibility(View.VISIBLE);
                }
                break;
            }
            default: {
                UIHelper.showLongToastInCenter(getActivity(), "Please choose correct option");
            }
        }
    }
}