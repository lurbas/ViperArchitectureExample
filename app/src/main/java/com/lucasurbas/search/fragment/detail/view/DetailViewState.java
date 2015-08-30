package com.lucasurbas.search.fragment.detail.view;

import android.os.Bundle;

import com.hannesdorfmann.mosby.mvp.viewstate.RestoreableViewState;
import com.lucasurbas.search.model.SearchItem;
import com.lucasurbas.search.model.SearchItemParcel;

/**
 * Created by Lucas on 30/08/15.
 */
public class DetailViewState implements RestoreableViewState<DetailView> {

    private final String KEY_ITEM = "key_item";
    private final String KEY_FAVOURITE = "key_favourite";

    private SearchItemParcel item;
    private boolean isFavourite;

    @Override
    public void saveInstanceState(Bundle out) {
        out.putBoolean(KEY_FAVOURITE, isFavourite);
        out.putParcelable(KEY_ITEM, item);
    }

    @Override
    public RestoreableViewState<DetailView> restoreInstanceState(Bundle in) {
        if (in == null) {
            return null;
        }
        this.isFavourite = in.getBoolean(KEY_FAVOURITE, false);
        this.item = in.getParcelable(KEY_ITEM);
        return this;
    }

    @Override
    public void apply(DetailView view, boolean b) {

        view.showFavouriteState(isFavourite);
        view.setItem(item.getSearchItem());
    }

    public void setItem(SearchItem item) {
        this.item = new SearchItemParcel(item);
    }

    public void setFavourite(boolean isFavourite) {
        this.isFavourite = isFavourite;
    }


}
