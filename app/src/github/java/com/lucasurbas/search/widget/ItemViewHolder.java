package com.lucasurbas.search.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lucasurbas.search.App;
import com.lucasurbas.search.DetailActivity;
import com.lucasurbas.search.R;
import com.lucasurbas.search.model.SearchItem;
import com.lucasurbas.search.model.SearchItemParcel;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Lucas on 30/08/15.
 */
public class ItemViewHolder extends RecyclerView.ViewHolder {

    @Inject
    Picasso picasso;

    @Bind(R.id.item_container)
    View itemContainer;
    @Bind(R.id.iv_avatar)
    ImageView ivAvatar;
    @Bind(R.id.tv_login)
    TextView tvLogin;

    public ItemViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
        App.getObjectGraph().inject(this);
    }

    public static int getLayoutResId(){
        return R.layout.row_user;
    }

    public void presentSearchItem(final Context context, final SearchItem searchItem) {
        picasso.load(searchItem.getImageUrl())
                .fit()
                .placeholder(R.drawable.placeholder_avatar)
                .into(ivAvatar);
        tvLogin.setText(searchItem.getTitle());

        itemContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(DetailActivity.getStartIntent(context, new SearchItemParcel(searchItem)));
            }
        });
    }
}
