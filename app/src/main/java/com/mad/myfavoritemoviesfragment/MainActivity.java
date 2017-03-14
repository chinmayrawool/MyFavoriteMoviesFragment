package com.mad.myfavoritemoviesfragment;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainFragment.OnFragmentInterface,AddMovieFragment.OnFragmentInteractionListener,YearFragment.OnFragmentInterface,EditMovieFragment.OnFragmentInteractionListener{
    ArrayList<Movie> movieArrayList;
    Movie movie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movieArrayList = new ArrayList<Movie>();
        for(int i=0;i<=2;i++){
            Movie m =new Movie("name"+i,"description"+i,"link"+i,"Action",2000+i,1+i);
            movieArrayList.add(m);
            //movieNames = new String[movieArrayList.size()];
        }
        getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_main,new MainFragment(),"tag_mainfrag")
                .commit();
        getSupportActionBar().setTitle("My Favorite Movies");

    }


    @Override
    public void gotoAddMovie() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_main,new AddMovieFragment(),"tag_addfrag")
                .addToBackStack(null)
                .commit();
        getSupportActionBar().setTitle(R.string.add_movie);
    }
    @Override
    public void gotoEditMovie(Movie movie) {
        this.movie=movie;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_main,new EditMovieFragment(),"tag_editfrag")
                .addToBackStack(null)
                .commit();
        getSupportActionBar().setTitle("Edit Movie");



    }

    @Override
    public ArrayList<Movie> list() {
        return movieArrayList;
    }

    @Override
    public void gotoMovie() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_main,new MainFragment(),"tag_mainfrag")
                .addToBackStack(null)
                .commit();
        getSupportActionBar().setTitle("My Favorite Movies");
    }


    @Override
    public void updateList(ArrayList<Movie> movies) {
        movieArrayList = movies;
    }

    @Override
    public void gotoRatingFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_main,new RatingFragment(),"tag_ratingfrag")
                .addToBackStack(null)
                .commit();
        getSupportActionBar().setTitle("Movies by Rating");
    }

    @Override
    public void gotoYearFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_main,new YearFragment(),"tag_yearfrag")
                .addToBackStack(null)
                .commit();
        getSupportActionBar().setTitle("Movies by Year");
    }

    @Override
    public void sendMovie(Movie movie) {
        movieArrayList.add(movie);
        Log.d("demo",movie.toString());
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_main,new MainFragment(),"tag_mainfrag")
                .addToBackStack(null)
                .commit();
        getSupportActionBar().setTitle("My Favorite Movies");
    }

    @Override
    public Movie getMovie() {
        return movie;
    }

    @Override
    public void addmovie(Movie new1, Movie old1) {
        movieArrayList.remove(old1);
        movieArrayList.add(new1);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_main,new MainFragment(),"tag_mainfrag")
                .addToBackStack(null)
                .commit();
        getSupportActionBar().setTitle("My Favorite Movies");
    }

    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount() > 0){
            getSupportFragmentManager().popBackStack();
            getSupportActionBar().setTitle("My Favorite Movies");
        }else {
            super.onBackPressed();
        }
    }

}
