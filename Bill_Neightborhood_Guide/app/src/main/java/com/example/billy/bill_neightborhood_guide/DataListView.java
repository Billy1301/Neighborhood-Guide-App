package com.example.billy.bill_neightborhood_guide;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DataListView extends RelativeLayout {

    /// this is related to layout_view xml...

    TextView headerText;

    public DataListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onFinishInflate(){
        super.onFinishInflate();
        headerText = (TextView)findViewById(R.id.header_text); // this is from relative layout activity list view
    }

    void bindTo(String data){
        headerText.setText(data);
    }
}
