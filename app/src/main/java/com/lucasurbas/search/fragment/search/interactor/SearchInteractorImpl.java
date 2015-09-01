package com.lucasurbas.search.fragment.search.interactor;

import android.util.Log;

import com.lucasurbas.search.App;
import com.lucasurbas.search.architecture.BaseInteractor;
import com.lucasurbas.search.db.Database;
import com.lucasurbas.search.db.OnTableChangedListener;
import com.lucasurbas.search.fragment.search.presenter.SearchPresenterForInteractor;
import com.lucasurbas.search.model.SearchItem;
import com.lucasurbas.search.model.SearchItemsProvider;
import com.lucasurbas.search.request.SearchApi;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Lucas on 29/08/15.
 */
public class SearchInteractorImpl extends BaseInteractor<SearchPresenterForInteractor> implements SearchInteractor {

    private static final String TAG = SearchInteractorImpl.class.getSimpleName();

    @Inject
    SearchApi searchApi;
    @Inject
    Database database;

    private List<Long> ids;

    private OnTableChangedListener onTableChangedListener = new OnTableChangedListener() {
        @Override
        public void onChanged() {
            Log.v(TAG, "!!! Database Changed !!!");
            getSearchItemsFromDatabase(ids);
        }

        @Override
        public Class<SearchItem> getTableType() {
            return SearchItem.class;
        }

        @Override
        public List<Long> getIds() {
            return ids;
        }
    };

    public SearchInteractorImpl() {
        App.getObjectGraph().inject(this);
    }

    @Override
    public void search(String query) {
        searchApi.query(query)
                .map(UPDATE_IN_DB)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<SearchItem>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.v(TAG, "Error: " + e.getMessage());
                        if (isPresenterSubscribed()) {
                            getPresenter().showItemList(false, null, true);
                        }
                    }

                    @Override
                    public void onNext(List<SearchItem> itemList) {
                        if (isPresenterSubscribed()) {
                            getPresenter().showItemList(true, itemList, true);
                        }
                    }
                });
    }

    private final Func1<SearchItemsProvider, List<SearchItem>> UPDATE_IN_DB =
            new Func1<SearchItemsProvider, List<SearchItem>>() {
                @Override
                public List<SearchItem> call(SearchItemsProvider provider) {
                    database.removeOnTableChangedListener(onTableChangedListener);

                    List<SearchItem> itemsFromApi = provider.getSearchItems();
                    ids = extractIdList(itemsFromApi);
                    List<SearchItem> itemsFromDb = database.getItemList(SearchItem.class, ids);
                    List<SearchItem> itemsUpdated = update(itemsFromApi, itemsFromDb);
                    database.createOrUpdateItemList(SearchItem.class, itemsUpdated);

                    database.addOnTableChangedListener(onTableChangedListener);
                    return itemsUpdated;
                }
            };

    private List<Long> extractIdList(List<SearchItem> itemsFromApi) {
        List<Long> ids = new ArrayList<>();
        for(SearchItem searchItem : itemsFromApi){
            ids.add(searchItem.getId());
        }
        return ids;
    }

    private List<SearchItem> update(List<SearchItem> itemsFromApi, List<SearchItem> itemsFromDb) {
        int i = 0;
        for(SearchItem searchItem : itemsFromApi){
            searchItem.setOrder(++i);
            for(SearchItem searchItemFromDb : itemsFromDb){
                if(searchItem.getId() == searchItemFromDb.getId()){
                    searchItem.setIsFavourite(searchItemFromDb.isFavourite());
                    searchItem.setIsVisited(searchItemFromDb.isVisited());
                    break;
                }
            }
        }
        return itemsFromApi;
    }

    private void getSearchItemsFromDatabase(final List<Long> ids){
        Observable.just(ids)
                .map(GET_FROM_DB)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<SearchItem>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.v(TAG, "Error: " + e.getMessage());
                    }

                    @Override
                    public void onNext(List<SearchItem> itemList) {
                        if (isPresenterSubscribed()) {
                            getPresenter().showItemList(true, itemList, false);
                        }
                    }
                });
    }

    private final Func1<List<Long>, List<SearchItem>> GET_FROM_DB =
            new Func1<List<Long>, List<SearchItem>>() {
                @Override
                public List<SearchItem> call(List<Long> ids) {
                    List<SearchItem> itemsFromDb = database.getItemList(SearchItem.class, ids);
                    return itemsFromDb;
                }
            };

    @Override
    public void unsubscribePresenter() {
        super.unsubscribePresenter();
        database.removeOnTableChangedListener(onTableChangedListener);
    }
}
