package com.example.hp.navigationapk;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;



@SuppressLint("ValidFragment")
public class first_frag extends android.app.Fragment {
    TextView tv1;
    List<String>lst1;
    int pos;

    public first_frag() {

    }
    void dis(List arra,int pos)
    {
        lst1=arra;
        this.pos=pos;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_first_frag,null);
        ListView lst=view.findViewById(R.id.listview);
        ArrayAdapter adapter=new ArrayAdapter(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1,lst1);
        lst.setAdapter(adapter);
        return view;
    }







}
