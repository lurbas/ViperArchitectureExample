package com.lucasurbas.search.fragment.detail.presenter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.lucasurbas.search.architecture.BasePresenter;
import com.lucasurbas.search.fragment.detail.interactor.DetailInteractor;
import com.lucasurbas.search.fragment.detail.interactor.DetailInteractorImpl;
import com.lucasurbas.search.fragment.detail.view.DetailView;
import com.lucasurbas.search.model.SearchItem;

/**
 * Created by Lucas on 30/08/15.
 */
public class DetailPresenterImpl extends BasePresenter<DetailInteractor, DetailView> implements DetailPresenterForView, DetailPresenterForInteractor {

    private static final String TAG = DetailPresenterImpl.class.getSimpleName();

    private Context context;
    private SearchItem searchItem;

    public DetailPresenterImpl(Context context) {
        this.context = context;
    }

    @Override
    public DetailInteractor createInteractor() {
        return new DetailInteractorImpl();
    }

    @Override
    public void initSearchItem(SearchItem searchItem) {
        this.searchItem = searchItem;
        if (getView() != null) {
            getView().setItem(searchItem);
            getView().showFavouriteState(searchItem.isFavourite());
        }
    }

    @Override
    public void toggleFavourite() {
        searchItem.setIsFavourite(!searchItem.isFavourite());
        if (getView() != null) {
            getView().showFavouriteState(searchItem.isFavourite());
        }
        getInteractor().setFavourite(searchItem.getId(), searchItem.isFavourite());

    }

    @Override
    public void openInBrowser() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(searchItem.getWebUrl()));
        context.startActivity(browserIntent);
    }

    @Override
    public void setVisited() {
        if (!searchItem.isVisited()) {
            searchItem.setIsVisited(true);
            getInteractor().setVisited(searchItem.getId(), true);
        }
    }

    @Override
    public void visitChanged(boolean isVisited, boolean success) {
        if (!success) {
            searchItem.setIsFavourite(isVisited);
            Log.e(TAG, "visitChanged Error");
        }
    }

    @Override
    public void favouriteChanged(boolean isFavourite, boolean success) {
        if (!success) {
            Log.e(TAG, "favouriteChanged Error");
            searchItem.setIsFavourite(isFavourite);
            if (getView() != null) {
                getView().showFavouriteState(isFavourite);
            }
        }
    }
}
