package com.mentor.mentor;

import android.content.Context;

/**
 * Created by Vignesh Baskar on 12/11/2016.
 */

public class SharedPreferences
{
    Context context;
    SharedPreferences(Context context)
    {
        this.context=context;
    }

    public void set(String name,String value)
    {
        android.content.SharedPreferences s=context.getSharedPreferences("mentor", Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor=s.edit();
        editor.putString(name,value);
        editor.apply();
    }
}
