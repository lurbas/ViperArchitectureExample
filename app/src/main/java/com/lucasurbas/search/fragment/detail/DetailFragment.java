package com.lucasurbas.search.fragment.detail;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;
import com.lucasurbas.search.App;
import com.lucasurbas.search.R;
import com.lucasurbas.search.architecture.BaseFragment;
import com.lucasurbas.search.fragment.detail.presenter.DetailPresenterForView;
import com.lucasurbas.search.fragment.detail.presenter.DetailPresenterImpl;
import com.lucasurbas.search.fragment.detail.view.DetailView;
import com.lucasurbas.search.fragment.detail.view.DetailViewState;
import com.lucasurbas.search.model.SearchItem;
import com.lucasurbas.search.util.Util;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Created by Lucas on 30/08/15.
 */
public class DetailFragment extends BaseFragment<DetailView, DetailPresenterForView> implements DetailView {

    private static final String TAG = DetailFragment.class.getSimpleName();
    private static final String KEY_SEARCH_ITEM = "key_search_item";

    @Inject
    Picasso picasso;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.iv_image)
    ImageView ivImage;

    @Bind(R.id.tv_title)
    TextView tvTitle;

    @Bind(R.id.fab_favourite)
    FloatingActionButton fabFavourite;


    public static Fragment newInstance(SearchItem searchItem) {
        Fragment fragment = new DetailFragment();
        Bundle b = new Bundle();
        b.putParcelable(KEY_SEARCH_ITEM, searchItem);
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        //ButterKnife in parent class
        App.getObjectGraph().inject(this);
        super.onViewCreated(view, savedInstanceState);
        setupToolbar();
        setupFab();
    }

    private void setupToolbar() {
        ViewCompat.setElevation(toolbar, Util.pxFromDp(getActivity(), 4));
        toolbar.setTitle(R.string.t_screen_details);
        toolbar.inflateMenu(R.menu.menu_detail);
        Menu menu = toolbar.getMenu();
        MenuItem searchItem = menu.findItem(R.id.action_web);
        Drawable drawable = searchItem.getIcon();
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, getResources().getColor(R.color.toolbar_icon));
        searchItem.setIcon(drawable);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_web:
                        getPresenter().openInBrowser();
                        return true;
                }
                return false;
            }
        });
    }

    private void setupFab() {
        fabFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().toggleFavourite();
            }
        });
    }

    @Override
    public ViewState createViewState() {
        return new DetailViewState();
    }

    @Override
    public void onNewViewStateInstance() {
        SearchItem searchItem = getArguments().getParcelable(KEY_SEARCH_ITEM);
        getPresenter().initSearchItem(searchItem);
        getPresenter().setVisited();
    }

    @Override
    public DetailPresenterForView createPresenter() {
        return new DetailPresenterImpl(getActivity());
    }

    @Override
    public void setItem(SearchItem searchItem) {
        ((DetailViewState) getViewState()).setItem(searchItem);
        picasso.load(searchItem.getImageUrl())
                .fit()
                .into(ivImage);
        tvTitle.setText(searchItem.getTitle());
    }

    @Override
    public void showFavouriteState(boolean isFavourite) {
        ((DetailViewState) getViewState()).setFavourite(isFavourite);
        int resId = isFavourite ? R.drawable.ic_star_white_24dp : R.drawable.ic_star_border_white_24dp;
        Drawable d = getResources().getDrawable(resId);
        DrawableCompat.setTint(d, getResources().getColor(R.color.toolbar_icon));
        fabFavourite.setImageDrawable(d);
    }
}
