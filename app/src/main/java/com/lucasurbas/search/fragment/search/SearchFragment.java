package com.lucasurbas.search.fragment.search;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;
import com.lucasurbas.search.R;
import com.lucasurbas.search.adapter.ItemsAdapter;
import com.lucasurbas.search.architecture.BaseFragment;
import com.lucasurbas.search.fragment.search.presenter.SearchPresenterForView;
import com.lucasurbas.search.fragment.search.presenter.SearchPresenterImpl;
import com.lucasurbas.search.fragment.search.view.SearchView;
import com.lucasurbas.search.fragment.search.view.SearchViewState;
import com.lucasurbas.search.model.SearchItem;
import com.lucasurbas.search.util.Util;
import com.lucasurbas.search.widget.RecyclerViewHelper;

import java.util.List;

import butterknife.Bind;

public class SearchFragment extends BaseFragment<SearchView, SearchPresenterForView> implements SearchView {

    private static final String TAG = SearchFragment.class.getSimpleName();

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.pi_searching)
    View piSearching;

    @Bind(R.id.rv_search_result)
    RecyclerView rvSearchResult;

    private ItemsAdapter adapter;

    public static Fragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        //ButterKnife in parent class
        super.onViewCreated(view, savedInstanceState);
        setupToolbar();
        setupRecyclerView();
    }

    private void setupToolbar() {
        ViewCompat.setElevation(toolbar, Util.pxFromDp(getActivity(), 4));
        toolbar.setTitle(R.string.app_name);
        toolbar.inflateMenu(R.menu.menu_search);
        Menu menu = toolbar.getMenu();
        MenuItem searchItem = menu.findItem(R.id.action_search);
        Drawable drawable = searchItem.getIcon();
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, getResources().getColor(R.color.toolbar_icon));
        searchItem.setIcon(drawable);
        android.support.v7.widget.SearchView searchWidget = (android.support.v7.widget.SearchView) searchItem.getActionView();
        searchWidget.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //search action
                getPresenter().search(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void setupRecyclerView() {
        RecyclerViewHelper.setup(getActivity(), rvSearchResult);

        adapter = new ItemsAdapter(getActivity());
        rvSearchResult.setAdapter(adapter);
    }

    @Override
    public ViewState createViewState() {
        return new SearchViewState();
    }

    @Override
    public void onNewViewStateInstance() {
        //nothing on first run. Wait for user search input.
    }

    @Override
    public SearchPresenterForView createPresenter() {
        return new SearchPresenterImpl(getActivity());
    }


    @Override
    public void showItemList(List<SearchItem> itemList) {
        adapter.setItems(itemList);
    }

    @Override
    public void showProgressIndicator(boolean show) {
        ((SearchViewState) getViewState()).setSearching(show);
        piSearching.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
