package com.example.dnghwls7.conn;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.HandlerThread;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ToggleButton;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ControlFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ControlFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ControlFragment extends Fragment implements ChairFragment.OnFragmentInteractionListener, WindowFragment.OnFragmentInteractionListener, StatusFragment.OnFragmentInteractionListener{

    TextView text_temp;
    ImageView imageWiper;
    ImageView imageDoor;
    ImageView imageCar;
    ImageView imageEngine;
    ImageView imageWasher;
    ImageView imageHeater1;
    ImageView imageHeater2;
    ImageView imageColdAircon;
    ImageView imageHotAircon;
    ImageView imageWind;
    Button btn_washer;
    Button btn_wiperOff;
    Button btn_mist;
    ToggleButton btn_auto;
    ToggleButton btn_lo;
    ToggleButton btn_hi;


    AnimationDrawable animationDrawable;

    View view;

    private String id;
    private String data;

    private boolean is_heat = false;
    private boolean is_heatRear = false;
    private boolean is_hot = false;
    private boolean is_cold = false;

    Timer mTimer;
    TimerTask gotask;
    TimerTask backtask;
    boolean wiperFlag = false;
    ChairFragment chairFragment = new ChairFragment();
    WindowFragment windowFragment = new WindowFragment();
    StatusFragment statusFragment = new StatusFragment();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private CarMainActivity activity;

    public ControlFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param ID Parameter 1.
     * @param Data Parameter 2.
     * @return A new instance of fragment ControlFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ControlFragment newInstance(String ID, String Data) {
        ControlFragment fragment = new ControlFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, ID);
        args.putString(ARG_PARAM2, Data);
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

    int wind_num = 3;
    int[] wind = {R.drawable.wind1,R.drawable.wind2,R.drawable.wind3,R.drawable.wind4,
            R.drawable.wind5,R.drawable.wind6, R.drawable.wind7,R.drawable.wind8};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_control_main, container, false);
        //Text
        text_temp = (TextView)view.findViewById(R.id.text_temp);

        btn_washer = (Button)view.findViewById(R.id.btn_washer);

        imageWiper = (ImageView)view.findViewById(R.id.imageWiper);
        //imageWiper.animate().rotation(180).setDuration(50).withLayer();
        imageDoor = (ImageView)view.findViewById(R.id.imageDoor);
        imageCar = (ImageView)view.findViewById(R.id.imageCar);
        imageEngine = (ImageView)view.findViewById(R.id.imageEngine);
        imageWasher = (ImageView)view.findViewById(R.id.imageWasher);
        imageHeater1 = (ImageView)view.findViewById(R.id.img_Heater);
        imageHeater2 = (ImageView)view.findViewById(R.id.img_Heater2);
        imageColdAircon  = (ImageView)view.findViewById(R.id.img_coldAIRCON);
        imageHotAircon = (ImageView)view.findViewById(R.id.img_hotAIRCON);
        imageWind = (ImageView)view.findViewById(R.id.wind);
        btn_wiperOff = (Button)view.findViewById(R.id.btn_wiperOff);
        btn_mist = (Button)view.findViewById(R.id.btn_wiperMist);
        btn_auto = (ToggleButton)view.findViewById(R.id.btn_wiperAuto);
        btn_lo = (ToggleButton)view.findViewById(R.id.btn_wiperLo);
        btn_hi = (ToggleButton)view.findViewById(R.id.btn_wiperHi);



        //door on button
        view.findViewById(R.id.btn_DoorOn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.sendDatabyfragment("04000200","1000000000000001");

            }
        });
        //door off button
        view.findViewById(R.id.btn_DoorOff).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.sendDatabyfragment("04000200","1000000000000000");

            }
        });

        //Power off button
        view.findViewById(R.id.btn_powerOff).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.sendDatabyfragment("04000201","1000000000000000");

            }
        });

        //Power on button
        view.findViewById(R.id.btn_powerOn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.sendDatabyfragment("04000201","1000000000000001");

            }
        });

        view.findViewById(R.id.Tab1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.child_container, chairFragment);
                fragmentTransaction.commit();
                //Log.d("chairFragment","chair picture");
            }
        });

        view.findViewById(R.id.Tab2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.child_container, windowFragment);
                fragmentTransaction.commit();
            }
        });
        view.findViewById(R.id.Tab3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.child_container, statusFragment);
                fragmentTransaction.commit();
            }
        });

        //car Ligth button
        view.findViewById(R.id.btn_Light0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("04000202","1000000000000000");
                activity.sendDatabyfragment("04000202","1000000000000000");
            }
        });
        view.findViewById(R.id.btn_Light1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("04000202","1000000000000001");
                activity.sendDatabyfragment("04000202","1000000000000001");
            }
        });
        view.findViewById(R.id.btn_Light2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("04000202","1000000000000002");
                activity.sendDatabyfragment("04000202","1000000000000002");
            }
        });
        view.findViewById(R.id.btn_Light3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("04000202","1000000000000003");
                activity.sendDatabyfragment("04000202","1000000000000003");
            }
        });
        view.findViewById(R.id.btn_Light4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("04000202","1000000000000004");
                activity.sendDatabyfragment("04000202","1000000000000004");
            }
        });

        //wiper
        btn_wiperOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.sendDatabyfragment("04000203","1000000000000000");
                btn_mist.setActivated(false);
                btn_auto.setChecked(false);
                btn_lo.setChecked(false);
                btn_hi.setChecked(false);
                imageWiper.animate().rotation(0).withLayer();
                if(mTimer != null) {
                    mTimer.cancel();
                    mTimer = null;
                }
            }
        });
        btn_mist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.sendDatabyfragment("04000203","1000000000000001");
                btn_auto.setChecked(false);
                btn_lo.setChecked(false);
                btn_hi.setChecked(false);
                if(mTimer != null) {
                    mTimer.cancel();
                    mTimer = null;
                }
                mTimer = new Timer();
                //FIFO로 Queue를 통해 통제하는 것까지 진화가 필요...
                gotask = new TimerTask() {
                    @Override
                    public void run() {
                        imageWiper.animate().rotation(-180).setDuration(1000).withLayer();
                    }
                };
                backtask = new TimerTask() {
                    @Override
                    public void run() {
                        imageWiper.animate().rotation(180).setDuration(1000).withLayer();
                    }
                };
                mTimer.schedule(gotask, 0);
                mTimer.schedule(backtask, 1100);
            }
        });
        btn_auto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if(isChecked){
                    btn_auto.setBackgroundDrawable(getActivity().getDrawable(R.drawable.round_select));
                    activity.sendDatabyfragment("04000203","1000000000000002");
                    btn_lo.setChecked(false);
                    btn_hi.setChecked(false);
                    if(mTimer == null) {
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                mTimer = new Timer();
                                gotask = new TimerTask() {
                                    @Override
                                    public void run() {
                                        imageWiper.animate().rotation(-180).setDuration(1000).withLayer();
                                    }
                                };
                                backtask = new TimerTask() {
                                    @Override
                                    public void run() {
                                        imageWiper.animate().rotation(180).setDuration(1000).withLayer();
                                    }
                                };
                                mTimer.schedule(gotask, 0, 2150);
                                mTimer.schedule(backtask, 1100, 2150);
                            }
                        },4200);
                    }else{
                        btn_auto.setChecked(false);
                        mTimer.cancel();
                        mTimer = null;
                    }
                }else{
                    activity.sendDatabyfragment("04000203","1000000000000000");
                    btn_auto.setBackgroundDrawable(getActivity().getDrawable(R.drawable.roundbtn));
                    mTimer.cancel();
                    mTimer = null;
                }

            }
        });
        btn_lo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if(isChecked){
                    btn_lo.setBackgroundDrawable(getActivity().getDrawable(R.drawable.round_select));
                    activity.sendDatabyfragment("04000203","1000000000000003");
                    btn_auto.setChecked(false);
                    btn_hi.setChecked(false);
                    if(mTimer == null) {
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                mTimer = new Timer();
                                gotask = new TimerTask() {
                                    @Override
                                    public void run() {
                                        imageWiper.animate().rotation(-180).setDuration(2000).withLayer();
                                    }
                                };
                                backtask = new TimerTask() {
                                    @Override
                                    public void run() {
                                        imageWiper.animate().rotation(180).setDuration(2000).withLayer();
                                    }
                                };
                                mTimer.schedule(gotask, 0, 4150);
                                mTimer.schedule(backtask, 2100, 4150);
                            }
                        },2200);
                    }else{
                        btn_lo.setChecked(false);
                        mTimer.cancel();
                        mTimer = null;
                    }
                }else{
                    activity.sendDatabyfragment("04000203","1000000000000000");
                    btn_lo.setBackgroundDrawable(getActivity().getDrawable(R.drawable.roundbtn));
                    mTimer.cancel();
                    mTimer = null;
                }
            }
        });
        btn_hi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if(isChecked){
                    btn_hi.setBackgroundDrawable(getActivity().getDrawable(R.drawable.round_select));
                    activity.sendDatabyfragment("04000203","1000000000000004");
                    btn_auto.setChecked(false);
                    btn_lo.setChecked(false);
                    if(mTimer == null) {
                        new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {

                                mTimer = new Timer();
                                gotask = new TimerTask() {
                                    @Override
                                    public void run() {
                                        imageWiper.animate().rotation(-180).setDuration(500).withLayer();
                                    }
                                };
                                backtask = new TimerTask() {
                                    @Override
                                    public void run() {
                                        imageWiper.animate().rotation(180).setDuration(500).withLayer();
                                    }
                                };
                                mTimer.schedule(gotask, 0, 1150);
                                mTimer.schedule(backtask, 550, 1150);
                            }

                        },4200);
                    } else {
                        btn_hi.setChecked(false);
                        mTimer.cancel();
                        mTimer = null;
                    }
                } else {
                    activity.sendDatabyfragment("04000203", "1000000000000000");
                    btn_hi.setBackgroundDrawable(getActivity().getDrawable(R.drawable.roundbtn));
                    mTimer.cancel();
                    mTimer = null;
                }
            }
        });


        view.findViewById(R.id.btn_washer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.sendDatabyfragment("04000204", "1000000000000001");
                imageWasher.setVisibility(View.VISIBLE);
                animationDrawable = (AnimationDrawable)imageWasher.getDrawable();
                animationDrawable.start();
            }
        });

        view.findViewById(R.id.btn_bonnet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.sendDatabyfragment("04000205", "1000000000000001");
            }
        });

        view.findViewById(R.id.btn_trunk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.sendDatabyfragment("04000206", "1000000000000001");
            }
        });


        view.findViewById(R.id.btn_uptemp1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.sendDatabyfragment("04000207","2000000000000001");
            }
        });
        view.findViewById(R.id.btn_downtemp1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.sendDatabyfragment("04000207","2000000000000000");
            }
        });

        //front heater
        view.findViewById(R.id.img_Heater).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                is_heat = !is_heat;
                if(is_heat) {
                    activity.sendDatabyfragment("04000208","1000000000000001");
                }else{
                    activity.sendDatabyfragment("04000208","1000000000000000");
                }
            }
        });

        //rear heater
        view.findViewById(R.id.img_Heater2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                is_heatRear = !is_heatRear;
                if(is_heatRear) {
                    activity.sendDatabyfragment("04000209","1000000000000001");
                }else{
                    activity.sendDatabyfragment("04000209","1000000000000000");
                }
            }
        });
        //cold aircon
        view.findViewById(R.id.img_coldAIRCON).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                is_cold = !is_cold;
                if(is_cold) {
                    activity.sendDatabyfragment("0400020A","1000000000000001");
                    activity.sendDatabyfragment("0400020B","1000000000000000");
                }else{
                    activity.sendDatabyfragment("0400020A","1000000000000000");
                }
            }
        });
        //hot aircon
        view.findViewById(R.id.img_hotAIRCON).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                is_hot = !is_hot;
                if(is_hot) {
                    activity.sendDatabyfragment("0400020B","1000000000000001");
                    activity.sendDatabyfragment("0400020A","1000000000000000");
                }else{
                    activity.sendDatabyfragment("0400020B","1000000000000000");
                }
            }
        });

        view.findViewById(R.id.btn_uptemp2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.sendDatabyfragment("0400020C","1000000000000001");
                if(wind_num<7){
                    wind_num++;
                }
                imageWind.setImageDrawable(getActivity().getDrawable(wind[wind_num]));
            }
        });

        view.findViewById(R.id.btn_downtemp2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.sendDatabyfragment("0400020C","1000000000000000");
                if(wind_num>0){
                    wind_num--;
                }
                imageWind.setImageDrawable(getActivity().getDrawable(wind[wind_num]));
            }
        });



        return view;
}

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof CarMainActivity){
            activity = (CarMainActivity)context;
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        if(mTimer!=null) {
            mTimer.cancel();
        }
        mListener = null;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

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

    //set Text and handle data
    public void setMessage(String id, String data){
        this.id = id;
        this.data = data;
        //Door off
        if(id.equals("04000200") && data.equals("1000000000000000")){
            //Door off
            imageDoor.setImageDrawable(getActivity().getDrawable(R.drawable.door_lock));
        }else if(id.equals("04000200") && data.equals("1000000000000001")){
            //Door on
            imageDoor.setImageDrawable(getActivity().getDrawable(R.drawable.door_unlock));
        }

        if(id.equals("04000201") && data.equals("1000000000000000")){
            //engine off
            imageEngine.setImageDrawable(getActivity().getDrawable(R.drawable.engine_stop));
        }else if(id.equals("04000201") && data.equals("1000000000000001")){
            //engine on
            imageEngine.setImageDrawable(getActivity().getDrawable(R.drawable.engine_start));
        }
        //car light
        if(id.equals("04000202") && data.equals("1000000000000000")){
            imageCar.setImageDrawable(getActivity().getDrawable(R.drawable.car));
        }else if(id.equals("04000202") && data.equals("1000000000000001")){
            imageCar.setImageDrawable(getActivity().getDrawable(R.drawable.car_1));
        }else if(id.equals("04000202") && (data.equals("1000000000000002") || data.equals("1000000000000003") ||data.equals("1000000000000004"))){
            imageCar.setImageDrawable(getActivity().getDrawable(R.drawable.car_2));
        }
        //wiper
        if(id.equals("04000203") && data.equals("1000000000000000")){

        }else if(id.equals("04000203") && data.equals("1000000000000001")){

        }else if(id.equals("04000203") && data.equals("1000000000000002")){

        }else if(id.equals("04000203") && data.equals("1000000000000003")){

        }else if(id.equals("04000203") && data.equals("1000000000000004")){

        }
        //washer
        if(id.equals("04000204") && data.equals("1000000000000001")){
            //washer on
            //imageWasher.setVisibility(View.VISIBLE);
        }else if(id.equals("04000204") && data.equals("1000000000000000")){
            //washer off
            imageWasher.setVisibility(View.INVISIBLE);
            if(animationDrawable !=null && animationDrawable.isRunning()) {
                animationDrawable.stop();
            }
        }

        if(id.equals("04000205") && data.equals("1000000000000001")){
            //bonnet on
            Toast.makeText(getActivity(),"bonnet on",Toast.LENGTH_SHORT).show();
        }

        if(id.equals("04000206") && data.equals("1000000000000001")){
            //trunk on
            Toast.makeText(getActivity(),"trunk on",Toast.LENGTH_SHORT).show();
        }
        //temp
        if(id.equals("04000207") && data.substring(0,1).equals("1")){
            String temp = data.substring(15);
            if(temp.equals("0")){
                temp=null;
            } else {
                int num = Integer.parseInt(temp, 16);
                num += 17;
                temp = Integer.toString(num);
            }
            text_temp.setText(temp);
        }
        //heater front
        if(id.equals("04000208") && data.equals("1000000000000001")){
            imageHeater1.setImageDrawable(getActivity().getDrawable(R.drawable.heater_on));
        }else if(id.equals("04000208") && data.equals("1000000000000000")){
            imageHeater1.setImageDrawable(getActivity().getDrawable(R.drawable.heater_off));
        }
        //heater rear
        if(id.equals("04000209") && data.equals("1000000000000001")){
            imageHeater2.setImageDrawable(getActivity().getDrawable(R.drawable.heater_on));
        }else if(id.equals("04000209") && data.equals("1000000000000000")){
            imageHeater2.setImageDrawable(getActivity().getDrawable(R.drawable.heater_off));
        }
        //heater rear
        if(id.equals("0400020A") && data.equals("1000000000000001")){
            imageColdAircon.setImageDrawable(getActivity().getDrawable(R.drawable.aircon_on));
            is_hot = false;
            imageHotAircon.setImageDrawable(getActivity().getDrawable(R.drawable.heatair_off));
        }else if(id.equals("0400020A") && data.equals("1000000000000000")){
            imageColdAircon.setImageDrawable(getActivity().getDrawable(R.drawable.aircon_off));
        }
        //heater rear
        if(id.equals("0400020B") && data.equals("1000000000000001")){
            imageHotAircon.setImageDrawable(getActivity().getDrawable(R.drawable.heatair_on));
            is_cold = false;
            imageColdAircon.setImageDrawable(getActivity().getDrawable(R.drawable.aircon_off));
        }else if(id.equals("0400020B") && data.equals("1000000000000000")){
            imageHotAircon.setImageDrawable(getActivity().getDrawable(R.drawable.heatair_off));
        }

        //wind
        if(id.equals("0400020C") && data.equals("1000000000000001")){
            Log.d("wind","on");
            imageWind.setVisibility(View.VISIBLE);
        }else if(id.equals("0400020C") && data.equals("1000000000000000")){
            imageWind.setVisibility(View.INVISIBLE);
        }
    }

}
