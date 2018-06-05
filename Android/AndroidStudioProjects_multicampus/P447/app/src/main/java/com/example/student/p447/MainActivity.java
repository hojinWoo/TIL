package com.example.student.p447;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ItemAdapter itemAdapter;
    ArrayList<Item> list;
    LinearLayout container;

    ListView listView;
    int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = (LinearLayout)findViewById(R.id.container);

        list = new ArrayList<>();
        list.add(new Item("GG1","010-1111-1222",30,R.drawable.icon));
        list.add(new Item("GG2","010-2111-2222",30,R.drawable.icon2));
        list.add(new Item("GG3","010-3111-3222",30,R.drawable.icon3));


        //ListView or Gallery들은 Adapter가 필요..
        itemAdapter = new ItemAdapter(list); //

        listView = findViewById(R.id.listView);
        //inner class가 아닌 따로 class로 만들 때는 자기 자신을 reference로 사용
        //itemAdapter = new ItemAdapter(this, list);
        listView.setAdapter(itemAdapter);
    }

    public void clickBt(View v){
        if(count ==1){
            list.add(new Item("GG4","010-4111-4222",30,R.drawable.icon4));

        }else if(count ==2 ){
            list.add(new Item("GG5","010-5111-5222",30,R.drawable.icon5));
        }else if(count ==3 ){
            list.add(new Item("GG6","010-6111-6222",30,R.drawable.icon6));
        }else if(count ==4 ){
            list.add(new Item("GG7","010-7111-7222",30,R.drawable.icon7));
        }
        listView.setAdapter(itemAdapter);
        //itemAdapter.notifyDataSetChanged();
        //listView.invalidate();
        count++;

    }

    public class ItemAdapter extends BaseAdapter {

        ArrayList<Item> list;

        public ItemAdapter(){

        }
        public ItemAdapter(ArrayList<Item> list){
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
            LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //화면 객체 1개.
            vw = inflater.inflate(R.layout.item, container,true);

            TextView name = vw.findViewById(R.id.textView);
            TextView phone = vw.findViewById(R.id.textView2);
            TextView age = vw.findViewById(R.id.textView3);
            ImageView img = vw.findViewById(R.id.imageView);
            name.setText(list.get(i).getName());
            phone.setText(list.get(i).getMobile());
            age.setText(list.get(i).getAge()+"");
            img.setImageResource(list.get(i).getResId());


            return vw;
        }
    }
}
