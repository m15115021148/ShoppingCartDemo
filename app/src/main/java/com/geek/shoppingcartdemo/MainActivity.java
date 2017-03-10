package com.geek.shoppingcartdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,MyAdapter.OnCallBack{
    private LinearLayout mSelectAll;//选择所有
    private ListView mLv;
    private MyAdapter adapter;
    private List<TypeModel> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSelectAll = (LinearLayout) findViewById(R.id.select_all);
        mSelectAll.setOnClickListener(this);
        mLv = (ListView) findViewById(R.id.listView);
        for (int i=0;i<10;i++){
            TypeModel model = new TypeModel();
            model.setName("DATA:"+(i+1));
            model.setIsSelect("0");
            mList.add(model);
        }
        initListView();
    }

    private void initListView() {
        adapter = new MyAdapter(this,mList,this);
        mLv.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if (v == mSelectAll){
            if (mSelectAll.isSelected()){
                mSelectAll.setSelected(false);
                for (TypeModel model:mList){
                    model.setIsSelect("0");
                }
                adapter.notifyDataSetChanged();
            }else{
                mSelectAll.setSelected(true);
                for (TypeModel model:mList){
                    model.setIsSelect("1");
                }
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onSelectedListener(int pos) {
        if (mList.get(pos).getIsSelect().equals("0")){
            mList.get(pos).setIsSelect("1");
        }else{
            mList.get(pos).setIsSelect("0");
            mSelectAll.setSelected(false);
        }
        adapter.notifyDataSetChanged();
    }
}
