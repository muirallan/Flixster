package com.example.flixster.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixster.Modles.Movie;
import com.example.flixster.R;

import java.util.List;

import static android.view.LayoutInflater.from;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    Context context;
    List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }


    //usually involves inflating the layout from the xml and return the holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d ( "MovieAdapter","onCreateViewHolder" );
       View movieView =  LayoutInflater.from( context ).inflate ( R.layout.item_movie, parent,false );
        return new ViewHolder ( movieView );
    }
  //involves populating data into the item through the holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d ( "MovieAdapter", "onCreateViewHolder" + position);
        //get the movie passed in position
        Movie movie = movies.get ( position );
        //bind the movie data in the View Holder
          holder.bind(movie);
    }
//return the total count of items in the list
    @Override
    public int getItemCount() {
        return movies.size ();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
   TextView tvTitle;
   TextView tvOverview;
   ImageView ivPoster;
        public ViewHolder(@NonNull View itemView) {
            super ( itemView );
            tvTitle = itemView.findViewById ( R.id.tvTitle);
            tvOverview = itemView.findViewById ( R.id.tvOverview);
             ivPoster = itemView.findViewById ( R.id.ivPoster);
        }

        public void bind(Movie movie) {
            tvTitle.setText ( movie.getTitle () );
            tvOverview.setText ( movie.getOverview () );
            String imageUrl;
            // if phone is in landscape
            if (context.getResources ().getConfiguration ().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                //then imageUrl = backdrop image
                imageUrl = movie.getBackdropPath ( );
            } else {
                //else imageUrl - image
                imageUrl = movie.getPosterPath ( );
            }
            Glide.with ( context ).load(imageUrl ).into ( ivPoster );
        }
    }
}
