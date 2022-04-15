package com.example.khadijejava;

import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {


    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(SearchAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
