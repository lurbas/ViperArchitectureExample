package com.lucasurbas.search.search.presenter;

import android.content.Context;
import android.os.Bundle;

import com.lucasurbas.search.R;
import com.lucasurbas.search.architecture.BasePresenter;
import com.lucasurbas.search.search.interactor.SearchInteractor;
import com.lucasurbas.search.search.interactor.SearchInteractorImpl;
import com.lucasurbas.search.search.view.SearchView;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Lucas on 29/08/15.
 */
public class SearchPresenterImpl extends BasePresenter<SearchInteractor, SearchView> implements SearchPresenterForView, SearchPresenterForInteractor {

    private AtomicInteger queryCounter;
    private Context context;

    public SearchPresenterImpl(Context context) {
        this.context = context;
        this.queryCounter = new AtomicInteger();
    }

    @Override
    public SearchInteractor createInteractor() {
        return new SearchInteractorImpl();
    }

//    @Override
//    public void getSearchHistory() {
//
//    }

    @Override
    public void search(String query) {
        if (queryCounter.getAndIncrement() == 0) {
            if (getView() != null) {
                getView().showProgressIndicator(true);
            }
        }
        getInteractor().search(query);
    }

    @Override
    public void showItemList(boolean success, Bundle itemList) {
        if (queryCounter.decrementAndGet() == 0) {
            if (getView() != null) {
                getView().showProgressIndicator(false);
            }
        }
        if (getView() != null && success) {
            getView().showItemList(itemList);
        } else if (getView() != null) {
            getView().showError(context.getString(R.string.error_connection));
        }
    }
}
