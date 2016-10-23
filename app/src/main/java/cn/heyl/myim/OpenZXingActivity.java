package cn.heyl.myim;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class OpenZXingActivity extends AppCompatActivity {
    /**
     * 显示扫描结果
     */
    private TextView mTextView ;
    /**
     * 显示扫描拍的图片
     */
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

        mTextView = (TextView) findViewById(R.id.result);
        mImageView = (ImageView) findViewById(R.id.qrcode_bitmap);
        Bundle bundle = getIntent().getExtras();
        //显示扫描到的内容
        mTextView.setText(bundle.getString("result"));
        //显示
        mImageView.setImageBitmap((Bitmap) getIntent().getParcelableExtra("bitmap"));

    }
}
