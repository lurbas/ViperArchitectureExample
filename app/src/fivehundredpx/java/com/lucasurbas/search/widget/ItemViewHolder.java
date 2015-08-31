package com.lucasurbas.search.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.florent37.picassopalette.PicassoPalette;
import com.lucasurbas.search.App;
import com.lucasurbas.search.DetailActivity;
import com.lucasurbas.search.R;
import com.lucasurbas.search.model.SearchItem;
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
    @Bind(R.id.iv_image)
    ImageView ivPhoto;
    @Bind(R.id.tv_desc)
    TextView tvDescription;

    public ItemViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
        App.getObjectGraph().inject(this);
    }

    public static int getLayoutResId(){
        return R.layout.row_photo;
    }

    public void presentSearchItem(final Context context, final SearchItem searchItem) {

        tvDescription.setTextColor(context.getResources().getColor(R.color.toolbar_icon));
        tvDescription.setBackgroundColor(context.getResources().getColor(R.color.primary));
        picasso.load(searchItem.getImageUrl())
                .fit()
                .into(ivPhoto,
                        PicassoPalette.with(searchItem.getImageUrl(), ivPhoto)
                                .use(PicassoPalette.Profile.VIBRANT_DARK)
                                .intoBackground(tvDescription)
                                .intoTextColor(tvDescription)
                );
        String title = searchItem.getTitle();
        tvDescription.setText(title == null || title.isEmpty() ? context.getString(R.string.t_no_desc) : title);

        itemContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(DetailActivity.getStartIntent(context, searchItem));
            }
        });
    }
}
