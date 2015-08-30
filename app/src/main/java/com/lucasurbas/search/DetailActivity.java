package com.lucasurbas.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.lucasurbas.search.architecture.BaseActivity;
import com.lucasurbas.search.fragment.detail.DetailFragment;
import com.lucasurbas.search.model.SearchItemParcel;

/**
 * Created by Lucas on 30/08/15.
 */
public class DetailActivity extends BaseActivity {

    private static final String KEY_SEARCH_ITEM = "key_search_item";

    public static Intent getStartIntent(Context context, SearchItemParcel searchItemParcel){
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(KEY_SEARCH_ITEM, searchItemParcel);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState == null){
            SearchItemParcel searchItemParcel = getIntent().getParcelableExtra(KEY_SEARCH_ITEM);
            Fragment fragment = DetailFragment.newInstance(searchItemParcel);
            getSupportFragmentManager().beginTransaction().replace(android.R.id.content, fragment).commit();
        }
    }
}
