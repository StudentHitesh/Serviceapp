package com.example.hp.navigationapk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * Created by HP on 12/28/2017.
 */

public class CustomAdapterFlipper extends BaseAdapter{
    Context context;
    int img[];
    LayoutInflater inflater;
    public CustomAdapterFlipper(Context applicationContext, int[] image) {
        this.context=applicationContext;
        this.img=image;
        inflater=(LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return img.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=inflater.inflate(R.layout.flipper_item,null);
        ImageView ser_img=(ImageView)convertView.findViewById(R.id.imageView2);
        ser_img.setImageResource(img[position]);
        return convertView;
    }
}
