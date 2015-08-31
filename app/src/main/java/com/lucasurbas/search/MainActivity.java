package com.lucasurbas.search;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.lucasurbas.search.architecture.BaseActivity;
import com.lucasurbas.search.event.OpenDetailScreenEvent;
import com.lucasurbas.search.fragment.search.SearchFragment;

import de.greenrobot.event.EventBus;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            Fragment fragment = SearchFragment.newInstance();
            getSupportFragmentManager().beginTransaction().replace(android.R.id.content, fragment).commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    public void onEvent(OpenDetailScreenEvent event) {
        startActivity(DetailActivity.getStartIntent(this, event.getSearchItem()));
    }
}
