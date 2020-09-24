package com.example.hp.navigationapk;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


public class RatingBar_frag extends android.app.Fragment {
    RatingBar rb;
    ImageView img;
    TextView rate;



    public RatingBar_frag() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_rating_bar_frag,container,false);
       
        img=view.findViewById(R.id.imageView9);
        rb=view.findViewById(R.id.ratingBar);
        rate=view.findViewById(R.id.textView10);

        rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                float rv=rb.getRating();
                rate.setText("Rating is : " + rv);
            }
        });
        return view;
    }


}
