package com.example.khadijejava;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.airbnb.lottie.LottieAnimationView;

public class DelNeveshteFragment extends Fragment {

    public static WebView webView_delneveshte;
    SwipeRefreshLayout swipeRefreshLayout;
    LottieAnimationView noConnectionLottie , progressLottie ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_del_neveshte , container , false);
        initID(view);
        loade_webView();
        setSwipeRefreshLayout();
        return view;
    }

    private void initID(View view){
        webView_delneveshte = view.findViewById(R.id.webview_delneveshte);
        progressLottie = view.findViewById(R.id.progress_lottie);
        noConnectionLottie = view.findViewById(R.id.lottie_no_connection);
        swipeRefreshLayout = view.findViewById(R.id.swipe_delneveshte);
    }

    private void setSwipeRefreshLayout(){
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loade_webView();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    public void loade_webView(){
        webView_delneveshte.getSettings().setJavaScriptEnabled(true);
        webView_delneveshte.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressLottie.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressLottie.setVisibility(View.INVISIBLE);
            }
        });
        checkConnection();
    }


    public void checkConnection(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if(wifi.isConnected()){
            webView_delneveshte.loadUrl("https://khadije.com/delneveshte");
            webView_delneveshte.setVisibility(View.VISIBLE);
            noConnectionLottie.setVisibility(View.INVISIBLE);

        }else if(mobile.isConnected()){
            webView_delneveshte.loadUrl("https://khadije.com/delneveshte");
            webView_delneveshte.setVisibility(View.VISIBLE);
            noConnectionLottie.setVisibility(View.INVISIBLE);

        }else {
            webView_delneveshte.setVisibility(View.INVISIBLE);
            progressLottie.setVisibility(View.INVISIBLE);
            noConnectionLottie.setVisibility(View.VISIBLE);
        }
    }


}