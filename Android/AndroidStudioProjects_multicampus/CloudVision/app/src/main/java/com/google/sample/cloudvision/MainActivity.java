/*
 * Copyright 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.sample.cloudvision;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.vision.v1.Vision;
import com.google.api.services.vision.v1.VisionRequest;
import com.google.api.services.vision.v1.VisionRequestInitializer;
import com.google.api.services.vision.v1.model.AnnotateImageRequest;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesRequest;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesResponse;
import com.google.api.services.vision.v1.model.EntityAnnotation;
import com.google.api.services.vision.v1.model.Feature;
import com.google.api.services.vision.v1.model.Image;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    private static final String CLOUD_VISION_API_KEY = "AIzaSyBDdSFBFTZVyWlND4th3S0tgUFQa_0vWDM";
    public static final String FILE_NAME = "temp.jpg";
    private static final String ANDROID_CERT_HEADER = "X-Android-Cert";
    private static final String ANDROID_PACKAGE_HEADER = "X-Android-Package";
    private static final int MAX_LABEL_RESULTS = 10;
    private static final int MAX_DIMENSION = 1200;

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int GALLERY_PERMISSIONS_REQUEST = 0;
    private static final int GALLERY_IMAGE_REQUEST = 1;
    public static final int CAMERA_PERMISSIONS_REQUEST = 2;
    public static final int CAMERA_IMAGE_REQUEST = 3;

    private TextView mImageDetails;
    private ImageView mMainImage;

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

    TextView tv_sum;
    int countStrike = 0;
    int countSpare = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder
                    .setMessage(R.string.dialog_select_prompt)
                    .setPositiveButton(R.string.dialog_select_gallery, (dialog, which) -> startGalleryChooser())
                    .setNegativeButton(R.string.dialog_select_camera, (dialog, which) -> startCamera());
            builder.create().show();
        });*/

        //mImageDetails = findViewById(R.id.image_details);
        //mMainImage = findViewById(R.id.main_image);
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
        tv_sum = findViewById(R.id.tv_sum);
        tv_sum.setText("방번호:126/test/test@test.com");

        //추가 구현 하려면 사진을 scan으로 해서 흑색으로 만들고 해야 할 듯
        //검은색으로
        frameScore[0].setText("7");
        frameScore[1].setText("/");
        frameScore[2].setText("1");
        frameScore[3].setText("4");
        frameScore[4].setText("X");
        frameScore[5].setText("-");
        frameScore[6].setText("5");
        frameScore[7].setText("4");
        frameScore[8].setText("2");
        frameScore[9].setText("/");
        frameScore[10].setText("6");
        frameScore[11].setText("2");
        frameScore[12].setText("8");
        frameScore[13].setText("/");
        frameScore[14].setText("4");
        frameScore[15].setText("/");
        frameScore[16].setText("6");
        frameScore[17].setText("/");
        frameScore[18].setText("X");
        frameScore[19].setText("X");
        frameScore[20].setText("X");
    }

    public void imporgImage(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder
                .setMessage(R.string.dialog_select_prompt)
                .setPositiveButton(R.string.dialog_select_gallery, (dialog, which) -> startGalleryChooser())
                .setNegativeButton(R.string.dialog_select_camera, (dialog, which) -> startCamera());
        builder.create().show();

    }
    public void startGalleryChooser() {
        if (PermissionUtils.requestPermission(this, GALLERY_PERMISSIONS_REQUEST, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select a photo"),
                    GALLERY_IMAGE_REQUEST);
        }
    }

    public void startCamera() {
        if (PermissionUtils.requestPermission(
                this,
                CAMERA_PERMISSIONS_REQUEST,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA)) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            Uri photoUri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", getCameraFile());
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivityForResult(intent, CAMERA_IMAGE_REQUEST);
        }
    }

    public File getCameraFile() {
        File dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return new File(dir, FILE_NAME);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            uploadImage(data.getData());
        } else if (requestCode == CAMERA_IMAGE_REQUEST && resultCode == RESULT_OK) {
            Uri photoUri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", getCameraFile());
            uploadImage(photoUri);
        }

    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CAMERA_PERMISSIONS_REQUEST:
                if (PermissionUtils.permissionGranted(requestCode, CAMERA_PERMISSIONS_REQUEST, grantResults)) {
                    startCamera();
                }
                break;
            case GALLERY_PERMISSIONS_REQUEST:
                if (PermissionUtils.permissionGranted(requestCode, GALLERY_PERMISSIONS_REQUEST, grantResults)) {
                    startGalleryChooser();
                }
                break;
        }
    }

    public void uploadImage(Uri uri) {
        if (uri != null) {
            try {
                // scale the image to save on bandwidth
                Bitmap bitmap =
                        scaleBitmapDown(
                                MediaStore.Images.Media.getBitmap(getContentResolver(), uri),
                                MAX_DIMENSION);

                callCloudVision(bitmap);
                //mMainImage.setImageBitmap(bitmap);

            } catch (IOException e) {
                Log.d(TAG, "Image picking failed because " + e.getMessage());
                Toast.makeText(this, R.string.image_picker_error, Toast.LENGTH_LONG).show();
            }
        } else {
            Log.d(TAG, "Image picker gave us a null image.");
            Toast.makeText(this, R.string.image_picker_error, Toast.LENGTH_LONG).show();
        }
    }

    private Vision.Images.Annotate prepareAnnotationRequest(Bitmap bitmap) throws IOException {
        HttpTransport httpTransport = AndroidHttp.newCompatibleTransport();
        JsonFactory jsonFactory = GsonFactory.getDefaultInstance();

        VisionRequestInitializer requestInitializer =
                new VisionRequestInitializer(CLOUD_VISION_API_KEY) {
                    /**
                     * We override this so we can inject important identifying fields into the HTTP
                     * headers. This enables use of a restricted cloud platform API key.
                     */
                    @Override
                    protected void initializeVisionRequest(VisionRequest<?> visionRequest)
                            throws IOException {
                        super.initializeVisionRequest(visionRequest);

                        String packageName = getPackageName();
                        visionRequest.getRequestHeaders().set(ANDROID_PACKAGE_HEADER, packageName);

                        String sig = PackageManagerUtils.getSignature(getPackageManager(), packageName);

                        visionRequest.getRequestHeaders().set(ANDROID_CERT_HEADER, sig);
                    }
                };

        Vision.Builder builder = new Vision.Builder(httpTransport, jsonFactory, null);
        builder.setVisionRequestInitializer(requestInitializer);

        Vision vision = builder.build();

        BatchAnnotateImagesRequest batchAnnotateImagesRequest =
                new BatchAnnotateImagesRequest();
        batchAnnotateImagesRequest.setRequests(new ArrayList<AnnotateImageRequest>() {{
            AnnotateImageRequest annotateImageRequest = new AnnotateImageRequest();

            // Add the image
            Image base64EncodedImage = new Image();
            // Convert the bitmap to a JPEG
            // Just in case it's a format that Android understands but Cloud Vision
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream);
            byte[] imageBytes = byteArrayOutputStream.toByteArray();

            // Base64 encode the JPEG
            base64EncodedImage.encodeContent(imageBytes);
            annotateImageRequest.setImage(base64EncodedImage);

            // add the features we want
            annotateImageRequest.setFeatures(new ArrayList<Feature>() {{
                Feature textDetection = new Feature();
                textDetection.setType("TEXT_DETECTION");
                textDetection.setMaxResults(10);
                add(textDetection);
            }});

            // Add the list of one thing to the request
            add(annotateImageRequest);
        }});

        Vision.Images.Annotate annotateRequest =
                vision.images().annotate(batchAnnotateImagesRequest);
        // Due to a bug: requests to Vision API containing large images fail when GZipped.
        annotateRequest.setDisableGZipContent(true);
        Log.d(TAG, "created Cloud Vision request object, sending request");

        return annotateRequest;
    }

    private static class LableDetectionTask extends AsyncTask<Object, Void, String> {
        private final WeakReference<MainActivity> mActivityWeakReference;
        private Vision.Images.Annotate mRequest;

        LableDetectionTask(MainActivity activity, Vision.Images.Annotate annotate) {
            mActivityWeakReference = new WeakReference<>(activity);
            mRequest = annotate;
        }

        @Override
        protected String doInBackground(Object... params) {
            try {
                Log.d(TAG, "created Cloud Vision request object, sending request");
                BatchAnnotateImagesResponse response = mRequest.execute();
                return convertResponseToString(response);

            } catch (GoogleJsonResponseException e) {
                Log.d(TAG, "failed to make API request because " + e.getContent());
            } catch (IOException e) {
                Log.d(TAG, "failed to make API request because of other IOException " +
                        e.getMessage());
            }
            return "Cloud Vision API request failed. Check logs for details.";
        }

        @Override
        protected void onPostExecute(String result) {
            MainActivity activity = mActivityWeakReference.get();
            if (activity != null && !activity.isFinishing()) {
                //TextView imageDetail = activity.findViewById(R.id.image_details);
                //imageDetail.setText(result);

                //output 출력
                String temp = result;
                temp = temp.replace(" ", "");
                temp = temp.replace("\n", "");
                Log.d("result","글자 결과 : " + temp);
                TextView sum = activity.findViewById(R.id.tv_sum);
                sum.setText(result);
                String[] temp_array = temp.split("");

                EditText[] test = activity.frameScore;

                for (int i = 0; i < 21; i++) {
                    if (temp_array[i] != null || temp_array[i] != ""){
                        test[i].setText(temp_array[i+1]);
                    }else{
                        test[i].setText("0");
                    }
                }
            }
        }
    }

    protected void onPostCreate(String result) {

    }

    private void callCloudVision(final Bitmap bitmap) {
        // Switch text to loading
        //mImageDetails.setText(R.string.loading_message);


        // Do the real work in an async task, because we need to use the network anyway
        try {
            AsyncTask<Object, Void, String> labelDetectionTask = new LableDetectionTask(this, prepareAnnotationRequest(bitmap));
            labelDetectionTask.execute();
        } catch (IOException e) {
            Log.d(TAG, "failed to make API request because of other IOException " +
                    e.getMessage());
        }

    }

    private Bitmap scaleBitmapDown(Bitmap bitmap, int maxDimension) {

        int originalWidth = bitmap.getWidth();
        int originalHeight = bitmap.getHeight();
        int resizedWidth = maxDimension;
        int resizedHeight = maxDimension;

        if (originalHeight > originalWidth) {
            resizedHeight = maxDimension;
            resizedWidth = (int) (resizedHeight * (float) originalWidth / (float) originalHeight);
        } else if (originalWidth > originalHeight) {
            resizedWidth = maxDimension;
            resizedHeight = (int) (resizedWidth * (float) originalHeight / (float) originalWidth);
        } else if (originalHeight == originalWidth) {
            resizedHeight = maxDimension;
            resizedWidth = maxDimension;
        }
        return Bitmap.createScaledBitmap(bitmap, resizedWidth, resizedHeight, false);
    }

    public static String convertResponseToString(BatchAnnotateImagesResponse response) {
        //StringBuilder message = new StringBuilder("I found these things:\n\n");
        String message ="";
        List<EntityAnnotation> labels = response.getResponses().get(0).getTextAnnotations();
        if (labels != null) {
            message+=labels.get(0).getDescription();
           /* for (EntityAnnotation label : labels) {
                message.append(String.format(Locale.US, "%.3f: %s", label.getScore(), label.getDescription()));
                message.append("\n");
            }*/
        } else {
            //message.append("nothing");
            message+="nothing";
        }

        return message;
        //return message.toString();
    }

    public void calculateScore(View v){

        int spareCount = 0;
        int strikeCount = 0;
        int sum=0;
        frameScorePlayer1 = new int[21];
        for(int i=0;i<21;i++){
            String temp = frameScore[i].getText().toString();
            if(temp == null || temp.equals("-")){
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
