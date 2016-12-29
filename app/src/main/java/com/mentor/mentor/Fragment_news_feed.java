package com.mentor.mentor;


import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_news_feed extends Fragment
{
    ArrayList<String> product;
    ArrayList<String> shop;
    ArrayList<Integer> images;
    int like=0;

    RecyclerView recyclerView;

    public Fragment_news_feed()
    {
        product=new ArrayList<>();
        shop=new ArrayList<>();
        images=new ArrayList<>();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v= inflater.inflate(R.layout.fragment_news_feed, container, false);

        recyclerView= (RecyclerView) v.findViewById(R.id.list_view_news_feed);



        recyclerView.setHasFixedSize(true);
        LinearLayoutManager lay=new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(lay);

        create_list_view();

        RecyclerView.Adapter adapter=new DataAdapter(getActivity().getApplicationContext(),product,shop,images);

        recyclerView.setAdapter(adapter);
        return v;
    }

    private void create_list_view()
    {
        product.add("Parker pen");
        product.add("Double cheese pizza");
        product.add("J7 Prime");

        shop.add("GK Book Store");
        shop.add("Dominos pizza");
        shop.add("King mobiles");

        images.add(R.drawable.sample_newsfeed1);
        images.add(R.drawable.sample_newsfeed2);
        images.add(R.drawable.sample_newsfeed3);
    }


    public class DataAdapter extends RecyclerView.Adapter<Fragment_news_feed.DataAdapter.ViewHolder>
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
        public Fragment_news_feed.DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
        {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_view_news_feed, viewGroup, false);
            return new Fragment_news_feed.DataAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int i)
        {
            holder.textView_title.setText(title.get(i));
            holder.textView_shopname.setText(product.get(i));
            holder.imageView_product_image.setImageResource(image.get(i));
            holder.imageView_like_button.setImageResource(R.drawable.ic_like_button);
            holder.imageView_like_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(like==0)
                    {
                        holder.imageView_like_button.setImageResource(R.drawable.ic_liked);
                        like=1;
                        String likes=holder.textView_likes.getText().toString();
                        int i= Integer.parseInt(likes);
                        i+=1;
                        holder.textView_likes.setText(String.valueOf(i));
                    }
                    else if(like==1)
                    {
                        holder.imageView_like_button.setImageResource(R.drawable.ic_like_button);
                        like=0;
                        String likes=holder.textView_likes.getText().toString();
                        int i= Integer.parseInt(likes);
                        i-=1;
                        holder.textView_likes.setText(String.valueOf(i));
                        //holder.textView_likes.setText(Integer.parseInt(holder.textView_likes.getText().toString())-1);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return title.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            private TextView textView_title;
            private TextView textView_shopname;
            private ImageView imageView_product_image;
            private ImageView imageView_like_button;
            private TextView textView_likes;
            ViewHolder(View view)
            {
                super(view);
                textView_shopname= (TextView) view.findViewById(R.id.text_view_news_feed_shop_name);
                textView_title= (TextView) view.findViewById(R.id.text_view_news_feed_product_name);
                imageView_product_image= (ImageView) view.findViewById(R.id.image_view_news_feed_product);
                imageView_like_button= (ImageView) view.findViewById(R.id.image_view_news_feed_like_button);
                textView_likes= (TextView) view.findViewById(R.id.text_view_news_feed_like_button);
            }
        }
    }

}
