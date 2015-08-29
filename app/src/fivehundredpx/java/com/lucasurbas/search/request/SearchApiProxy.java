package com.lucasurbas.search.request;

import com.lucasurbas.search.App;
import com.lucasurbas.search.constant.Url;
import com.lucasurbas.search.model.BundleProvider;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Lucas on 29/08/15.
 */
public class SearchApiProxy implements SearchApi {

    @Inject
    FiveHundredPxApi fiveHundredPxApi;

    public SearchApiProxy() {
        App.getObjectGraph().inject(this);
    }

    @Override
    public Observable<? extends BundleProvider> query(String query) {
        return fiveHundredPxApi.query(query, Url.VALUE_CONSUMER_KEY);
    }
}
