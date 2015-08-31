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
    private final String KEY_INFO_TEXT = "key_info_text";

    private ArrayList<SearchItem> searchItemList;
    private boolean isSearching;
    private String infoText;

    @Override
    public void saveInstanceState(Bundle out) {
        out.putBoolean(KEY_SEARCHING, isSearching);
        out.putParcelableArrayList(KEY_ITEMS, searchItemList);
        out.putString(KEY_INFO_TEXT, infoText);
    }

    @Override
    public RestoreableViewState<SearchView> restoreInstanceState(Bundle in) {
        if (in == null) {
            return null;
        }
        this.isSearching = in.getBoolean(KEY_SEARCHING, false);
        this.searchItemList = in.getParcelableArrayList(KEY_ITEMS);
        this.infoText = in.getString(KEY_INFO_TEXT);
        return this;
    }

    @Override
    public void apply(SearchView view, boolean b) {
        view.showProgressIndicator(isSearching);
        view.showItemList(searchItemList);
        view.setInfoText(infoText);
    }

    public void setSearching(boolean isSearching) {
        this.isSearching = isSearching;
    }

    public void setItemList(List<SearchItem> itemList) {
        this.searchItemList = new ArrayList<>(itemList);
    }

    public void setInfoText(String infoText) {
        this.infoText = infoText;
    }
}
