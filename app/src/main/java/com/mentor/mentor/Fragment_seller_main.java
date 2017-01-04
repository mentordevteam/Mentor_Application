package com.mentor.mentor;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.mentor.mentor.R;


public class Fragment_seller_main extends Fragment {

    FragmentTransaction fragmentTransaction;
    LinearLayout add_new_product;

    public Fragment_seller_main()
    {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v= inflater.inflate(R.layout.fragment_seller_main, container, false);
        fragmentTransaction=getActivity().getSupportFragmentManager().beginTransaction();
        add_new_product= (LinearLayout) v.findViewById(R.id.add_new_products);
        add_new_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentTransaction.replace(R.id.main_drawer_layout_seller,new Fragment_seller_add_product(),"add product").commit();
            }
        });
        return v;
    }

}
