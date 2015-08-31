package com.lucasurbas.search.fragment.detail.interactor;

import com.lucasurbas.search.architecture.Interactor;
import com.lucasurbas.search.fragment.detail.presenter.DetailPresenterForInteractor;
import com.lucasurbas.search.model.SearchItem;

/**
 * Created by Lucas on 30/08/15.
 */
public interface DetailInteractor extends Interactor<DetailPresenterForInteractor>{

    void setFavourite(SearchItem searchItem, boolean favourite);

    void setVisited(SearchItem searchItem, boolean visited);
}
