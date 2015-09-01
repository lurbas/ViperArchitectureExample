package com.lucasurbas.search.request;

import com.lucasurbas.search.constant.Url;
import com.lucasurbas.search.model.SearchItemsProvider;

import rx.Observable;

/**
 * Created by Lucas on 29/08/15.
 */
public class SearchApiProxy implements SearchApi {

    private FiveHundredPxApi fiveHundredPxApi;

    public SearchApiProxy(FiveHundredPxApi fiveHundredPxApi) {
        this.fiveHundredPxApi = fiveHundredPxApi;
    }

    @Override
    public Observable<? extends SearchItemsProvider> query(String query) {
        return fiveHundredPxApi.query(query, Url.VALUE_CONSUMER_KEY);
    }
}
