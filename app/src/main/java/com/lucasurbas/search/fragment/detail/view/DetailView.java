package com.lucasurbas.search.fragment.detail.view;


import com.lucasurbas.search.architecture.View;
import com.lucasurbas.search.model.SearchItem;

/**
 * Created by Lucas on 30/08/15.
 */
public interface DetailView extends View {

    void setItem(SearchItem searchItem);

    void showFavouriteState(boolean isFavourite);
}
