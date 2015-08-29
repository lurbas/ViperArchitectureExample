package com.lucasurbas.search;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.lucasurbas.search.search.SearchFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState == null){
            Fragment fragment = SearchFragment.newInstance();
            getSupportFragmentManager().beginTransaction().replace(android.R.id.content, fragment).commit();
        }
    }
}
