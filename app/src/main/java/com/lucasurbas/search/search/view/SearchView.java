package com.lucasurbas.search.search.view;


import android.os.Bundle;

import com.lucasurbas.search.architecture.View;

/**
 * Created by Lucas on 29/08/15.
 */
public interface SearchView extends View {

    void showItemList(Bundle itemList);

    void showProgressIndicator(boolean show);

    void showError(String message);
}
