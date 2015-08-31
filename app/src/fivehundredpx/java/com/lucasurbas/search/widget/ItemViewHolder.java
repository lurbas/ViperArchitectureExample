package com.lucasurbas.search.widget;

import android.content.Context;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.florent37.picassopalette.BitmapPalette;
import com.github.florent37.picassopalette.PicassoPalette;
import com.lucasurbas.search.App;
import com.lucasurbas.search.R;
import com.lucasurbas.search.event.OpenDetailScreenEvent;
import com.lucasurbas.search.model.SearchItem;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by Lucas on 30/08/15.
 */
public class ItemViewHolder extends RecyclerView.ViewHolder {

    private static final String TAG = ItemViewHolder.class.getSimpleName();

    @Inject
    Picasso picasso;

    @Bind(R.id.item_container)
    View itemContainer;
    @Bind(R.id.footer)
    View footer;
    @Bind(R.id.iv_image)
    ImageView ivPhoto;
    @Bind(R.id.tv_desc)
    TextView tvDescription;
    @Bind(R.id.iv_favourite)
    ImageView ivFavourite;

    public ItemViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
        App.getObjectGraph().inject(this);
    }

    public static int getLayoutResId() {
        return R.layout.row_photo;
    }

    public void presentSearchItem(final Context context, final SearchItem searchItem) {

        tvDescription.setTextColor(context.getResources().getColor(R.color.toolbar_icon));
        itemContainer.setBackgroundColor(context.getResources().getColor(R.color.primary));
        if (searchItem.isFavourite()) {
            ivFavourite.setVisibility(View.VISIBLE);
            DrawableCompat.setTint(ivFavourite.getDrawable(), context.getResources().getColor(R.color.toolbar_icon));
        } else {
            ivFavourite.setVisibility(View.GONE);
        }
        if (searchItem.isVisited()) {
            ivPhoto.setAlpha(0.33f);
            itemContainer.setAlpha(0.6f);
        } else {
            ivPhoto.setAlpha(1f);
            itemContainer.setAlpha(1f);
        }

        picasso.load(searchItem.getImageUrl())
                .fit()
                .into(ivPhoto,
                        PicassoPalette.with(searchItem.getImageUrl(), ivPhoto)
                                .intoCallBack(new BitmapPalette.CallBack() {
                                    @Override
                                    public void onPaletteLoaded(Palette palette) {
                                        Palette.Swatch swatch = palette.getDarkVibrantSwatch();
                                        if (swatch != null) {
                                            itemContainer.setBackgroundColor(swatch.getRgb());
                                            tvDescription.setTextColor(swatch.getTitleTextColor());
                                            DrawableCompat.setTint(ivFavourite.getDrawable(), swatch.getTitleTextColor());
                                        }
                                    }
                                })
                );

        String title = searchItem.getTitle();
        tvDescription.setText(title == null || title.isEmpty() ? context.getString(R.string.t_no_desc) : title);

        itemContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new OpenDetailScreenEvent(searchItem));
            }
        });
    }
}
