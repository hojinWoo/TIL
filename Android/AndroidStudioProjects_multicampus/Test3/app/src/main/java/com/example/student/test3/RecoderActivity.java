package com.example.student.test3;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.WriteAbortedException;

public class RecoderActivity extends AppCompatActivity {
    //MediaRecorder myrecorder;
    private String outputFile = null;
    private Button start, stop, play;

    private static final int RECORDER_BPP = 16;
    private static final String AUDIO_RECORDER_FILE_EXT_WAV = ".wav";
    private static final String AUDIO_RECORDER_FOLDER = "AudioRecorder";
    private static final String AUDIO_RECORDER_TEMP_FILE = "record_temp.raw";
    private static final int RECORDER_SAMPLERATE = 44100;
    private static final int RECORDER_CHANNELS = AudioFormat.CHANNEL_IN_STEREO;
    private static final int RECORDER_AUDIO_ENCODING = AudioFormat.ENCODING_PCM_16BIT;

    private AudioRecord recorder = null;
    private int buffersize = 0;
    private Thread recordingThread = null;
    private boolean isRecording =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setButtonHandlers();
        enableButtons(false);
        buffersize = AudioRecord.getMinBufferSize(8000, AudioFormat.CHANNEL_CONFIGURATION_MONO,AudioFormat.ENCODING_PCM_16BIT);


        /*start = (Button) findViewById(R.id.start);
        stop =  (Button) findViewById(R.id.stop);
        play =  (Button) findViewById(R.id.play);

        stop.setEnabled(false);
        play.setEnabled(false);

        //outputFile = Environment.getExternalStorageDirectory().getAbsolutePath()+"/myrec.3gp";

        myrecorder = new MediaRecorder();
        myrecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myrecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        myrecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        myrecorder.setOutputFile(outputFile);*/
    }
    public void setButtonHandlers(){
        ((Button)findViewById(R.id.start)).setOnClickListener(btnClick);
        ((Button)findViewById(R.id.stop)).setOnClickListener(btnClick);
    }
    public void enableButton(int id, boolean isEnable){
        ((Button)findViewById(id)).setEnabled(isEnable);
    }
    public void enableButtons(boolean isRecording){
        enableButton(R.id.start,!isRecording);
        enableButton(R.id.stop,isRecording);
    }
    public String getFileName(){
        String filepath = Environment.getExternalStorageDirectory().getPath();
        File file = new File(filepath, AUDIO_RECORDER_FOLDER);
        if(!file.exists()){
            file.mkdir();
        }

        return(file.getAbsolutePath() + "/" + System.currentTimeMillis() + AUDIO_RECORDER_FILE_EXT_WAV);
    }
    public String getTempFileName(){
        String filepath = Environment.getExternalStorageDirectory().getPath();
        File file = new File(filepath, AUDIO_RECORDER_FOLDER);
        if(!file.exists()){
            file.mkdir();
        }
        File tempFile = new File(filepath, AUDIO_RECORDER_TEMP_FILE);
        if(!tempFile.exists()){
            tempFile.mkdir();
        }
        return(file.getAbsolutePath() + "/" + AUDIO_RECORDER_TEMP_FILE);
    }

    private void startRecording(){
        recorder = new AudioRecord(MediaRecorder.AudioSource.MIC, RECORDER_SAMPLERATE, RECORDER_CHANNELS, RECORDER_AUDIO_ENCODING, buffersize);

        int i = recorder.getState();
        if (i == 1){
            recorder.startRecording();
        }
        isRecording = true;
        recordingThread  = new Thread(new Runnable() {
            @Override
            public void run() {
                writeAudioDataToFile();
            }
        }, "AudioRecorder Thread");
        recordingThread.start();

    }
    private  void writeAudioDataToFile() {
        byte data[] = new byte[buffersize];
        String filename = getTempFileName();
        FileOutputStream os = null;

        try {
            os = new FileOutputStream(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int read = 0;
        if (null != os) {
            while (isRecording) {
                read = recorder.read(data, 0, buffersize);

                if (AudioRecord.ERROR_INVALID_OPERATION != read) {
                    try {
                        os.write(data);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        try {
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void stopRecording(){

        if (null != recorder){
            isRecording = false;

            int i = recorder.getState();
            if(i == 1){
                recorder.stop();
            }
            recorder.release();
            recorder = null;
            recordingThread = null;
        }

        copyWaveFile(getTempFileName(), getFileName());
        deleteTempFile();
    }

    private void deleteTempFile(){
        File file = new File(getTempFileName());
        file.delete();
    }
    private void copyWaveFile(String inFileName, String outFileName){
        FileInputStream in = null;
        FileOutputStream out = null;
        long totalAudioLen = 0;
        long totalDataLen = totalAudioLen+36;
        long longSampleRate = RECORDER_SAMPLERATE;
        int channels = 2;
        long byteRate = RECORDER_BPP +  RECORDER_SAMPLERATE + channels/8;
        byte[] data = new byte[buffersize];

        try{
            in = new FileInputStream(inFileName);
            out = new FileOutputStream(outFileName);
            totalAudioLen = in.getChannel().size();
            totalDataLen = totalAudioLen + 36;
            WriteWaveFileHeader(out, totalAudioLen, totalDataLen, longSampleRate, channels, byteRate);
            while (in.read(data) != -1){
                out.write(data);
            }
            in.close();
            out.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void WriteWaveFileHeader(FileOutputStream out, long totalAudioLen, long totalDataLen, long longSampleRate, int channels, long byteRate) throws IOException{
        byte[] header = new byte[44];

        header[0] = 'R';
        header[1] = 'I';
        header[2] = 'F';
        header[3] = 'F';

        header[4] =  (byte)(totalDataLen & 0xff);
        header[5] =  (byte)((totalDataLen >> 8) & 0xff);
        header[6] =  (byte)((totalDataLen >> 16) & 0xff);
        header[7] =  (byte)((totalDataLen >> 24) & 0xff);

        header[8] = 'W';
        header[9] = 'A';
        header[10] = 'V';
        header[11] = 'E';

        header[12] = 'f'; //chunk
        header[13] = 'm';
        header[14] = 't';
        header[15] = ' ';
        header[16] = 16; //4 bytes (size of 'fmt')

        header[17] = 0;
        header[18] = 0;
        header[19] = 0;
        header[20] = 1; //format = 1

        header[21] = 0;
        header[22] = (byte) channels;
        header[23] = 0;
        header[24] = (byte) (longSampleRate & 0xff);
        header[25] = (byte) ((longSampleRate >> 8) & 0xff);
        header[26] = (byte) ((longSampleRate >> 16) & 0xff);
        header[27] = (byte) ((longSampleRate >> 24) & 0xff);

        header[28] = (byte) (byteRate & 0xff);
        header[29] = (byte) ((byteRate >> 8) & 0xff);
        header[30] = (byte) ((byteRate >> 8) & 0xff);
        header[31] = (byte) ((byteRate >> 16) & 0xff);
        header[32] = (byte) ((byteRate >> 24) & 0xff);
        header[33] = 0;

        header[34] = RECORDER_BPP;
        header[35] = 0;
        header[36] = 'd';
        header[37] = 'a';
        header[38] = 't';
        header[39] = 'a';
        header[40] = (byte) (totalAudioLen & 0xff);
        header[41] = (byte) ((totalAudioLen >> 8) & 0xff);
        header[42] = (byte) ((totalAudioLen >> 16) & 0xff);
        header[43] = (byte) ((totalAudioLen >> 24) & 0xff);

        out.write(header,0,44);

    }
    private View.OnClickListener btnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.start: {
                    enableButtons(true);
                    startRecording();

                    break;
                }
                case R.id.stop:{
                    enableButtons(false);
                    stopRecording();

                    break;
                }

            }
        }
    };



    /*public void start(View v){
        try{
            myrecorder.prepare();
        }catch (IllegalStateException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

        start.setEnabled(false);
        start.setEnabled(true);

        Toast.makeText(this, "Recording start", Toast.LENGTH_SHORT).show();
    }
    public void stop (View v){
        myrecorder.stop();
        myrecorder.release();
        myrecorder = null;
        stop.setEnabled(false);
        play.setEnabled(true);
        Toast.makeText(this, "Recording stop", Toast.LENGTH_SHORT).show();


    }
    public void play(View v) throws  IOException{
        MediaPlayer m = new MediaPlayer();
        m.setDataSource(outputFile);
        m.prepare();
        m.start();
        Toast.makeText(this, "playing audio", Toast.LENGTH_SHORT).show();
    }*/
}
