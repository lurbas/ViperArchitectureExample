package com.lucasurbas.search.fragment.search.view;

import android.os.Bundle;

import com.hannesdorfmann.mosby.mvp.viewstate.RestoreableViewState;

/**
 * Created by Lucas on 29/08/15.
 */
public class SearchViewState implements RestoreableViewState<SearchView> {

    private final String KEY_SEARCHING = "key_searching";

    private boolean isSearching;

    @Override
    public void saveInstanceState(Bundle out) {
        out.putBoolean(KEY_SEARCHING, isSearching);
    }

    @Override
    public RestoreableViewState<SearchView> restoreInstanceState(Bundle in) {
        if (in == null) {
            return null;
        }
        isSearching = in.getBoolean(KEY_SEARCHING, false);
        return this;
    }

    @Override
    public void apply(SearchView view, boolean b) {
        view.showProgressIndicator(isSearching);
    }

    public void setSearching(boolean isSearching) {
        this.isSearching = isSearching;
    }
}
