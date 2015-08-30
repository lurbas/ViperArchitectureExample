package com.lucasurbas.search.request;

import com.lucasurbas.search.model.SearchItemsProvider;

import rx.Observable;

/**
 * Created by Lucas on 29/08/15.
 */
public interface SearchApi {

    Observable<? extends SearchItemsProvider> query(String query);
}
