package com.example.acorutas.ui;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.acorutas.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class history_Fragment extends Fragment {

    private WebView Wmapa;

    public history_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_history_, container, false);

        Wmapa = (WebView) v.findViewById(R.id.WV_mapa);
        Wmapa.loadUrl("https://www.google.com/maps/d/viewer?mid=1eDeBiKh_C2h9i5agIXyxrYzGLFaYR3kb&ll=19.410357954684365%2C-99.08837564999999&z=11");

        // this will enable the javascipt.
        Wmapa.getSettings().setJavaScriptEnabled(true);

        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.
        Wmapa.setWebViewClient(new WebViewClient());


        return v;
    }



}
