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
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.airbnb.lottie.LottieAnimationView;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.github.ybq.android.spinkit.style.FoldingCube;
import com.github.ybq.android.spinkit.style.Wave;

public class HomeFragment extends Fragment {

    public static WebView webView;
    SwipeRefreshLayout swipeRefreshLayout;
    ProgressBar progressBar;
    LottieAnimationView lottieAnimationView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home , container , false);
        initID(view);
        setProgressBar();
        load_webview();
        setSwipeRefreshLayout();
        return view;
    }

    private void initID(View view){
        webView = view.findViewById(R.id.webview_home);
        progressBar = view.findViewById(R.id.spinkit);
        lottieAnimationView = view.findViewById(R.id.lottie_no_connection);
        swipeRefreshLayout = view.findViewById(R.id.swipe_home);
    }

    private void setProgressBar(){
        Sprite doubleBounce = new FoldingCube();
        progressBar.setIndeterminateDrawable(doubleBounce);
    }

    private void setSwipeRefreshLayout(){
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                load_webview();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    public void load_webview(){
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);
                swipeRefreshLayout.setRefreshing(false);
                super.onPageFinished(view, url);
            }
        });

        checkConnection();
        webView.goBack();
    }

    public void checkConnection(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if(wifi.isConnected()){
            webView.loadUrl("https://khadije.com/fa");
            webView.setVisibility(View.VISIBLE);
            lottieAnimationView.setVisibility(View.INVISIBLE);

        }else if(mobile.isConnected()){
            webView.loadUrl("https://khadije.com/fa");
            webView.setVisibility(View.VISIBLE);
            lottieAnimationView.setVisibility(View.INVISIBLE);

        }else {
            progressBar.setVisibility(View.INVISIBLE);
            webView.setVisibility(View.INVISIBLE);
            lottieAnimationView.setVisibility(View.VISIBLE);
        }
    }


}