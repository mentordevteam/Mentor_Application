package com.mentor.mentor;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;

/**
 * Created by Vignesh Baskar on 11/4/2016.
 */

class Volley
{
    private static Volley instance;
    private RequestQueue requestQueue;
    private Context context;

    private Volley(Context context)
    {
        this.context=context;
        requestQueue=getRequestQueue();
    }

    private RequestQueue getRequestQueue()
    {
        if(requestQueue==null)
        {
            requestQueue= com.android.volley.toolbox.Volley.newRequestQueue(context);
        }
        return requestQueue;
    }

    public static synchronized Volley getInstance(Context context)
    {
        if(instance==null)
        {
            instance=new Volley(context);
        }
        return instance;
    }

    public <T> void addtoRequestqueue(Request<T> request)
    {
        requestQueue.add(request);
    }
}
