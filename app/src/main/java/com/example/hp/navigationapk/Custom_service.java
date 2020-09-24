package com.example.hp.navigationapk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by HP on 12/26/2017.
 */

public class Custom_service extends BaseAdapter{
    Context context;
    String service1[];
    int imgage[];
    LayoutInflater inflater;

    public Custom_service(Home_page home_page, String[] service, int[] image) {
        this.context=home_page;
        this.service1=service;
        this.imgage=image;
        //inflater=LayoutInflater.from(this.context);
        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return imgage.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


            if (convertView == null) {
                convertView = inflater.inflate(R.layout.custom_service, null);
                TextView txtname = convertView.findViewById(R.id.textView2);

                ImageView imageView = convertView.findViewById(R.id.imageView7);

                txtname.setText(service1[position]);
                imageView.setImageResource(imgage[position]);

            }
            return convertView;


    }
}
