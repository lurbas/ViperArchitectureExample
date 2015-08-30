package com.lucasurbas.search.request;

import com.lucasurbas.search.constant.Url;
import com.lucasurbas.search.model.Response;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Lucas on 30/08/15.
 */
public interface GithubApi {
    @GET(Url.PATH_SEARCH_USERS)
    Observable<Response> query(@Query(Url.QUERY_Q) String query);
}
