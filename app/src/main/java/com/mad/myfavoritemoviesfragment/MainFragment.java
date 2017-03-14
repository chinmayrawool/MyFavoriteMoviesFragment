package com.mad.myfavoritemoviesfragment;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    ArrayList<Movie> movieArrayList;
    String[] movieNames;
    int movieIndex;
    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
    OnFragmentInterface mListener;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("demo","AFragment: onAttach");
        try{
            mListener = (OnFragmentInterface) context;

        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()+"Should implement OnFragmentTextChange");
        }

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("demo","AFragment: onActivityCreated");
        movieArrayList = new ArrayList<Movie>();
        getView().findViewById(R.id.buttonAddMovie).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.gotoAddMovie();
            }
        });
        getView().findViewById(R.id.buttonEdit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movieArrayList= mListener.list();

                if(movieArrayList.size()!=0) {
                    movieNames = getMovieNames();
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Pick a movie")
                            .setItems(movieNames, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    movieIndex = which;

                                    Movie movie = movieArrayList.get(which);
                                    //movieArrayList.remove(movieIndex);
                                    mListener.updateList(movieArrayList);
                                    mListener.gotoEditMovie(movie);
                                }
                            });

                    AlertDialog deleteAlert = builder.create();
                    deleteAlert.show();
                }
                else{
                    Toast.makeText(getActivity(),"No movies found!",Toast.LENGTH_LONG).show();
                }
            }
        });
        getView().findViewById(R.id.buttonDeleteMovie).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movieArrayList= mListener.list();
                movieNames = getMovieNames();
                if(movieArrayList.size()!=0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Pick a movie")
                            .setItems(movieNames, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String name=movieArrayList.get(which).getName();
                                    movieArrayList.remove(which);
                                    Toast.makeText(getActivity(), "Movie " + name + " was deleted", Toast.LENGTH_LONG).show();
                                    movieNames = new String[movieArrayList.size()];
                                    mListener.updateList(movieArrayList);
                                }
                            });

                    AlertDialog deleteAlert = builder.create();
                    deleteAlert.show();
                }
                else{
                    Toast.makeText(getActivity(),"No movies found!",Toast.LENGTH_LONG).show();
                }
            }
        });
        getView().findViewById(R.id.buttonByYear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //movieArrayList= mListener.list();
                movieArrayList = mListener.list();
                if(movieArrayList.size()>0) {
                    mListener.gotoYearFragment();
                }else{
                    Toast.makeText(getActivity(),"No movies found!",Toast.LENGTH_LONG).show();
                }
            }
        });
        getView().findViewById(R.id.buttonByRating).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //movieArrayList= mListener.list();

                movieArrayList = mListener.list();
                if(movieArrayList.size()>0) {
                    mListener.gotoRatingFragment();
                }else{
                    Toast.makeText(getActivity(),"No movies found!",Toast.LENGTH_LONG).show();
                }
            }
        });

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
        void gotoAddMovie();
        void gotoEditMovie(Movie movie);
        public ArrayList<Movie> list();
        public void updateList(ArrayList<Movie> movies);
        public void gotoRatingFragment();
        public void gotoYearFragment();
    }

    public String[] getMovieNames(){
        movieNames = new String[movieArrayList.size()];
        for (int i = 0; i < movieArrayList.size(); i++) {
            movieNames[i] = movieArrayList.get(i).getName();
        }
        return movieNames;
    }

}
