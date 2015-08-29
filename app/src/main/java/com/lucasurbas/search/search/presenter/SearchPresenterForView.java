package com.lucasurbas.search.search.presenter;

import com.lucasurbas.search.architecture.PresenterForView;
import com.lucasurbas.search.search.view.SearchView;

/**
 * Created by Lucas on 29/08/15.
 */
public interface SearchPresenterForView extends PresenterForView<SearchView>{

    //void getSearchHistory();

    void search(String query);
}
