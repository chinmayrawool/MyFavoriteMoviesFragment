package com.mad.myfavoritemoviesfragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainFragment.OnFragmentInterface,AddMovieFragment.OnFragmentInteractionListener{
    ArrayList<Movie> movieArrayList = new ArrayList<Movie>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_main,new MainFragment(),"tag_mainfrag")
                .commit();

    }

    @Override
    public void OnTextChange(String text) {

    }

    @Override
    public void gotoAddMovie() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_main,new AddMovieFragment(),"tag_addfrag")
                .addToBackStack(null)
                .commit();
    }
    @Override
    public void gotoEditMovie(Movie movie) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_main,new EditMovieFragment(),"tag_editfrag")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public ArrayList<Movie> list() {
        return movieArrayList;
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
    }

    @Override
    public void gotoYearFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_main,new YearFragment(),"tag_yearfrag")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void sendMovie(Movie movie) {

    }
}
