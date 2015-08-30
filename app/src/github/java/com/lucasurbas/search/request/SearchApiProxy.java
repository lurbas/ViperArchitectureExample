package com.lucasurbas.search.request;

import com.lucasurbas.search.App;
import com.lucasurbas.search.model.SearchItemsProvider;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Lucas on 30/08/15.
 */
public class SearchApiProxy implements SearchApi {

    @Inject
    GithubApi githubApi;

    public SearchApiProxy() {
        App.getObjectGraph().inject(this);
    }

    @Override
    public Observable<? extends SearchItemsProvider> query(String query) {
        return githubApi.query(query);
    }
}
