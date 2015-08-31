package com.lucasurbas.search.fragment.search.presenter;

import android.content.Context;

import com.lucasurbas.search.R;
import com.lucasurbas.search.architecture.BasePresenter;
import com.lucasurbas.search.fragment.search.interactor.SearchInteractor;
import com.lucasurbas.search.fragment.search.interactor.SearchInteractorImpl;
import com.lucasurbas.search.fragment.search.view.SearchView;
import com.lucasurbas.search.model.SearchItem;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Lucas on 29/08/15.
 */
public class SearchPresenterImpl extends BasePresenter<SearchInteractor, SearchView> implements SearchPresenterForView, SearchPresenterForInteractor {

    private static final String EMPTY = "";

    private AtomicInteger queryCounter;
    private Context context;

    public SearchPresenterImpl(Context context) {
        this.context = context;
        this.queryCounter = new AtomicInteger();
    }

    @Override
    public void init() {
        if (getView() != null) {
            getView().setInfoText(context.getString(R.string.t_welcome));
        }
    }

    @Override
    public SearchInteractor createInteractor() {
        return new SearchInteractorImpl();
    }

    @Override
    public void search(String query) {
        if (queryCounter.getAndIncrement() == 0) {
            if (getView() != null) {
                getView().showProgressIndicator(true);
                getView().setInfoText(EMPTY);
            }
        }
        getInteractor().search(query);
    }

    @Override
    public void showItemList(boolean success, List<SearchItem> itemList, boolean isRequested) {
        if (isRequested && queryCounter.decrementAndGet() == 0) {
            if (getView() != null) {
                getView().showProgressIndicator(false);
            }
        }
        if (getView() != null) {
            getView().setInfoText(EMPTY);
        }
        if (getView() != null && success) {
            getView().showItemList(itemList);
            if (itemList.size() <= 0) {
                getView().setInfoText(context.getString(R.string.t_no_items));
            }
        } else if (getView() != null) {
            getView().showError(context.getString(R.string.error_connection));
        }
    }
}
