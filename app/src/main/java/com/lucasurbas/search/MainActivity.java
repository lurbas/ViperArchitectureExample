package com.lucasurbas.search;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.lucasurbas.search.architecture.BaseActivity;
import com.lucasurbas.search.event.OpenDetailScreenEvent;
import com.lucasurbas.search.fragment.detail.DetailFragment;
import com.lucasurbas.search.fragment.search.SearchFragment;

import de.greenrobot.event.EventBus;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            Fragment fragment = SearchFragment.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.container_master, fragment).commit();
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
        if (findViewById(R.id.container_detail) == null) {
            startActivity(DetailActivity.getStartIntent(this, event.getSearchItem()));
        } else {
            Fragment fragment = DetailFragment.newInstance(event.getSearchItem());
            getSupportFragmentManager().beginTransaction().replace(R.id.container_detail, fragment).commit();
        }
    }
}
