package com.lucasurbas.search.dagger;

import com.google.gson.Gson;
import com.lucasurbas.search.constant.Url;
import com.lucasurbas.search.fragment.search.interactor.SearchInteractorImpl;
import com.lucasurbas.search.request.FiveHundredPxApi;
import com.lucasurbas.search.request.SearchApi;
import com.lucasurbas.search.request.SearchApiProxy;
import com.squareup.okhttp.OkHttpClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by Lucas on 29/08/15.
 */
@Module(
        complete = false,
        library = true,

        injects = {
                SearchInteractorImpl.class
        }
)
public class ApiModule {

    @Provides
    FiveHundredPxApi providesApi(Gson gson, OkHttpClient client) {

        RestAdapter restAdapter = new RestAdapter.Builder().setClient(new OkClient(client))
                .setEndpoint(Url.BASE_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setConverter(new GsonConverter(gson))
                .build();

        return restAdapter.create(FiveHundredPxApi.class);
    }

    @Provides
    @Singleton
    SearchApi providesSearchApi(FiveHundredPxApi fiveHundredPxApi){
        return new SearchApiProxy(fiveHundredPxApi);
    }
}
