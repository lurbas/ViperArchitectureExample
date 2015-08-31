package com.lucasurbas.search.fragment.search.view;


import com.lucasurbas.search.architecture.View;
import com.lucasurbas.search.model.SearchItem;

import java.util.List;

/**
 * Created by Lucas on 29/08/15.
 */
public interface SearchView extends View {

    void showItemList(List<SearchItem> itemList);

    void showProgressIndicator(boolean show);

    void showError(String message);

    void setInfoText(String text);
}
