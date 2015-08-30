package com.lucasurbas.search.fragment.detail.interactor;

import com.lucasurbas.search.architecture.Interactor;
import com.lucasurbas.search.fragment.detail.presenter.DetailPresenterForInteractor;

/**
 * Created by Lucas on 30/08/15.
 */
public interface DetailInteractor extends Interactor<DetailPresenterForInteractor>{

    void setFavourite(long id, boolean favourite);

    void setVisited(long id, boolean visited);
}
