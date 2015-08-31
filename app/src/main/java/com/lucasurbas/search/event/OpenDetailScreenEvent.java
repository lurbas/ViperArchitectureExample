package com.lucasurbas.search.event;

import com.lucasurbas.search.model.SearchItem;

/**
 * Created by Lucas on 31/08/15.
 */
public class OpenDetailScreenEvent {
    private SearchItem searchItem;

    public OpenDetailScreenEvent(SearchItem searchItem){
        this.searchItem = searchItem;
    }

    public SearchItem getSearchItem() {
        return searchItem;
    }
}
