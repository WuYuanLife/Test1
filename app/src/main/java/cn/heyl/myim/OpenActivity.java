package cn.heyl.myim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class OpenActivity extends AppCompatActivity {
    private List<String> list;
    private ListView lv;
    private ListAdapter adapter;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open);
        lv = (ListView) findViewById(R.id.openlistView);
        setData();
        adapter=new ArrayAdapter(this,R.layout.item,R.id.list_item_text,list);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:intent=new Intent(OpenActivity.this,MainActivity.class);break;
                    case 1:intent=new Intent(OpenActivity.this,SVActivity.class);break;
                    case 2:intent=new Intent(OpenActivity.this,DownMentActivity.class);break;
                    case 3:intent=new Intent(OpenActivity.this,OBSrcollviewActivity.class);break;
                }
                startActivity(intent);
            }
        });
    }

    private void setData() {
        list = new ArrayList<>();
        list.add("广告轮播");
        list.add("引导滑动");
        list.add("下拉筛选");
        list.add("标题栏下拉隐藏");

    }

}
