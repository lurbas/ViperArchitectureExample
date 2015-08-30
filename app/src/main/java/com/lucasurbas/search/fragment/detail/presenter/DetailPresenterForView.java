package com.lucasurbas.search.fragment.detail.presenter;

import com.lucasurbas.search.architecture.PresenterForView;
import com.lucasurbas.search.fragment.detail.view.DetailView;
import com.lucasurbas.search.model.SearchItem;

/**
 * Created by Lucas on 30/08/15.
 */
public interface DetailPresenterForView extends PresenterForView<DetailView>{

    void initSearchItem(SearchItem searchItem);

    void toggleFavourite();

    void openInBrowser();

    void setVisited();
}
