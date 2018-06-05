package com.example.dnghwls7.conn;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StatusFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StatusFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatusFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView setText1;TextView setText2;TextView setText3;TextView setText4;
    TextView setText5;TextView setText6;TextView setText7;TextView setText8;TextView setText9;
    TextView setText10;TextView setText11;

    private OnFragmentInteractionListener mListener;

    public StatusFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StatusFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StatusFragment newInstance(String param1, String param2) {
        StatusFragment fragment = new StatusFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    Timer statusTimer;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_status, container, false);
        setText1 = v.findViewById(R.id.settext1);
        setText2 = v.findViewById(R.id.settext2);
        setText3 = v.findViewById(R.id.settext3);
        setText4 = v.findViewById(R.id.settext4);
        setText5 = v.findViewById(R.id.settext5);
        setText6 = v.findViewById(R.id.settext6);
        setText7 = v.findViewById(R.id.settext7);
        setText8 = v.findViewById(R.id.settext8);
        setText9 = v.findViewById(R.id.settext9);
        setText10 = v.findViewById(R.id.settext10);
        setText11 = v.findViewById(R.id.settext11);
        int[] duration = new int[11];
        for(int i=0;i<11;i++) {
            duration[i] = (int) (Math.random() * 2000 + 1000);
        }

        v.postDelayed(new Runnable() {
            @Override
            public void run() {
                setText1.setVisibility(View.VISIBLE);
            }
        },duration[0]);
        v.postDelayed(new Runnable() {
            @Override
            public void run() {
                setText2.setVisibility(View.VISIBLE);
            }
        },duration[1]);
        v.postDelayed(new Runnable() {
            @Override
            public void run() {
                setText3.setVisibility(View.VISIBLE);
            }
        },duration[2]);
        v.postDelayed(new Runnable() {
            @Override
            public void run() {
                setText4.setVisibility(View.VISIBLE);
            }
        },duration[3]);
        v.postDelayed(new Runnable() {
            @Override
            public void run() {
                setText5.setVisibility(View.VISIBLE);
            }
        },duration[4]);
        v.postDelayed(new Runnable() {
            @Override
            public void run() {
                setText5.setVisibility(View.VISIBLE);
            }
        },duration[4]);
        v.postDelayed(new Runnable() {
            @Override
            public void run() {
                setText6.setVisibility(View.VISIBLE);
            }
        },duration[5]);
        v.postDelayed(new Runnable() {
            @Override
            public void run() {
                setText7.setVisibility(View.VISIBLE);
            }
        },duration[6]);
        v.postDelayed(new Runnable() {
            @Override
            public void run() {
                setText8.setVisibility(View.VISIBLE);
            }
        },duration[7]);
        v.postDelayed(new Runnable() {
            @Override
            public void run() {
                setText9.setVisibility(View.VISIBLE);
            }
        },duration[8]);
        v.postDelayed(new Runnable() {
            @Override
            public void run() {
                setText10.setVisibility(View.VISIBLE);
            }
        },duration[9]);
        v.postDelayed(new Runnable() {
            @Override
            public void run() {
                setText11.setVisibility(View.VISIBLE);
            }
        },duration[10]);


        return v;
    }
    final Handler handler = new Handler(){
        public void handleMessage(TextView textView){
            textView.setText(View.VISIBLE);
        }
    };

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
