package com.lucasurbas.search.fragment.search.interactor;

import com.lucasurbas.search.architecture.BaseInteractor;
import com.lucasurbas.search.db.Db;
import com.lucasurbas.search.db.OnTableChangedListener;
import com.lucasurbas.search.fragment.search.presenter.SearchPresenterForInteractor;
import com.lucasurbas.search.model.SearchItem;
import com.lucasurbas.search.model.SearchItemsProvider;
import com.lucasurbas.search.request.SearchApiProxy;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Lucas on 29/08/15.
 */
public class SearchInteractorImpl extends BaseInteractor<SearchPresenterForInteractor> implements SearchInteractor {

    private SearchApiProxy searchApiProxy;
    private OnTableChangedListener onTableChangedListener;

    private Db database;

//    @Override
//    public void getSearchHistory() {
//
//    }

    public SearchInteractorImpl() {
        searchApiProxy = new SearchApiProxy();
    }

    @Override
    public void search(String query) {
        searchApiProxy.query(query)
                .map(UPDATE_IN_DB)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<SearchItem>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isPresenterSubscribed()) {
                            getPresenter().showItemList(false, null);
                        }
                    }

                    @Override
                    public void onNext(List<SearchItem> itemList) {
                        if (isPresenterSubscribed()) {
                            getPresenter().showItemList(true, itemList);
                        }
                    }
                });
    }

    private final Func1<SearchItemsProvider, List<SearchItem>> UPDATE_IN_DB =
            new Func1<SearchItemsProvider, List<SearchItem>>() {
                @Override
                public List<SearchItem> call(SearchItemsProvider provider) {
                    List<SearchItem> itemsFromApi = provider.getSearchItems();
                    List<Long> idList = extractIdList(itemsFromApi);

                    List<SearchItem> itemsFromDb = database.getItemList(SearchItem.class, idList);

                    List<SearchItem> itemsUpdated = update(itemsFromApi, itemsFromDb);

                    database.createOrUpdateItemList(SearchItem.class, itemsUpdated);

                    return itemsUpdated;
                }
            };


    private List<Long> extractIdList(List<SearchItem> itemsFromApi) {
        return null;
    }

    private List<SearchItem> update(List<SearchItem> itemsFromApi, List<SearchItem> itemsFromDb) {
        return null;
    }
}
