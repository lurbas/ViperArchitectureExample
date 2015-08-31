package com.lucasurbas.search.fragment.detail.interactor;

import android.util.Log;

import com.lucasurbas.search.App;
import com.lucasurbas.search.architecture.BaseInteractor;
import com.lucasurbas.search.db.Db;
import com.lucasurbas.search.fragment.detail.presenter.DetailPresenterForInteractor;
import com.lucasurbas.search.model.SearchItem;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Lucas on 30/08/15.
 */
public class DetailInteractorImpl extends BaseInteractor<DetailPresenterForInteractor> implements DetailInteractor {

    private static final String TAG = DetailInteractorImpl.class.getSimpleName();

    Db database;

    public DetailInteractorImpl(){
        App.getObjectGraph().inject(this);
    }

    @Override
    public void setFavourite(final SearchItem searchItem, final boolean favourite) {
        Observable.just(searchItem)
                .map(UPDATE_IN_DB)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SearchItem>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.v(TAG, "Error: " + e.getMessage());
                        if (isPresenterSubscribed()) {
                            getPresenter().favouriteChanged(!favourite, false);
                        }
                    }

                    @Override
                    public void onNext(SearchItem item) {
                        if (isPresenterSubscribed()) {
                            getPresenter().favouriteChanged(favourite, true);
                        }
                    }
                });
    }

    @Override
    public void setVisited(final SearchItem searchItem, final boolean visited) {
        Observable.just(searchItem)
                .map(UPDATE_IN_DB)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SearchItem>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.v(TAG, "Error: " + e.getMessage());
                        if (isPresenterSubscribed()) {
                            getPresenter().visitChanged(!visited, false);
                        }
                    }

                    @Override
                    public void onNext(SearchItem item) {
                        if (isPresenterSubscribed()) {
                            getPresenter().visitChanged(visited, true);
                        }
                    }
                });
    }

    private final Func1<SearchItem, SearchItem> UPDATE_IN_DB =
            new Func1<SearchItem, SearchItem>() {
                @Override
                public SearchItem call(SearchItem searchItem) {
                    database.createOrUpdateItem(SearchItem.class, searchItem);
                    return searchItem;
                }
            };
}
