package com.labmap.ko.bowling_input;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText selectedText = null;

    EditText frame1Score1Player1;
    EditText frame1Score2Player1;
    EditText frame2Score1Player1;
    EditText frame2Score2Player1;
    EditText frame3Score1Player1;
    EditText frame3Score2Player1;
    EditText frame4Score1Player1;
    EditText frame4Score2Player1;
    EditText frame5Score1Player1;
    EditText frame5Score2Player1;
    EditText frame6Score1Player1;
    EditText frame6Score2Player1;
    EditText frame7Score1Player1;
    EditText frame7Score2Player1;
    EditText frame8Score1Player1;
    EditText frame8Score2Player1;
    EditText frame9Score1Player1;
    EditText frame9Score2Player1;
    EditText frame10Score1Player1;
    EditText frame10Score2Player1;
    EditText frame10Score3Player1;

    EditText[] frameScore = {frame1Score1Player1, frame1Score2Player1, frame2Score1Player1, frame2Score2Player1, frame3Score1Player1,
            frame3Score2Player1,frame4Score1Player1,frame4Score2Player1,frame5Score1Player1,frame5Score2Player1,frame6Score1Player1,
            frame6Score2Player1,frame7Score1Player1,frame7Score2Player1,frame8Score1Player1, frame8Score2Player1,frame9Score1Player1,
            frame9Score2Player1,frame10Score1Player1,frame10Score2Player1,frame10Score3Player1};

    int[] frameIDScoresPlayer1 = {R.id.frame1Score1Player1, R.id.frame1Score2Player1, R.id.frame2Score1Player1, R.id.frame2Score2Player1, R.id.frame3Score1Player1,
            R.id.frame3Score2Player1,R.id.frame4Score1Player1,R.id.frame4Score2Player1,R.id.frame5Score1Player1,R.id.frame5Score2Player1,R.id.frame6Score1Player1,
            R.id.frame6Score2Player1,R.id.frame7Score1Player1,R.id.frame7Score2Player1,R.id.frame8Score1Player1, R.id.frame8Score2Player1,R.id.frame9Score1Player1,
            R.id.frame9Score2Player1,R.id.frame10Score1Player1,R.id.frame10Score2Player1,R.id.frame10Score3Player1};

    int[] frameScorePlayer1;
    int[] frameTotalPlayer1;

    TextView frame1TotalScorePlayer1;
    TextView frame2TotalScorePlayer1;
    TextView frame3TotalScorePlayer1;
    TextView frame4TotalScorePlayer1;
    TextView frame5TotalScorePlayer1;
    TextView frame6TotalScorePlayer1;
    TextView frame7TotalScorePlayer1;
    TextView frame8TotalScorePlayer1;
    TextView frame9TotalScorePlayer1;
    TextView frame10TotalScorePlayer1;

    TextView[] frameTotalScorePlayer1 = {frame1TotalScorePlayer1, frame2TotalScorePlayer1,frame3TotalScorePlayer1, frame4TotalScorePlayer1,
            frame5TotalScorePlayer1, frame6TotalScorePlayer1, frame7TotalScorePlayer1, frame8TotalScorePlayer1, frame9TotalScorePlayer1,
            frame10TotalScorePlayer1};
    int[] frameIDTotalPlayer1 = {R.id.frame1TotalScorePlayer1, R.id.frame2TotalScorePlayer1,R.id.frame3TotalScorePlayer1, R.id.frame4TotalScorePlayer1,
            R.id.frame5TotalScorePlayer1, R.id.frame6TotalScorePlayer1, R.id.frame7TotalScorePlayer1, R.id.frame8TotalScorePlayer1, R.id.frame9TotalScorePlayer1,
            R.id.frame10TotalScorePlayer1
    };

    int countStrike = 0;
    int countSpare = 0;

    //private InputMethodManager imm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        frameScore[0].setText("1");
        frameScore[1].setText("4");
        frameScore[2].setText("6");
        frameScore[3].setText("/");
        frameScore[4].setText("X");
        frameScore[5].setText("0");
        frameScore[6].setText("5");
        frameScore[7].setText("4");
        frameScore[8].setText("2");
        frameScore[9].setText("/");
        frameScore[10].setText("7");
        frameScore[11].setText("2");
        frameScore[12].setText("5");
        frameScore[13].setText("2");
        frameScore[14].setText("8");
        frameScore[15].setText("/");
        frameScore[16].setText("4");
        frameScore[17].setText("/");
        frameScore[18].setText("X");
        frameScore[19].setText("X");
        frameScore[20].setText("X");

    }

    public void init(){

        for(int i = 0;i< 21;i++){
            frameScore[i] = findViewById(frameIDScoresPlayer1[i]);
            frameScore[i].setInputType(InputType.TYPE_NULL);
            frameScore[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedText = (EditText) v;
                }
            });
        }
        for(int i=0;i<10;i++){
            frameTotalScorePlayer1[i] = findViewById(frameIDTotalPlayer1[i]);
        }
    }

    public void calculateScore(View v){

        int spareCount = 0;
        int strikeCount = 0;
        int sum=0;
        frameScorePlayer1 = new int[21];
        for(int i=0;i<21;i++){
            String temp = frameScore[i].getText().toString();
            if(temp == null){
                frameScorePlayer1[i] = 0;
            }else if(temp.equals("/")){
                frameScorePlayer1[i] = 10 - frameScorePlayer1[i-1];
                countSpare++;
            }else if(temp.equals("X")){
                frameScorePlayer1[i] = 10;
                countStrike++;
            }else{
                frameScorePlayer1[i] = Integer.parseInt(temp);
            }
        }
        frameTotalPlayer1 = new int[10];
        //0-9 frame
        for(int i=0;i<9;i++){
            int num1 = frameScorePlayer1[2*i];
            int num2 = frameScorePlayer1[2*i+1];

            if(frameScore[2*i].getText().toString().equals("X") || frameScore[2*i+1].getText().toString().equals("X")){
                sum+=10+frameScorePlayer1[2*i+2]+frameScorePlayer1[2*i+3];
                if(frameScore[2*i+2].getText().toString().equals("X") || frameScore[2*i+3].getText().toString().equals("X")){
                    if(frameScore[2*i+4].getText().toString().equals("X") || frameScore[2*i+5].getText().toString().equals("X")){
                        sum+=10;
                    }
                    sum+=frameScorePlayer1[2*i+4];
                }
            }else if(frameScore[2*i+1].getText().toString().equals("/")){
                sum+=10+frameScorePlayer1[2*i+2];
            }else{
                sum+=num1+num2;
            }
            frameTotalPlayer1[i] = sum;
            frameTotalScorePlayer1[i].setText(""+sum);
        }
        //final frame
        int num1 = frameScorePlayer1[18];
        int num2 = frameScorePlayer1[19];
        int num3 = frameScorePlayer1[20];
        if(frameScore[18].getText().toString().equals("X")){
            sum+=20+num3;
        }else{
            if(frameScore[19].getText().toString().equals("/")){
                sum+=num1+num2+num3;
            }
            else{
                sum+=num1+num2;
            }
        }
        frameTotalPlayer1[9] = sum;
        frameTotalScorePlayer1[9].setText(""+sum);

    }

    public void inputScore(View v){
        Button button = (Button)v;
        if(selectedText == null){
            Toast.makeText(getApplicationContext(),"nothing",Toast.LENGTH_SHORT).show();
        }else{
            selectedText.setText(((Button)v).getText().toString());
        }
        //Log.d("text",""+button.getText().toString());
        //Log.d("View",selectedText.getText()+"");
    }
}
