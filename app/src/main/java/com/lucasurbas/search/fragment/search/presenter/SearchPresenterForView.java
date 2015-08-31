package com.lucasurbas.search.fragment.search.presenter;

import com.lucasurbas.search.architecture.PresenterForView;
import com.lucasurbas.search.fragment.search.view.SearchView;

/**
 * Created by Lucas on 29/08/15.
 */
public interface SearchPresenterForView extends PresenterForView<SearchView>{

    void init();

    void search(String query);
}
