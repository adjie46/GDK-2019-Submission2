package com.adjiekurniawan.sumbission2_dicoding.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.adjiekurniawan.sumbission2_dicoding.Models.TvShow;
import com.adjiekurniawan.sumbission2_dicoding.R;

import java.util.ArrayList;

public class TvShowAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected Context context;
    private ArrayList<TvShow> tvShows;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, TvShow tvShow, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.onItemClickListener = mItemClickListener;
    }

    public TvShowAdapter(Context ctx, ArrayList<TvShow> items) {
        tvShows = items;
        context = ctx;
    }

    private class OriginalViewHolder extends RecyclerView.ViewHolder {
        private ImageView poster_image;
        private TextView poster_title,
                poster_duration,
                poster_category,
                poster_description;
        private RatingBar poster_rating;
        private CardView cardView;


        OriginalViewHolder(View view) {
            super(view);
            poster_image = view.findViewById(R.id.posterImage);
            poster_title = view.findViewById(R.id.posterTitle);
            poster_duration = view.findViewById(R.id.posterDuration);
            poster_category = view.findViewById(R.id.posterCategoty);
            poster_description = view.findViewById(R.id.posterDescription);
            poster_rating = view.findViewById(R.id.rating);
            cardView = view.findViewById(R.id.cardView);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder viewHolder;
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_tvshow, viewGroup, false);
        viewHolder = new OriginalViewHolder(view);
        return viewHolder;
    }

    @Override
    @SuppressLint("RecyclerView")
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        if (viewHolder instanceof OriginalViewHolder) {
            OriginalViewHolder view = (OriginalViewHolder) viewHolder;
            TvShow tvShow = tvShows.get(i);

            view.poster_image.setImageResource(tvShow.getPosterCover());
            view.poster_title.setText(String.format("%s %s", tvShow.getPosterName(), tvShow.getPosterReleaseDate()));
            view.poster_duration.setText(tvShow.getPosterDuration());
            view.poster_category.setText(tvShow.getPosterCategory());
            view.poster_description.setText(tvShow.getPosterDescription());
            view.poster_rating.setRating(Float.parseFloat(tvShow.getPosterRating()));

            view.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(v, tvShows.get(i), i);
                    }
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return tvShows.size();
    }
}
