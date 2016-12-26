package com.mentor.mentor;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Vignesh Baskar on 12/10/2016.
 */

public class Custom_Toast
{
    private Context context;
    private View v;
    private LayoutInflater layoutInflater;
    Custom_Toast(Context c, View v, LayoutInflater layoutInflater)
    {
        context=c;
        this.v=v;
        this.layoutInflater=layoutInflater;
    }

    public void show(String s)
    {
        View view=layoutInflater.inflate(R.layout.custom_toast,(ViewGroup)v.findViewById(R.id.custom_toast_layout));
        Toast toast=new Toast(context);
        TextView textView= (TextView) view.findViewById(R.id.text_view_custom_toast);
        textView.setText(s);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM,0,50);
        toast.setView(view);
        toast.show();
    }
}
