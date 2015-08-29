package com.lucasurbas.search.request;

import com.lucasurbas.search.constant.Url;
import com.lucasurbas.search.model.Response;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Lucas on 29/08/15.
 */
public interface FiveHundredPxApi {

    @GET(Url.PATH_SEARCH)
    Observable<Response> query(@Query(Url.QUERY_TERM) String term,
                    @Query(Url.QUERY_CONSUMER_KEY) String consumerKey);
}
