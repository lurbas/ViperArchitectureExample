package com.lucasurbas.search.fragment.detail.presenter;

import com.lucasurbas.search.architecture.PresenterForInteractor;
import com.lucasurbas.search.fragment.detail.interactor.DetailInteractor;

/**
 * Created by Lucas on 30/08/15.
 */
public interface DetailPresenterForInteractor extends PresenterForInteractor<DetailInteractor>{

    void visitChanged(boolean isVisited, boolean success);

    void favouriteChanged(boolean isFavourite, boolean success);
}
