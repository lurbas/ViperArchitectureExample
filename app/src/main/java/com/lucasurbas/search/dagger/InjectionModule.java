package com.lucasurbas.search.dagger;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lucasurbas.search.db.Database;
import com.lucasurbas.search.db.DatabaseORMLite;
import com.lucasurbas.search.db.ORMLiteHelper;
import com.lucasurbas.search.fragment.detail.DetailFragment;
import com.lucasurbas.search.fragment.detail.interactor.DetailInteractorImpl;
import com.lucasurbas.search.fragment.search.interactor.SearchInteractorImpl;
import com.lucasurbas.search.widget.ItemViewHolder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.Picasso;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Lucas on 29/08/15.
 */
@Module(
        library = true,
        injects = {
                ItemViewHolder.class,
                DetailFragment.class,
                SearchInteractorImpl.class,
                DetailInteractorImpl.class
        },
        includes = {
                ApiModule.class
        })
public class InjectionModule {

    private static final int CACHE_TIME = 120; //seconds

    private Context applicationContext;

    public InjectionModule(Context context) {
        this.applicationContext = context;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return applicationContext;
    }

    @Provides
    @Singleton
    Picasso providesPicasso() {
        return Picasso.with(applicationContext);
    }

//    @Provides
//    Interceptor providesInterceptor() {
//        return new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Response originalResponse = chain.proceed(chain.request());
//                return originalResponse.newBuilder()
//                        .header("Cache-Control", String.format("max-age=%d, only-if-cached, max-stale=%d", CACHE_TIME, 0))
//                        .build();
//
//            }
//        };
//    }

    @Provides
    Gson providesGson() {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }

    @Provides
    OkHttpClient providesOkHttpClient() {
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(10, TimeUnit.SECONDS);
//        client.setCache(new Cache(applicationContext.getCacheDir(), 10 * 1024 * 1024));
//        client.networkInterceptors().add(interceptor);
        return client;
    }

    @Provides
    @Singleton
    ORMLiteHelper providesDatabaseHelper(Context context){
        return new ORMLiteHelper(context);
    }

    @Provides
    @Singleton
    Database providesDatabase(ORMLiteHelper helper){
        return new DatabaseORMLite(helper);
    }
}
