package com.mentor.mentor;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Vignesh Baskar on 12/27/2016.
 */

public class Fragment_home extends Fragment
{
    RecyclerView recyclerView;

    ArrayList<String> title=new ArrayList<>();
    ArrayList<String> shop=new ArrayList<>();
    ArrayList<Integer> image=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView= (RecyclerView) v.findViewById(R.id.recycler_view_top5_news_feed);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager lay=new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(lay);

        create_recycler_view();

        RecyclerView.Adapter adapter=new DataAdapter(getActivity().getApplicationContext(),title,shop,image);

        recyclerView.setAdapter(adapter);


        return v;
    }

    private void create_recycler_view()
    {
        title.add("Parker pen");
        title.add("Double cheese pizza");
        title.add("J7 Prime");

        shop.add("GK Book Store");
        shop.add("Dominos pizza");
        shop.add("King mobiles");

        image.add(R.drawable.sample_newsfeed1);
        image.add(R.drawable.sample_newsfeed2);
        image.add(R.drawable.sample_newsfeed3);


    }


    public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder>
    {
        private Context context;
        private ArrayList<String> title;
        private ArrayList<String> product;
        private ArrayList<Integer> image;

        DataAdapter(Context context,ArrayList<String> title,ArrayList<String> product,ArrayList<Integer> image)
        {
            this.context = context;
            this.title=title;
            this.product=product;
            this.image=image;
        }

        @Override
        public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
        {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_view_top_trending, viewGroup, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(DataAdapter.ViewHolder viewHolder, int i)
        {
            viewHolder.textView_title.setText(title.get(i));
            viewHolder.textView_shopname.setText(product.get(i));
            viewHolder.imageView_product_image.setImageResource(image.get(i));
        }

        @Override
        public int getItemCount() {
            return title.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            private TextView textView_title;
            private TextView textView_shopname;
            private ImageView imageView_product_image;
            ViewHolder(View view)
            {
                super(view);
                textView_shopname= (TextView) view.findViewById(R.id.text_view_shop_name_recycler_view);
                textView_title= (TextView) view.findViewById(R.id.text_view_title_vertical_recycler);
                imageView_product_image= (ImageView) view.findViewById(R.id.image_view_recycler_view);
            }
        }
    }
}
