package com.lucasurbas.search.db;

import com.lucasurbas.search.model.IdProvider;

import java.util.List;

/**
 * Created by lucas.urbas on 31/08/15.
 */
public interface Db {

    void reset();

    void addOnTableChangedListener(OnTableChangedListener listener);

    void removeOnTableChangedListener(OnTableChangedListener listener);

    <T> T getItem(Class<T> type, Long id);

    <T> List<T> getItemList(Class<T> type, List<Long> idList);

    <T extends IdProvider> void createOrUpdateItem(Class<T> type, T item);

    <T extends IdProvider> void createOrUpdateItemList(Class<T> type, List<T> itemList);

}
