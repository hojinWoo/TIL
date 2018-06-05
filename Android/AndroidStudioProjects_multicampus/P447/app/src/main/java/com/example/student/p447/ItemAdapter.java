package com.example.student.p447;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class ItemAdapter extends BaseAdapter {

    ArrayList<Item> list;
    Context context;

    public ItemAdapter(){

    }
    public ItemAdapter(Context context, ArrayList<Item> list){
        this.context = context;
        this.list = list;
    }

    //return data size
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void addItem(Item item){
        list.add(item);
    }

    //가져올 때마다 호출(함수 호출을 따로 안해도 자동적으로 실행된다)
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vw = null;
        //item.xml에 ArrayLIst에 있는 data 중 한 개를 같은 형태로 만들어서 넣어준다.

        //getSystemService는 oncreate에서 AppCompataActivity를 상속받아서 사용했지만
        //여기서는 따로 호출하기 위해서 Context (Android reference 받아오기)
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //vw = inflater.inflate(R.layout.item, R.id.container,true);
        return null;
    }
}
