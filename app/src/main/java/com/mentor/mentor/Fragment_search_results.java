package com.mentor.mentor;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_search_results extends Fragment {


    public Fragment_search_results() {
        // Required empty public constructor
    }


    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_search_results, container, false);
        view=v;
        String search=((MainActivity)getActivity()).search_text;
        ((MainActivity)getActivity()).search_text="";
        TextView textView= (TextView) v.findViewById(R.id.text_view_search_result_show);
        textView.setText(search);
        display_results(search);
        return v;
    }

    private void display_results(String search)
    {
        new Custom_Toast(getActivity().getApplication(),view,getActivity().getLayoutInflater()).show("Results not found");
    }
}
