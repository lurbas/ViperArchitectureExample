package com.lucasurbas.search.fragment.search.view;

import android.os.Bundle;

import com.hannesdorfmann.mosby.mvp.viewstate.RestoreableViewState;
import com.lucasurbas.search.model.SearchItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucas on 29/08/15.
 */
public class SearchViewState implements RestoreableViewState<SearchView> {

    private final String KEY_ITEMS = "key_items";
    private final String KEY_SEARCHING = "key_searching";

    private ArrayList<SearchItem> searchItemList;
    private boolean isSearching;

    @Override
    public void saveInstanceState(Bundle out) {
        out.putBoolean(KEY_SEARCHING, isSearching);
        out.putParcelableArrayList(KEY_ITEMS, searchItemList);
    }

    @Override
    public RestoreableViewState<SearchView> restoreInstanceState(Bundle in) {
        if (in == null) {
            return null;
        }
        isSearching = in.getBoolean(KEY_SEARCHING, false);
        searchItemList = in.getParcelableArrayList(KEY_ITEMS);
        return this;
    }

    @Override
    public void apply(SearchView view, boolean b) {
        view.showProgressIndicator(isSearching);
        view.showItemList(searchItemList);
    }

    public void setSearching(boolean isSearching) {
        this.isSearching = isSearching;
    }

    public void setItemList(List<SearchItem> itemList) {
        this.searchItemList = new ArrayList<>(itemList);
    }
}
