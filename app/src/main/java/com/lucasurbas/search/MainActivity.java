package com.lucasurbas.search;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.lucasurbas.search.architecture.BaseActivity;
import com.lucasurbas.search.fragment.search.SearchFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState == null){
            Fragment fragment = SearchFragment.newInstance();
            getSupportFragmentManager().beginTransaction().replace(android.R.id.content, fragment).commit();
        }
    }
}
