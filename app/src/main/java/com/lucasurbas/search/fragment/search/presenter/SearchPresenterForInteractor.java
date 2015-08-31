package com.lucasurbas.search.fragment.search.presenter;

import com.lucasurbas.search.architecture.PresenterForInteractor;
import com.lucasurbas.search.fragment.search.interactor.SearchInteractor;
import com.lucasurbas.search.model.SearchItem;

import java.util.List;

/**
 * Created by Lucas on 29/08/15.
 */
public interface SearchPresenterForInteractor extends PresenterForInteractor<SearchInteractor>{

    void showItemList(boolean success, List<SearchItem> itemList, boolean isRequested);
}
