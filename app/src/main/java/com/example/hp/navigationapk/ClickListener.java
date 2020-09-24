package com.example.hp.navigationapk;

import android.view.View;

/**
 * Created by HP on 3/13/2018.
 */

public interface ClickListener {
    public void itemClicked(View view, int position);
    public int onLongClick(View view,int position);
}
