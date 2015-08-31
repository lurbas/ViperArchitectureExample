package com.lucasurbas.search.db;

import java.util.List;

/**
 * Created by lucas.urbas on 31/08/15.
 */
public interface OnTableChangedListener {

    void onChanged();

    <T> Class<T> getTableType();

    List<Long> getIds();
}
