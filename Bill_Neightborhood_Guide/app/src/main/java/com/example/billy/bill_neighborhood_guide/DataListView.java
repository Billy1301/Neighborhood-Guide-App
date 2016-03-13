package com.example.billy.bill_neighborhood_guide;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DataListView extends RelativeLayout {

    /// this is related to result_layout_view xml...

    TextView headerText;

    public DataListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onFinishInflate(){
        super.onFinishInflate();
        headerText = (TextView)findViewById(com.example.billy.bill_neighborhood_guide.R.id.area_description_text); // this is from relative layout activity list view
    }

    void bindTo(String data){
        headerText.setText(data);
    }
}
