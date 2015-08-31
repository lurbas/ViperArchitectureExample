package com.lucasurbas.search.db;

import com.j256.ormlite.stmt.PreparedQuery;
import com.lucasurbas.search.constant.Field;
import com.lucasurbas.search.model.IdProvider;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by lucas.urbas on 31/08/15.
 */
public class DatabaseManager implements Db {

    private static final String TAG = DatabaseManager.class.getSimpleName();

    private DatabseHelper helper;
    private List<OnTableChangedListener> listenerList;

    public DatabaseManager(DatabseHelper helper) {
        this.helper = helper;
        listenerList = Collections.synchronizedList(new ArrayList());
    }

    @Override
    public void reset() {
        helper.resetDatabase();
    }

    @Override
    public void addOnTableChangedListener(OnTableChangedListener listener) {
        listenerList.add(listener);
    }

    @Override
    public void removeOnTableChangedListener(OnTableChangedListener listener) {
        listenerList.remove(listener);
    }

    @Override
    public <T> T getItem(Class<T> type, Long id) {
        try {
            return helper.getCustomDao(type).queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public <T> List<T> getItemList(Class<T> type, List<Long> idList) {
        try {
            PreparedQuery<T> query = helper.getCustomDao(type).queryBuilder().where().in(Field.ID, idList).prepare();
            return helper.getCustomDao(type).query(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public <T extends IdProvider> void createOrUpdateItem(Class<T> type, T item) {
        try {
            helper.getCustomDao(type).createOrUpdate(item);
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        notifyDatabaseChanged(type, item);
    }

    @Override
    public <T extends IdProvider> void createOrUpdateItemList(final Class<T> type, final List<T> itemList) {
        try {
            helper.getCustomDao(type).callBatchTasks(new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    for (T item : itemList) {
                        helper.getCustomDao(type).createOrUpdate(item);
                    }
                    return null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        notifyDatabaseChanged(type, itemList);
    }

    private <T extends IdProvider> void notifyDatabaseChanged(Class<T> type, T item) {
        for (OnTableChangedListener listener : listenerList) {
            if (listener.getTableType().equals(type) && listener.getIds().contains(item.getId())) {
                listener.onChanged();
            }
        }
    }

    private <T extends IdProvider> void notifyDatabaseChanged(Class<T> type, List<T> itemList) {
        for (OnTableChangedListener listener : listenerList) {
            if (listener.getTableType().equals(type)) {
                for (T item : itemList) {
                    if (listener.getIds().contains(item.getId())) {
                        listener.onChanged();
                        break;
                    }
                }
            }
        }
    }
}
