package com.mad.myfavoritemoviesfragment;

import android.app.Activity;
import android.content.Context;
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

import java.io.Serializable;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddMovieFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class AddMovieFragment extends Fragment {

    EditText et;
    TextView tv;
    Activity mainActivity;

    private OnFragmentInteractionListener mListener;

    public AddMovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_movie, container, false);
    }


    public void onButtonPressed(Movie movie) {
        if (mListener != null) {
            mListener.sendMovie(movie);
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
        void sendMovie(Movie movie);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SeekBar sb = (SeekBar) getView().findViewById(R.id.seekBarRating);
        tv = (TextView)getView().findViewById(R.id.textViewRatingOutput);
        tv.setText(sb.getProgress()+"");
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                tv = (TextView) getView().findViewById(R.id.textViewRatingOutput);
                tv.setText(progress+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });

        getView().findViewById(R.id.buttonMovieAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Movie movie= new Movie();

                et = (EditText) getView().findViewById(R.id.editTextName);
                movie.setName(et.getText().toString().trim());

                et = (EditText) getView().findViewById(R.id.editTextDescription);
                movie.setDescription(et.getText().toString().trim());

                et = (EditText) getView().findViewById(R.id.editTextYear);
                String temp= et.getText().toString().trim();
                if(temp.equals("")||temp==null){
                    movie.setYear(0);
                }else{
                    movie.setYear(Integer.parseInt(temp));

                }

                et = (EditText) getView().findViewById(R.id.editTextIMDBLink);
                movie.setImdbLink(et.getText().toString().trim());

                tv = (TextView) getView().findViewById(R.id.textViewRatingOutput);
                temp= tv.getText().toString().trim();
                movie.setRating(Integer.parseInt(temp));

                Spinner mySpinner=(Spinner) getView().findViewById(R.id.spinnerGenre);
                temp= mySpinner.getSelectedItem().toString();
                movie.setGenre(temp);

                if(movie.getName().equals("") || movie.getName().length()>50){
                    Toast.makeText(mainActivity,getResources().getString(R.string.error_name),Toast.LENGTH_SHORT).show();
                }else if(movie.getDescription().equals("") || movie.getDescription().length()>1000){
                    Toast.makeText(mainActivity,getResources().getString(R.string.error_description),Toast.LENGTH_SHORT).show();
                }else if(movie.getYear()<1800 || movie.getYear()>2030){
                    Toast.makeText(mainActivity,getResources().getString(R.string.error_year),Toast.LENGTH_SHORT).show();
                }else if(movie.getImdbLink().equals("")){
                    Toast.makeText(mainActivity,getResources().getString(R.string.error_imdblink),Toast.LENGTH_SHORT).show();
                }else{
                    onButtonPressed(movie);
                }
            }
        });

    }
}
