package com.mad.myfavoritemoviesfragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EditMovieFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class EditMovieFragment extends Fragment {
    EditText editText;
    TextView textViewRating;
    Spinner genre;
    SeekBar rating;
    Activity mainActivity;

    private OnFragmentInteractionListener mListener;

    public EditMovieFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_movie, container, false);
    }

    public void onButtonPressed(Movie movie) {
        if (mListener != null) {
            //mListener.sendMovie(movie);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        mainActivity = (Activity)context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        //void sendMovie(Movie movie);
        Movie getMovie();
        void addmovie(Movie new1,Movie old1);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Movie movie = new Movie();
        movie = mListener.getMovie();
        final Movie movieOld = movie;
        editText = (EditText) getView().findViewById(R.id.editTextName);
        editText.setText(movie.getName());
        editText = (EditText) getView().findViewById(R.id.editTextDescription);
        editText.setText(movie.getDescription());
        editText = (EditText) getView().findViewById(R.id.editTextYear);
        editText.setText(String.valueOf(movie.getYear()));
        editText = (EditText) getView().findViewById(R.id.editTextIMDBLink);
        editText.setText(movie.getImdbLink());
        genre = (Spinner) getView().findViewById(R.id.spinnerGenre);
        String movieGenre = movie.getGenre();
        switch(movieGenre){
            case "Action": genre.setSelection(0);
                break;
            case "Animation": genre.setSelection(1);
                break;
            case "Comedy": genre.setSelection(2);
                break;
            case "Documentary": genre.setSelection(3);
                break;
            case "Family": genre.setSelection(4);
                break;
            case "Horror": genre.setSelection(5);
                break;
            case "Crime": genre.setSelection(6);
                break;
            case "Others": genre.setSelection(7);
                break;
        }

        rating = (SeekBar) getView().findViewById(R.id.seekBarRating);
        rating.setProgress(movie.getRating());
        textViewRating = (TextView) getView().findViewById(R.id.textViewRatingOutput);
        textViewRating.setText(String.valueOf(movie.getRating()));

        rating.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBar.setProgress(progress);
                textViewRating = (TextView) getView().findViewById(R.id.textViewRatingOutput);
                textViewRating.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        getView().findViewById(R.id.buttonMovieSaveChanges).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Movie movie = new Movie();
                editText = (EditText) getView().findViewById(R.id.editTextName);
                movie.setName(editText.getText().toString());

                editText = (EditText) getView().findViewById(R.id.editTextDescription);
                movie.setDescription(editText.getText().toString());

                editText = (EditText) getView().findViewById(R.id.editTextName);
                movie.setName(editText.getText().toString());

                editText = (EditText) getView().findViewById(R.id.editTextYear);
                movie.setYear(Integer.parseInt(editText.getText().toString()));

                rating = (SeekBar) getView().findViewById(R.id.seekBarRating);
                movie.setRating(rating.getProgress());

                editText = (EditText) getView().findViewById(R.id.editTextIMDBLink);
                movie.setImdbLink(editText.getText().toString());

                movie.setGenre(genre.getSelectedItem().toString());

                if(movie.getName().equals("")){
                    Toast.makeText(mainActivity,getResources().getString(R.string.error_name),Toast.LENGTH_SHORT).show();
                }else if(movie.getName().length()>50) {
                    Toast.makeText(mainActivity,getResources().getString(R.string.error_movie_length),Toast.LENGTH_SHORT).show();
                }else if(movie.getDescription().equals("")){
                    Toast.makeText(mainActivity,getResources().getString(R.string.error_description),Toast.LENGTH_SHORT).show();
                }else if (movie.getDescription().length()>1000) {
                    Toast.makeText(mainActivity,getResources().getString(R.string.error_description_length),Toast.LENGTH_SHORT).show();
                }else if(movie.getYear()<1800 || movie.getYear()>2050){
                    Toast.makeText(mainActivity,getResources().getString(R.string.error_year),Toast.LENGTH_SHORT).show();
                }else if(movie.getImdbLink().equals("")){
                    Toast.makeText(mainActivity,getResources().getString(R.string.error_imdblink),Toast.LENGTH_SHORT).show();
                }else{
                    onButtonPressed(movie);
                    Toast.makeText(mainActivity,movie.getName() + " changed",Toast.LENGTH_SHORT).show();
                    mListener.addmovie(movie,movieOld);
                }
            }
        });
    }


}
