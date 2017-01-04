package com.mentor.mentor;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mentor.mentor.R;


public class Fragment_seller_main extends Fragment {


    public Fragment_seller_main()
    {
        // Required empty public constructor
    }

    FragmentTransaction fragmentTransaction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v= inflater.inflate(R.layout.fragment_seller_main, container, false);
        fragmentTransaction=getActivity().getSupportFragmentManager().beginTransaction();
        return v;
    }

}
