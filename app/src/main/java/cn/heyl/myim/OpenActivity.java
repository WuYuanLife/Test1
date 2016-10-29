package cn.heyl.myim;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import tyrantgit.explosionfield.ExplosionField;

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
                    case 4:intent=new Intent(OpenActivity.this,OBSV2Activity.class);break;
                    case 5:intent=new Intent(OpenActivity.this,OBSV3Activity.class);break;
                    case 6:intent=new Intent(OpenActivity.this,OBSV4Activity.class);break;
                    case 7:openDiaLog();return;
                    case 8:intent=new Intent(OpenActivity.this,TriangLifyActivity.class);break;
                    case 9:intent=new Intent(OpenActivity.this,ZXingActivity.class);break;
                    case 10:intent=new Intent(OpenActivity.this,Login3Activity.class);break;
                }
                startActivity(intent);
            }
        });
    }

    private void openDiaLog() {
AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();
        dialog.show();

        Window window = dialog.getWindow();
        final View view = LayoutInflater.from(this).inflate(R.layout.weather_detail, null);
        ImageView ivx = (ImageView) view.findViewById(R.id.ivX);
        final RelativeLayout rldown= (RelativeLayout) view.findViewById(R.id.rldown);
        final RelativeLayout rlup= (RelativeLayout) view.findViewById(R.id.rlup);
        final LinearLayout ll= (LinearLayout) view.findViewById(R.id.linearLayout1);

        final ExplosionField mExplosionField = ExplosionField.attach2Window(OpenActivity.this);
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {

                Log.d("hyl", "onDismiss: ");
//                mExplosionField.explode(rldown);
            }
        });
        ivx.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                rldown.setBackgroundColor(Color.parseColor("#FF69b4"));
                mExplosionField.explode(rldown);
                mExplosionField.explode(rlup);
                new Thread(){
                    @Override
                    public void run() {

                        try {
                            sleep(200);
                            dialog.dismiss();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
//                dialog.dismiss();
            }
        });
        window.setContentView(view);

    }

    private void setData() {
        list = new ArrayList<>();
        list.add("广告轮播");
        list.add("引导滑动");
        list.add("下拉筛选");
        list.add("标题图片拉隐藏");
        list.add("标题栏下拉隐藏");
        list.add("标题布局拉隐藏");
        list.add("标题ListView下拉隐藏");
        list.add("爆炸DiaLog");
        list.add("三角形视图背景");
        list.add("二维码");
        list.add("第三方登录");

    }

}
