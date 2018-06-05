package com.example.dnghwls7.parsingdemo;

import android.provider.DocumentsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listView);

        //Strind []data = {"일","이","삼"};
        //String []data = domParsing();
        String []data = xmlParser();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1, //안드로이드 붙어 있는 것은 시스템이 지급하는 것
                data);

        listView.setAdapter(adapter);
    }

    //DOM parsing 사용하기
    String []domParsing(){

        //1. xml 파일을 읽는다 - xml 파일을 InputStream 객체와 연결
        InputStream in = getResources().openRawResource(R.raw.student);
        DocumentBuilderFactory factory =
                DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document doc = builder.parse(in);

        NodeList studentList = doc.getElementsByTagName("student");
        NodeList nameList = doc.getElementsByTagName("name");
        NodeList ageList = doc.getElementsByTagName("age");
        NodeList addressList = doc.getElementsByTagName("address");

        String [] resultData = new String[studentList.getLength()];
        //data 개수만큼 배열 만들고 배열에 data 넣기
        for (int i = 0; i<studentList.getLength();i++){
            String name = nameList.item(i).getFirstChild().getNodeValue();
            String age = ageList.item(i).getFirstChild().getNodeValue();
            String address = addressList.item(i).getFirstChild().getNodeValue();

            resultData[i]=String.format("이름 : %s 나이 : %s 주소 : %s",name, age, address);
        }
        return resultData;
    }


    //xmlPullParser 사용하기
    String[]xmlParser()
    {
        try
        {
            List<String> list = new ArrayList<String>();
            InputStream in = getResources().openRawResource(R.raw.student);

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.XmlPullParser();
            parser.setInput(new InputStreamReader(in, "utf-8"));


            //getEventType함수가 현재 파서가 어떤 상황에 처해 잇는지 알려준다
            //



            String temp="";
            int eventType = parser.getEventType();
            //현재 eventType을 알아본다
            while(eventType!=XmlPullParser.END_DOCUMENT){
                switch (eventType){
                    case XmlPullParser.START_TAG://<name>
                        String startTag = parser.getName();
                        if(startTag.equals("student")){
                            temp = "";
                        }
                        else if(startTag.equals("name") || startTag.equals("age") ||
                                startTag.equals("address")){
                            temp = temp + parser.nextText()+"    "; //다음 단어 읽기
                        }
                        break;
                    case XmlPullParser.END_TAG://</name>
                        //end tag 만날 때 리스트에 추가한다.
                        String endTag = parser.getName();
                        if(endTag.equals("student"))
                            list.add(temp);
                        break;

                }
                eventType = parser.next();
            }
        }
        catch(Exception ex)
        {
            Log.e("Error", ex.getMessage());
            //Log.e 에러일때
            //Log.i 그냥 정보확인
            //Log.d 디버그 상태

            //ex) printStackTrace() : 안드로이드에서는 안 된다

        }
        return new String[]{"데이타 없음"};
    }
}

