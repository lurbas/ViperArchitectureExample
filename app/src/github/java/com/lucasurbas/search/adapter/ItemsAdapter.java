package com.lucasurbas.search.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lucasurbas.search.App;
import com.lucasurbas.search.R;
import com.lucasurbas.search.constant.Keys;
import com.lucasurbas.search.model.User;
import com.lucasurbas.search.model.UserParcel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Lucas on 30/08/15.
 */
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder> {

    @Inject
    Picasso picasso;

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.iv_avatar)
        ImageView ivAvatar;
        @Bind(R.id.tv_login)
        TextView tvLogin;

        public ItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    private List<User> users;
    private Context context;

    public ItemsAdapter(Context context) {
        App.getObjectGraph().inject(this);
        this.users = new ArrayList<>();
        this.context = context;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_user, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {

        User user = users.get(position);

        picasso.load(user.getAvatarUrl())
                .fit()
                .placeholder(R.drawable.placeholder_avatar)
                .into(holder.ivAvatar);
        holder.tvLogin.setText(user.getLogin());
    }

    public void setItems(Bundle itemList) {
        List<UserParcel> upList = itemList.getParcelableArrayList(Keys.PARCEL_USER_LIST);
        users.clear();
        for (UserParcel up : upList) {
            users.add(up.getUser());
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
