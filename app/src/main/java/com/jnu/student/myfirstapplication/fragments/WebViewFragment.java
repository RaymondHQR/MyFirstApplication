package com.jnu.student.myfirstapplication.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jnu.student.myfirstapplication.R;

public class WebViewFragment extends Fragment {

    private static final String ARG_PARAM_URL = "url";

    // TODO: Rename and change types of parameters
    private String url;

    public static WebViewFragment newInstance(String url) {
        WebViewFragment fragment = new WebViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM_URL, url);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            url = getArguments().getString(ARG_PARAM_URL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web_view, container, false);
        WebView webView = view.findViewById(R.id.wv_main);
        WebSettings settings = webView.getSettings();

        settings.setJavaScriptEnabled(true);//必须
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);//提高渲染的优先级
        settings.setUseWideViewPort(true);//WebView是否支持HTML的“viewport”标签或者使用wide viewport。
        settings.setAllowContentAccess(true);//是否允许在WebView中访问内容URL
        settings.setBuiltInZoomControls(true);//是否使用其内置的变焦机制
        settings.setJavaScriptCanOpenWindowsAutomatically(true);//是否允许自动打开弹窗
        settings.setDomStorageEnabled(true);//是否开启DOM存储API权限

        webView.loadUrl(url);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (!webView.getSettings().getLoadsImagesAutomatically()) {
                    webView.getSettings().setLoadsImagesAutomatically(true);
                }
            }
        });
        return view;
    }
}