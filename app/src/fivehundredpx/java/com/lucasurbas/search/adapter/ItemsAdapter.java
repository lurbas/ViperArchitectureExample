package com.lucasurbas.search.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.florent37.picassopalette.PicassoPalette;
import com.lucasurbas.search.App;
import com.lucasurbas.search.R;
import com.lucasurbas.search.constant.Keys;
import com.lucasurbas.search.model.Photo;
import com.lucasurbas.search.model.PhotoParcel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Lucas on 29/08/15.
 */
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder> {

    @Inject
    Picasso picasso;

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.ll_item_container)
        View llItemContainer;
        @Bind(R.id.iv_image)
        ImageView ivPhoto;
        @Bind(R.id.tv_desc)
        TextView tvDescription;

        public ItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    private List<Photo> photos;
    private Context context;

    public ItemsAdapter(Context context) {
        App.getObjectGraph().inject(this);
        this.photos = new ArrayList<>();
        this.context = context;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_photo, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {

        Photo photo = photos.get(position);

        holder.tvDescription.setTextColor(context.getResources().getColor(R.color.toolbar_icon));
        holder.llItemContainer.setBackgroundColor(context.getResources().getColor(R.color.primary));
        picasso.load(photo.getImageUrl())
                .fit()
                .into(holder.ivPhoto,
                        PicassoPalette.with(photo.getImageUrl(), holder.ivPhoto)
                                .use(PicassoPalette.Profile.VIBRANT_DARK)
                                .intoBackground(holder.llItemContainer)
                                .intoTextColor(holder.tvDescription)
                );
        holder.tvDescription.setText(photo.getDescription() == null || photo.getDescription().isEmpty() ? context.getString(R.string.t_no_desc) : photo.getDescription());

    }

    public void setItems(Bundle itemList) {
        List<PhotoParcel> ppList = itemList.getParcelableArrayList(Keys.PARCEL_PHOTO_LIST);
        photos.clear();
        for (PhotoParcel pp : ppList) {
            photos.add(pp.getPhoto());
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }
}
