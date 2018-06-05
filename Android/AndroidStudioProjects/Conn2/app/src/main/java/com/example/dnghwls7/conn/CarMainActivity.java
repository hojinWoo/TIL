package com.example.dnghwls7.conn;

import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.Timer;


public class CarMainActivity extends AppCompatActivity implements HomeFragment.OnFragmentInteractionListener,BoardFragment.OnFragmentInteractionListener,ControlFragment.OnFragmentInteractionListener, SettingFragment.OnFragmentInteractionListener, ChairFragment.OnFragmentInteractionListener, WindowFragment.OnFragmentInteractionListener, StatusFragment.OnFragmentInteractionListener{

    private String tag = "Server..";
    private Server server;
    private SetupTask setupTask;

    private static String lat_temp = "1";
    private static String lon_temp = "1";
    private static String mile_temp = "10000";
    private static String[] map = {lat_temp, lon_temp, mile_temp};

    HomeFragment homeFragment = new HomeFragment();
    ControlFragment controlFragment = new ControlFragment();
    SettingFragment settingFragment = new SettingFragment();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_main);

        setTabLayout();

        server = new Server();
        server.start();

    }


    //setting tablayout
    public void setTabLayout(){
        //setting tab
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        //add Tab
        tabLayout.addTab(tabLayout.newTab().setText("Home"));
        tabLayout.addTab(tabLayout.newTab().setText("Control"));
        tabLayout.addTab(tabLayout.newTab().setText("Board"));
        tabLayout.addTab(tabLayout.newTab().setText("Setting"));

        //tab order
        //fill : same width / center
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        //change color : unselected tab/ selected tab
        tabLayout.setTabTextColors(Color.parseColor("#ffffff"),Color.parseColor("#ff00ff"));


        final ViewPager viewPager = (ViewPager)findViewById(R.id.Container);
        //PageAdapter class를 통해 tablayout 설정
        // Creating pageAdapter
        final PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), homeFragment, controlFragment, settingFragment);

        viewPager.setAdapter(pageAdapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //setOnTabSelectedListener : add one listener
        //addOnTabSelectedListener : add multiple listener
        //removeOnTabSelectedListener : remove the listener when you're done with it.
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    class Server extends Thread {

        private int port;
        private Socket socket;
        private ServerSocket serverSocket;
        private boolean flag;
        private boolean rflag;
        private DataOutputStream dout;

        public Server() {

            flag = true;
            rflag = true;
            port = 9999;
            try {
                if(serverSocket == null) {
                    serverSocket = new ServerSocket(port);
                }
            } catch (IOException e) {
                Log.d(tag,"비정상적으로 종료 되었습니다...Server(0)");
            }
        }

        //call
        public void sendMessage(String message){
            new Thread(new Sender(message)).start();
        }

        public void run() {
            Log.d(tag,"Server starts...");
            while (flag) {
                try {
                    socket = serverSocket.accept();
                    Log.d(tag,"Connected Client..." + socket.getInetAddress());
                    Thread receiver = new Thread(new Receiver(socket));
                    receiver.start();
                    dout = new DataOutputStream(socket.getOutputStream());
                } catch (IOException e) {
                    Log.d(tag,"비정상적으로 종료 되었습니다...Server(1)");
                }
            }
        }

        class Sender implements Runnable {

            String sendMsg;

            public Sender(String sendMsg) {
                this.sendMsg = sendMsg;
            }

            @Override
            public void run() {
                try {
                    Log.d("send data",sendMsg);
                    dout.writeUTF(sendMsg);

                } catch (Exception e) {
                    Log.d(tag, "비정상적으로 종료 되었습니다...Sender(0)");
                }
            }

        }

        class Receiver implements Runnable {
            private Socket socket;
            private String address;
            private DataInputStream dis;
            private String receiveMessage;
            private String id;
            private String data;

            public Receiver(Socket socket) {
                this.socket = socket;
                //receive IP address
                address = this.socket.getInetAddress().toString();
                try {
                    dis = new DataInputStream(this.socket.getInputStream());

                } catch (IOException e) {
                    Log.d(tag,"비정상적으로 종료 되었습니다...Receiver(0)");
                }

            }

            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("connected ip",address);
                    }
                });
                try {
                    while (rflag) {
                        receiveMessage = dis.readUTF();
                        //Log.d("receive",receiveMessage);
                        if(receiveMessage!=null) {
                            receiveMessage.trim();
                            //extract string
                            receiveMessage = receiveMessage.substring(4, 28);
                            id = receiveMessage.substring(0, 8);
                            data = receiveMessage.substring(8, 24);
                            Log.d("id",id);
                            Log.d("data",data);
                        }
                        /*if (receiveMessage.equals("q")) {
                            receiveMessage = "Disconnected.." + address;
                            break;
                        }*/
                        //WebServer web = new WebServer();
                        //web.execute(receiveMessage,"1000");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //Toast.makeText(CarMainActivity.this,receiveMessage,Toast.LENGTH_SHORT).show();
                                //Log.d("Received Message : ",receiveMessage);
                                getMessageFragment(id, data);
                            }
                        });
                    }

                } catch(Exception e) {
                    Log.d(tag,"비정상적으로 종료 되었습니다...Receiver(1)");

                } finally {
                    try {
                        if (dis != null)
                            dis.close();
                    } catch (IOException e) {
                        Log.d(tag,"비정상적으로 종료 되었습니다...Receiver(2)");
                    }
                    try {
                        if (socket != null)
                            socket.close();
                    } catch (IOException e) {
                        Log.d(tag,"비정상적으로 종료 되었습니다...Receiver(3)");
                    }
                    Log.d(tag,"정상적으로 종료 되었습니다...Receiver");
                }

            }
        }

        class WebServer extends AsyncTask<String, Void, Void> {
            private URL url;
            private String adr;
            private String speed;
            private String temp;

            @Override
            protected Void doInBackground(String... strings) {
                speed = strings[0];
                temp = strings[1];

                adr = "http://70.12.114.134/ws/main.do?speed=" + speed + "&temp="+temp;
                System.out.println(adr);

                URL url = null;
                HttpURLConnection con = null;
                int result=0;

                try {
                    url = new URL(adr);
                    con = (HttpURLConnection) url.openConnection();
                    if(con != null) {
                        con.setConnectTimeout(5000);
                        con.setRequestMethod("GET");
                        con.setRequestProperty("Content-type","text/plain");
                        result = con.getResponseCode();
                    }
                    Log.d(tag,"Http OK..."+result);
                } catch (MalformedURLException e) {
                    Log.d(tag,"Http Error...(0)");
                } catch (IOException e) {
                    Log.d(tag,"Http Error...(1)");
                } finally {
                    con.disconnect();
                }

                return null;
            }
        }
    }
    public void sendDatabyfragment(String id, String data){
        server.sendMessage(id+data);
    }
    String[] send = new String[8];
    public void getMessageFragment(String Id, String Data){
        //Toast.makeText(CarMainActivity.this,Data,Toast.LENGTH_SHORT).show();

        ReloadTask reloadTask = new ReloadTask();
        reloadTask.execute();
        if(Id.equals("01000100") || Id.equals("01000101")) {
            if(Id.equals("01000100")){
                lat_temp = Integer.toString(Integer.parseInt(Data.substring(0,2),16));
                lat_temp += "." + String.format("%06d",Integer.parseInt(Data.substring(2,8),16));
                map[0] = lat_temp;
                lon_temp = Integer.toString(Integer.parseInt(Data.substring(8,10),16));
                lon_temp += "." + String.format("%06d",Integer.parseInt(Data.substring(10,16),16));
                map[1] = lon_temp;
            }else if(Id.equals("01000101")) {
                mile_temp = Integer.toString(Integer.parseInt(Data.substring(1,16),16));
                map[2] = mile_temp;
            }
            LatLonSender latLonSender = new LatLonSender();
            latLonSender.execute(lat_temp,lon_temp,mile_temp);
            homeFragment.setLatLon(lat_temp, lon_temp);
            //Log.d("??",map[0]+", "+map[1]+", "+map[2]);
        }/*else if(Id.equals("") && Data.equals("1000000000000001")){
            send[1]= "1";
            setupTask.execute(send);
        }*/
        else if(Id.equals("04000200")||Id.equals("04000201")||Id.equals("04000202")||Id.equals("04000203")||Id.equals("04000204")||Id.equals("04000205")
                ||Id.equals("04000206")|Id.equals("04000207")|Id.equals("04000208")|Id.equals("04000209")|Id.equals("0400020A")|Id.equals("0400020B")||Id.equals("0400020C")){
           controlFragment.setMessage(Id, Data);
        }
    }

    class LatLonSender extends AsyncTask<String, String, String>{

        String url = null;

        String lat;
        String lon;
        String mile;
        //constructor
        public LatLonSender(){

        }
        public LatLonSender(String url){
            this.url = url;
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... strings) {
            lat = strings[0];
            lon = strings[1];
            mile = strings[2];
            Log.d("sssss", lat+", "+lon+", "+mile);
            //To JSP
            url = "http://70.12.114.140/car/updateCarloc.do?car_num=test999&lat="+lat+"&log="+lon+"&mile="+mile;

            //HTTP REQUEST
            StringBuilder sb = new StringBuilder();
            URL url;
            HttpURLConnection con = null;
            BufferedReader reader=null;
            try{
                url = new URL(this.url);
                Log.d("url", "OK");
                //Connection
                con = (HttpURLConnection)url.openConnection();
                if(con != null){
                    con.setConnectTimeout(5000);
                    con.setRequestMethod("POST");
                    con.setRequestProperty("Accept","*/*");
                    Log.d("------------1-1","OK");

                    if(con.getResponseCode()!=HttpURLConnection.HTTP_OK){
                        Log.d("getResponseCode","ERROR");
                        return null;
                    }
                    /*reader  = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String line=null;
                    while(true){
                        line=reader.readLine();
                        Log.d("receive",line);
                        if(line==null){
                            break;
                        }
                        sb.append(line);
                    }*/
                }
            }catch (Exception e){
                //Log.d("------------2",e.getMessage());
                return e.getMessage();
            }finally {
                con.disconnect();
            }
            return sb.toString();
            // return "";
        }

        @Override
        protected void onPostExecute(String s) {
            //Log.d("------------3",s);
            //Toast.makeText(MainActivity.this, "" + s, Toast.LENGTH_SHORT).show();
        }
    }

    //car_num/start_onoff/Door_onoff/Air_lv/Heat_lv/Elight_onoff/Temp/
    String[] info = new String[7];

    boolean engineFlag = false;
    boolean doorFlag = false;

    public class ReloadTask extends AsyncTask<String, Void, Void> {

        private final String TAG = "ReloadTask";
        private boolean displayFlag;
        private int tasknum;

        public ReloadTask() {
            displayFlag = true;
        }

        public ReloadTask(boolean displayFlag, int tasknum) {
            this.displayFlag = displayFlag;
            this.tasknum = tasknum;
        }
        @Override
        protected Void doInBackground(String... strings) {
            Log.e(TAG, "Do Task");

            String address = "http://70.12.114.140/car/readCarCtrl.do?car_num=test999";
            URL url = null;
            HttpURLConnection con = null;
            BufferedReader br = null;

            try {
                url = new URL(address);

                //connect
                con=(HttpURLConnection)url.openConnection();

                        if(con != null) {
                            con.setConnectTimeout(1000);
                            con.setRequestMethod("GET");
                            con.setRequestProperty("Accept","*/*");
                            if(con.getResponseCode() != HttpURLConnection.HTTP_OK){
                                return null;
                    }
                    br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    info = br.readLine().toString().split("/");
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                con.disconnect();
            }
            Log.e(TAG, "Stop Task");

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if(engineFlag==false && info[1].equals("1")){
                sendDatabyfragment("04000201","1000000000000001");
                engineFlag = true;
                Log.d("by cell phone - engine", "on");
            } else if (engineFlag == true && info[1].equals("0")) {
                engineFlag = false;
                sendDatabyfragment("04000201", "1000000000000000");
                Log.d("by cell phone - engine", "off");
            }

            if (doorFlag == false && info[2].equals("1")) {
                sendDatabyfragment("04000200", "1000000000000001");
                doorFlag = true;
                Log.d("by cell phone - door", "on");
            } else if (doorFlag == true && info[2].equals("0")) {
                doorFlag = false;
                sendDatabyfragment("04000200", "1000000000000000");
                Log.d("by cell phone - door", "off");
            }
        }
    }

    public class SetupTask extends AsyncTask<String, Void, Void> {

        private final String TAG = "SetupTask";

        @Override
        protected Void doInBackground(String... strings) {
            String address = "http://70.12.114.140/car/updateCarCtrl.do?car_num=" + strings[0] + "&start_onoff="+ strings[1]+"&Door_onoff="+ strings[2]+"&Air_lv="+ strings[3]+"&Heat_lv="+ strings[4]+"&Elight_onoff="+ strings[5]+"&Temp="+ strings[6];
            Log.d("please","recieve");
            URL url = null;
            HttpURLConnection con = null;
            BufferedReader br = null;

            try {
                url = new URL(address);

                //connect
                con=(HttpURLConnection)url.openConnection();

                if(con != null) {
                    con.setConnectTimeout(1000);
                    con.setRequestMethod("GET");
                    con.setRequestProperty("Accept","**/*//*");
                    if(con.getResponseCode() != HttpURLConnection.HTTP_OK){
                        return null;
                    }
                    br = new BufferedReader(new InputStreamReader(con.getInputStream()));

                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                con.disconnect();
            }
            Log.e(TAG, "Stop Task");
            return null;
        }
    }
}
