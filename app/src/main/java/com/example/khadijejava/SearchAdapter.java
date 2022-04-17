package com.example.khadijejava;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.L;
import com.bumptech.glide.Glide;
import com.example.khadijejava.model.Post;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {


    private ArrayList<Post> post_list;
    private Context context;

    public SearchAdapter(ArrayList<Post> post_list, Context context) {
        this.post_list = post_list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.search_item , parent , false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SearchAdapter.ViewHolder holder, int position) {
        Post model = post_list.get(position);
        holder.title_tv.setText(model.title);
        holder.description_tv.setText(model.excerpt);
        Glide.with(context).load(model.file.thumb).placeholder(R.drawable.logo).into(holder.circleImageView);

    }

    @Override
    public int getItemCount() {
        return post_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView circleImageView;
        TextView title_tv , description_tv ;

        public ViewHolder(View itemView) {
            super(itemView);

            circleImageView =  itemView.findViewById(R.id.image_list);
            title_tv = itemView.findViewById(R.id.title_tv);
            description_tv = itemView.findViewById(R.id.description_tv);

        }
    }
}
