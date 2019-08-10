package com.adjiekurniawan.sumbission2_dicoding.Fragments;


import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adjiekurniawan.sumbission2_dicoding.Activities.DetailMovieActivity;
import com.adjiekurniawan.sumbission2_dicoding.Adapters.TvShowAdapter;
import com.adjiekurniawan.sumbission2_dicoding.Models.TvShow;
import com.adjiekurniawan.sumbission2_dicoding.R;

import java.util.ArrayList;

public class FragmentTvShow extends Fragment {

    private Context context;
    private View rootView;
    private RecyclerView recyclerView;
    private String[] dataTvshow;
    private String[] dataTvshowDuration;
    private String[] dataTvshowCategory;
    private String[] dataTvshowDescription;
    private String[] dataTvshowRating;
    private String[] category;
    private String[] dataTvshowReleaseDateLong;
    private String[] dataTvshowDirector;
    private TypedArray dataTvshowCover;
    private String categoryIntent = "tvshow";

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_tv_show, container, false);
        context = getContext();
        initView();
        return rootView;
    }

    private void initView(){
        recyclerView = rootView.findViewById(R.id.dataTvShow);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        initData();
    }

    private void initData(){
        dataTvshowCover = getResources().obtainTypedArray(R.array.data_cover_tvshow);
        dataTvshow = getResources().getStringArray(R.array.data_tvshow);
        dataTvshowDuration = getResources().getStringArray(R.array.data_tvshow_duration);
        dataTvshowCategory = getResources().getStringArray(R.array.data_tvshow_category);
        dataTvshowDescription = getResources().getStringArray(R.array.data_tvshow_description);
        dataTvshowRating = getResources().getStringArray(R.array.data_tvshow_rating);
        dataTvshowReleaseDateLong = getResources().getStringArray(R.array.data__tvshow_release_date_long);
        dataTvshowDirector = getResources().getStringArray(R.array.data_tvshow_director);
        category = getResources().getStringArray(R.array.category_tvshow);
        String[] datareleasedate = getResources().getStringArray(R.array.data__tvshow_release_date_short);

        ArrayList<TvShow> tvshow = new ArrayList<>();
        for(int i=0;i<dataTvshow.length;i++){
            TvShow tvShow = new TvShow();
            tvShow.setPosterCover(dataTvshowCover.getResourceId(i,-1));
            tvShow.setPosterName(dataTvshow[i]);
            tvShow.setPosterCategory(dataTvshowCategory[i]);
            tvShow.setPosterDuration(dataTvshowDuration[i]);
            tvShow.setPosterDescription(dataTvshowDescription[i]);
            tvShow.setPosterRating(dataTvshowRating[i]);
            tvShow.setPosterReleaseDate(datareleasedate[i]);
            tvShow.setPosterReleaseDateLong(dataTvshowReleaseDateLong[i]);
            tvShow.setPosterDirector(dataTvshowDirector[i]);
            tvShow.setCategory(category[i]);
            tvshow.add(tvShow);
        }
        TvShowAdapter tvShowAdapter = new TvShowAdapter(context, tvshow);
        tvShowAdapter.setOnItemClickListener(new TvShowAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, TvShow tvShow, int i) {
                TvShow tvshow = new TvShow();
                tvshow.setPosterCover(dataTvshowCover.getResourceId(i,-1));
                tvshow.setPosterName(dataTvshow[i]);
                tvshow.setPosterCategory(dataTvshowCategory[i]);
                tvshow.setPosterDuration(dataTvshowDuration[i]);
                tvshow.setPosterDescription(dataTvshowDescription[i]);
                tvshow.setPosterRating(dataTvshowRating[i]);
                tvshow.setPosterReleaseDateLong(dataTvshowReleaseDateLong[i]);
                tvshow.setPosterDirector(dataTvshowDirector[i]);
                tvshow.setCategory(category[i]);
                Intent detailMovie = new Intent(getActivity(), DetailMovieActivity.class);
                detailMovie.putExtra(DetailMovieActivity.extraTvShow, tvshow);
                detailMovie.putExtra(DetailMovieActivity.extraCategory,categoryIntent);
                startActivity(detailMovie);
            }
        });
        recyclerView.setAdapter(tvShowAdapter);
    }

}
