package com.lucasurbas.search.request;

import com.lucasurbas.search.model.SearchItemsProvider;

import rx.Observable;

/**
 * Created by Lucas on 30/08/15.
 */
public class SearchApiProxy implements SearchApi {

    private GithubApi githubApi;

    public SearchApiProxy(GithubApi githubApi) {
        this.githubApi = githubApi;
    }

    @Override
    public Observable<? extends SearchItemsProvider> query(String query) {
        return githubApi.query(query);
    }
}
