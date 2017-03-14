package com.mad.myfavoritemoviesfragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;


/**
 * A simple {@link Fragment} subclass.
 */
public class RatingFragment extends Fragment {

    ArrayList<Movie> movieArrayList;
    String[] movieNames;
    int movieIndex;
    int i=0,size;
    public RatingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rating, container, false);
    }

    YearFragment.OnFragmentInterface mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("demo","AFragment: onAttach");
        try{
            mListener = (YearFragment.OnFragmentInterface)context;

        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()+"Should implement OnFragmentTextChange");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("demo","AFragment: onActivityCreated");
        movieArrayList = new ArrayList<Movie>();
        movieArrayList = mListener.list();

        Collections.sort(movieArrayList, Movie.MovieYear);
        size=movieArrayList.size()-1;
        displayYear(movieArrayList,i);

        Log.d("demo",i+"  size = "+size);
        ImageButton btn_first=(ImageButton)getView().findViewById(R.id.imageButtonFirst1);
        ImageButton btn_previous=(ImageButton)getView().findViewById(R.id.imageButtonPrevious1);
        ImageButton btn_next=(ImageButton)getView().findViewById(R.id.imageButtonNext1);
        ImageButton btn_last=(ImageButton)getView().findViewById(R.id.imageButtonLast1);
        Button btn_finish = (Button) getView().findViewById(R.id.buttonFinishRating1);
        btn_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (i == 0) {
                    Toast.makeText(getActivity(), "First entry already displayed", Toast.LENGTH_SHORT).show();
                } else {

                    i = 0;
                    //call function for display
                    //tv.setText(i+"");
                    displayYear(movieArrayList, i);
                }
            }
        });

        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i==0){
                    Toast.makeText(getActivity(), "No previous entry", Toast.LENGTH_SHORT).show();
                }else{
                    i--;
                    //tv.setText(i+"");
                    displayYear(movieArrayList,i);
                    //call function for display
                }
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i==size){
                    Toast.makeText(getActivity(), "Last entry. No more entry", Toast.LENGTH_SHORT).show();
                }else{
                    i++;
                    //call function for display
                    displayYear(movieArrayList,i);
                    //tv.setText(i+"");

                }
            }
        });

        btn_last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (i == size) {
                    Toast.makeText(getActivity(), "Last entry already displayed", Toast.LENGTH_SHORT).show();
                } else {

                    i = size;
                    //call function for display
                    //tv.setText(i+"");
                    displayYear(movieArrayList, i);
                }
            }
        });

        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.gotoMovie();
            }
        });
    }

    void displayYear(ArrayList<Movie> mov, int i){
        //display text on the screen

        TextView tv;
        tv = (TextView)getView().findViewById(R.id.textViewTitleOutput1);
        tv.setText(mov.get(i).getName());
        tv = (TextView)getView().findViewById(R.id.textViewDescriptionOutput1);
        tv.setText(mov.get(i).getDescription());
        tv = (TextView)getView().findViewById(R.id.textViewGenreOutput1);
        tv.setText(mov.get(i).getGenre());
        tv = (TextView)getView().findViewById(R.id.textViewRatingOutput1);
        tv.setText(String.valueOf(mov.get(i).getRating())+"/5");
        tv = (TextView)getView().findViewById(R.id.textViewYearOutput1);
        tv.setText(String.valueOf(mov.get(i).getYear()));
        tv = (TextView)getView().findViewById(R.id.textViewIMDBOutput1);
        tv.setText(mov.get(i).getImdbLink());
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("demo","AFragment: onDestroy");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("demo","AFragment: onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("demo","AFragment: onResume");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("demo","AFragment: onCreate");
    }

    public interface OnFragmentInterface{
        public ArrayList<Movie> list();
        void gotoMovie();
    }
}
