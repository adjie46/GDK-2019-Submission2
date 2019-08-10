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
import com.adjiekurniawan.sumbission2_dicoding.Adapters.MovieAdapter;
import com.adjiekurniawan.sumbission2_dicoding.Models.Movies;
import com.adjiekurniawan.sumbission2_dicoding.R;

import java.util.ArrayList;

public class FragmentMovie extends Fragment {

    private View rootView;
    private RecyclerView recyclerView;
    private String[] dataMovie;
    private String[] dataDuration;
    private String[] dataCategory;
    private String[] category;
    private String[] dataDescription;
    private String[] dataRating;
    private String[] dataReleaseDateLong;
    private String[] dataDirector;
    private TypedArray dataCover;
    private Context context;
    private String categoryIntent = "movie";


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_movie, container, false);
        context = getContext();
        initView();
        return rootView;
    }

    private void initView(){
        recyclerView = rootView.findViewById(R.id.dataMovie);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        initData();
    }



    private void initData(){
        dataCover = getResources().obtainTypedArray(R.array.data_cover);
        dataMovie = getResources().getStringArray(R.array.data_movie);
        dataDuration = getResources().getStringArray(R.array.data_duration);
        dataCategory = getResources().getStringArray(R.array.data_category);
        dataDescription = getResources().getStringArray(R.array.data_description);
        dataRating = getResources().getStringArray(R.array.data_rating);
        category = getResources().getStringArray(R.array.category_movie);
        dataReleaseDateLong = getResources().getStringArray(R.array.data_release_date_long);
        dataDirector = getResources().getStringArray(R.array.data_director);
        String[] datareleasedate = getResources().getStringArray(R.array.data_release_date_short);

        ArrayList<Movies> movies = new ArrayList<>();
        for(int i=0;i<dataMovie.length;i++){
            Movies movie = new Movies();
            movie.setPosterCover(dataCover.getResourceId(i,-1));
            movie.setPosterName(dataMovie[i]);
            movie.setPosterCategory(dataCategory[i]);
            movie.setPosterDuration(dataDuration[i]);
            movie.setPosterDescription(dataDescription[i]);
            movie.setPosterRating(dataRating[i]);
            movie.setPosterReleaseDate(datareleasedate[i]);
            movie.setPosterReleaseDateLong(dataReleaseDateLong[i]);
            movie.setPosterDirector(dataDirector[i]);
            movie.setCategory(category[i]);
            movies.add(movie);
        }
        MovieAdapter moviesAdapter = new MovieAdapter(context, movies);
        moviesAdapter.setOnItemClickListener(new MovieAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Movies movies, int i) {
                Movies movie = new Movies();
                movie.setPosterCover(dataCover.getResourceId(i,-1));
                movie.setPosterName(dataMovie[i]);
                movie.setPosterCategory(dataCategory[i]);
                movie.setPosterDuration(dataDuration[i]);
                movie.setPosterDescription(dataDescription[i]);
                movie.setPosterRating(dataRating[i]);
                movie.setPosterReleaseDateLong(dataReleaseDateLong[i]);
                movie.setPosterDirector(dataDirector[i]);
                movie.setCategory(category[i]);
                Intent detailMovie = new Intent(getActivity(), DetailMovieActivity.class);
                detailMovie.putExtra(DetailMovieActivity.extraMovie, movie);
                detailMovie.putExtra(DetailMovieActivity.extraCategory,categoryIntent);
                startActivity(detailMovie);
            }
        });
        recyclerView.setAdapter(moviesAdapter);
    }

}
