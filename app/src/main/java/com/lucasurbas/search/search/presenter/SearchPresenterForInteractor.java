package com.lucasurbas.search.search.presenter;

import android.os.Bundle;

import com.lucasurbas.search.architecture.PresenterForInteractor;
import com.lucasurbas.search.search.interactor.SearchInteractor;

/**
 * Created by Lucas on 29/08/15.
 */
public interface SearchPresenterForInteractor extends PresenterForInteractor<SearchInteractor>{

    void showItemList(boolean success, Bundle itemList);
}
