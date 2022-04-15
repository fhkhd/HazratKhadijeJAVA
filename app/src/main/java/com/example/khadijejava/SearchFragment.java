package com.example.khadijejava;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;
import com.example.khadijejava.api.ApiClient;
import com.example.khadijejava.api.ApiService;
import com.example.khadijejava.model.Data;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {

    LottieAnimationView lottieAnimationView;
    RecyclerView recyclerView;
    ApiService apiService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search , container , false);
        recyclerView = view.findViewById(R.id.recycler_search);
        lottieAnimationView = view.findViewById(R.id.lottie_no_connection);
        checkConnection();
        return view;
    }

    @SuppressLint("ResourceAsColor")
    public void checkConnection(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if(wifi.isConnected()){
            lottieAnimationView.setVisibility(View.INVISIBLE);
            apiService = ApiClient.getClient().create(ApiService.class);
            Call<Data> getApiPost = apiService.getPost();
            getApiPost.enqueue(new Callback<Data>() {
                @Override
                public void onResponse(Call<Data> call, Response<Data> response) {

                    recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
                    recyclerView.setAdapter(new SearchAdapter(response.body().result , getContext()));

                }
                @Override
                public void onFailure(Call<Data> call, Throwable t) {
                    Log.e("qqqq", "onFailure: ",t );

                }
            });

        }else if(mobile.isConnected()){
            lottieAnimationView.setVisibility(View.INVISIBLE);
            apiService = ApiClient.getClient().create(ApiService.class);
            Call<Data> getApiPost = apiService.getPost();
            getApiPost.enqueue(new Callback<Data>() {
                @Override
                public void onResponse(Call<Data> call, Response<Data> response) {
//                Log.e("qqq", "onResponse: "+response.body().ok);
                    recyclerView.setAdapter(new SearchAdapter(response.body().result , getContext()));

                }
                @Override
                public void onFailure(Call<Data> call, Throwable t) {
                    Log.e("qqqq", "onFailure: ",t );

                }
            });

        }else {
            recyclerView.setVisibility(View.INVISIBLE);
            lottieAnimationView.setVisibility(View.VISIBLE);
        }
    }

}