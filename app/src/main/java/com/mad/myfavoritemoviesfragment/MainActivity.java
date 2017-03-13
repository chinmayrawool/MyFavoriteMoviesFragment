package com.mad.myfavoritemoviesfragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements MainFragment.OnFragmentInterface{

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
                .replace(R.id.activity_main,new MainFragment(),"tag_addfrag")
                .addToBackStack(null)
                .commit();
    }
}
