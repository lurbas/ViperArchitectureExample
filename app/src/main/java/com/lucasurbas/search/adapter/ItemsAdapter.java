package com.lucasurbas.search.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lucasurbas.search.model.SearchItem;
import com.lucasurbas.search.widget.ItemViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucas on 30/08/15.
 */
public class ItemsAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private List<SearchItem> searchItems;
    private Context context;

    public ItemsAdapter(Context context) {
        this.searchItems = new ArrayList<>();
        this.context = context;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(ItemViewHolder.getLayoutResId(), parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {

        final SearchItem searchItem = searchItems.get(position);
        boolean bestResult = position == 0;
        holder.presentSearchItem(context, searchItem, bestResult);
    }

    public void setItems(List<SearchItem> itemList) {
        this.searchItems = itemList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return searchItems.size();
    }
}
