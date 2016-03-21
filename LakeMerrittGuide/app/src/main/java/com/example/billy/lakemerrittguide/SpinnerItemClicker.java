package com.example.billy.lakemerrittguide;

import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;

/**
 * Created by Billy on 3/21/16.
 */
public class SpinnerItemClicker implements AdapterView.OnItemSelectedListener, View.OnTouchListener {

    boolean userSelect = false;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        userSelect = true;
        return false;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        if (userSelect) {
            // Your selection handling code here
            userSelect = false;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
