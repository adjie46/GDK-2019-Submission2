package com.adjiekurniawan.sumbission2_dicoding.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.adjiekurniawan.sumbission2_dicoding.Adapters.MovieAdapter;
import com.adjiekurniawan.sumbission2_dicoding.Adapters.TvShowAdapter;
import com.adjiekurniawan.sumbission2_dicoding.Models.Movies;
import com.adjiekurniawan.sumbission2_dicoding.Models.TvShow;
import com.adjiekurniawan.sumbission2_dicoding.R;

import java.util.ArrayList;
import java.util.Objects;

public class DetailMovieActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String extraMovie = "extraMovie";
    public static final String extraTvShow = "extraTvShow";
    public static final String extraCategory = "category";
    private TextView posterTitle,
            posterDuration,
            posterCategory,
            posterReleaseDate,
            posterDirector,
            posterRatingText,
            posterDescription;
    private RatingBar posterRating;
    private ImageView posterImage,posterCover;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        initToolbar();
    }

    private void initToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.back_button);
        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).setTitle(null);
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        initView();
        initData();
    }

    private void initView(){

        recyclerView = findViewById(R.id.dataMovie);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void initDataOther(String category){
        if (category.equals("Movie")){
            otherDataMovie();
        }else{
            otherDataTvShow();
        }
    }

    @SuppressLint("Recycle")
    private void otherDataMovie(){
        TypedArray dataCover = getResources().obtainTypedArray(R.array.data_cover);
        String[] dataMovie = getResources().getStringArray(R.array.data_movie);
        String[] dataDuration = getResources().getStringArray(R.array.data_duration);
        String[] dataCategory = getResources().getStringArray(R.array.data_category);
        String[] dataDescription = getResources().getStringArray(R.array.data_description);
        String[] dataRating = getResources().getStringArray(R.array.data_rating);
        String[] datareleasedate = getResources().getStringArray(R.array.data_release_date_short);
        String[] dataReleaseDateLong = getResources().getStringArray(R.array.data_release_date_long);
        String[] dataDirector = getResources().getStringArray(R.array.data_director);

        ArrayList<Movies> movies = new ArrayList<>();
        for(int i = 0; i< dataMovie.length; i++){
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
            movies.add(movie);
        }

        MovieAdapter moviesAdapter = new MovieAdapter(this, movies);
        moviesAdapter.setOnItemClickListener(new MovieAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Movies movies, int i) {
                posterImage.setImageResource(movies.getPosterCover());
                posterCover.setImageResource(movies.getPosterCover());
                posterDuration.setText(String.format("%s %s", getString(R.string.txt_detail_duration), movies.getPosterDuration()));
                posterCategory.setText(String.format("%s %s", getString(R.string.txt_detail_category), movies.getPosterCategory()));
                posterRating.setRating(Float.parseFloat(movies.getPosterRating()));
                posterRatingText.setText(movies.getPosterRating());
                posterReleaseDate.setText(String.format("%s %s", getString(R.string.txt_detail_release_date), movies.getPosterReleaseDateLong()));
                posterDirector.setText(String.format("%s %s", getString(R.string.txt_detail_director), movies.getPosterDirector()));
                posterDescription.setText(movies.getPosterDescription());
                posterTitle.setText(movies.getPosterName());
            }
        });
        recyclerView.setAdapter(moviesAdapter);
    }

    @SuppressLint("Recycle")
    private void otherDataTvShow(){
        TypedArray dataTvshowCover = getResources().obtainTypedArray(R.array.data_cover_tvshow);
        String[] dataTvshow = getResources().getStringArray(R.array.data_tvshow);
        String[] dataTvshowDuration = getResources().getStringArray(R.array.data_tvshow_duration);
        String[] dataTvshowCategory = getResources().getStringArray(R.array.data_tvshow_category);
        String[] dataTvshowDescription = getResources().getStringArray(R.array.data_tvshow_description);
        String[] dataTvshowRating = getResources().getStringArray(R.array.data_tvshow_rating);
        String[] dataTvshowReleaseDateLong = getResources().getStringArray(R.array.data__tvshow_release_date_long);
        String[] dataTvshowDirector = getResources().getStringArray(R.array.data_tvshow_director);
        String[] category = getResources().getStringArray(R.array.category_tvshow);
        String[] datareleasedate = getResources().getStringArray(R.array.data__tvshow_release_date_short);

        ArrayList<TvShow> tvshow = new ArrayList<>();
        for(int i = 0; i< dataTvshow.length; i++){
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

        TvShowAdapter tvShowAdapter = new TvShowAdapter(this, tvshow);
        tvShowAdapter.setOnItemClickListener(new TvShowAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, TvShow tvShow, int position) {
                posterImage.setImageResource(tvShow.getPosterCover());
                posterCover.setImageResource(tvShow.getPosterCover());
                posterDuration.setText(String.format("%s %s", getString(R.string.txt_detail_duration), tvShow.getPosterDuration()));
                posterCategory.setText(String.format("%s %s", getString(R.string.txt_detail_category), tvShow.getPosterCategory()));
                posterRating.setRating(Float.parseFloat(tvShow.getPosterRating()));
                posterRatingText.setText(tvShow.getPosterRating());
                posterReleaseDate.setText(String.format("%s %s", getString(R.string.txt_detail_release_date), tvShow.getPosterReleaseDateLong()));
                posterDirector.setText(String.format("%s %s", getString(R.string.txt_detail_director), tvShow.getPosterDirector()));
                posterDescription.setText(tvShow.getPosterDescription());
                posterTitle.setText(tvShow.getPosterName());
            }
        });
        recyclerView.setAdapter(tvShowAdapter);
    }

    @SuppressLint("SetTextI18n")
    private void initData(){

        posterImage = findViewById(R.id.posterImage);
        posterCover = findViewById(R.id.posterCover);
        posterDuration = findViewById(R.id.posterDuration);
        posterCategory = findViewById(R.id.posterCategoty);
        posterReleaseDate = findViewById(R.id.posterReleaseData);
        posterDirector = findViewById(R.id.posterDirector);
        posterRating = findViewById(R.id.rating);
        posterRatingText = findViewById(R.id.posterRatingText);
        Button btnPosterLike = findViewById(R.id.btnPosterLike);
        posterDescription = findViewById(R.id.posterDescription);
        posterTitle = findViewById(R.id.posterTitle);

        String category = getIntent().getStringExtra("category");

        if (category.equals("tvshow")){
            TvShow dataIntent = getIntent().getParcelableExtra(extraTvShow);
            posterImage.setImageResource(dataIntent.getPosterCover());
            posterCover.setImageResource(dataIntent.getPosterCover());
            posterDuration.setText(String.format("%s %s", getString(R.string.txt_detail_duration), dataIntent.getPosterDuration()));
            posterCategory.setText(String.format("%s %s", getString(R.string.txt_detail_category), dataIntent.getPosterCategory()));
            posterRating.setRating(Float.parseFloat(dataIntent.getPosterRating()));
            posterRatingText.setText(dataIntent.getPosterRating());
            posterReleaseDate.setText(getString(R.string.txt_detail_release_date) + " " + dataIntent.getPosterReleaseDateLong());
            posterDirector.setText(getString(R.string.txt_detail_director) + " " + dataIntent.getPosterDirector());
            posterDescription.setText(dataIntent.getPosterDescription());
            posterTitle.setText(dataIntent.getPosterName());
            btnPosterLike.setOnClickListener(this);
            initDataOther(dataIntent.getCategory());
        }else{
            Movies dataIntent = getIntent().getParcelableExtra(extraMovie);
            posterImage.setImageResource(dataIntent.getPosterCover());
            posterCover.setImageResource(dataIntent.getPosterCover());
            posterDuration.setText(String.format("%s %s", getString(R.string.txt_detail_duration), dataIntent.getPosterDuration()));
            posterCategory.setText(String.format("%s %s", getString(R.string.txt_detail_category), dataIntent.getPosterCategory()));
            posterRating.setRating(Float.parseFloat(dataIntent.getPosterRating()));
            posterRatingText.setText(dataIntent.getPosterRating());
            posterReleaseDate.setText(getString(R.string.txt_detail_release_date) + " " + dataIntent.getPosterReleaseDateLong());
            posterDirector.setText(getString(R.string.txt_detail_director) + " " + dataIntent.getPosterDirector());
            posterDescription.setText(dataIntent.getPosterDescription());
            posterTitle.setText(dataIntent.getPosterName());
            btnPosterLike.setOnClickListener(this);
            initDataOther(dataIntent.getCategory());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = String.format("%s\n%s\n%s", posterTitle.getText().toString(),
                    posterDescription.getText().toString(), getString(R.string.txt_detail_shared));
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, R.string.txt_detail_subject);
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, getString(R.string.txt_detail_title)));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnPosterLike) {
            Toast.makeText(this, String.format("%s %s", getString(R.string.txt_detail_clicked), posterTitle.getText().toString()), Toast.LENGTH_SHORT).show();
        }
    }
}
