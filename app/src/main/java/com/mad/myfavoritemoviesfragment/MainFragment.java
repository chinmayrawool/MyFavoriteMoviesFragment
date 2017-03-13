package com.mad.myfavoritemoviesfragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


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
        getView().findViewById(R.id.buttonAddMovie).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.gotoAddMovie();
            }
        });
        getView().findViewById(R.id.buttonEdit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        getView().findViewById(R.id.buttonDeleteMovie).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        getView().findViewById(R.id.buttonByYear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        getView().findViewById(R.id.buttonByRating).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
        void OnTextChange(String text);
        void gotoAddMovie();
    }

}
