package com.mad.myfavoritemoviesfragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainFragment.OnFragmentInterface{
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
}
