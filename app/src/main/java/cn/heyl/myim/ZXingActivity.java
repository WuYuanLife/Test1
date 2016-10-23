package cn.heyl.myim;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.WriterException;

import zxing.encoding.EncodingHandler;
import zxingdemo.MipcaActivityCapture;

public class ZXingActivity extends AppCompatActivity {

        private final static int SCANNIN_GREQUEST_CODE = 1;
        /**
         * 显示扫描结果
         */
        private TextView mTextView ;
        /**
         * 显示扫描拍的图片
         */
        private ImageView mImageView;

        /**
         * 输入框产生二维码
         * @param savedInstanceState
         */
        private EditText qrStrEditText;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_zxing);

            mTextView = (TextView) findViewById(R.id.result);
            mImageView = (ImageView) findViewById(R.id.qrcode_bitmap);
            qrStrEditText = (EditText) findViewById(R.id.et_qr_string);

            //点击按钮跳转到二维码扫描界面，这里用的是startActivityForResult跳转
            //扫描完了之后调到该界面
            Button mButtonScan = (Button) findViewById(R.id.button1);
            Button mBtnTwoCode = (Button) findViewById(R.id.button2);
            Button mBtnOneCode = (Button) findViewById(R.id.button3);

            mButtonScan.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(ZXingActivity.this, MipcaActivityCapture.class);
                    startActivity(intent);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
                }
            });


            //产生二维码
            mBtnTwoCode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String contentString = qrStrEditText.getText().toString();
                    try {
                        if (!contentString.equals("")) {
                            Bitmap qrCodeBitmap = EncodingHandler.createQRCode(contentString, 350);
                            mImageView.setImageBitmap(qrCodeBitmap);
                            qrStrEditText.setText("");
                            mTextView.setText(contentString);
                        } else {
                            Toast.makeText(getApplicationContext(), "Text can be not empty", Toast.LENGTH_SHORT).show();
                        }
                    } catch (WriterException e) {
                        e.printStackTrace();
                    }
                }
            });

            //产生条形码
            mBtnOneCode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String contentString = qrStrEditText.getText().toString();
                    int size = contentString.length();
                    for (int i = 0; i < size; i++) {
                        int c = contentString.charAt(i);
                        if ((19968 <= c && c < 40623)) {
                            Toast.makeText(getApplicationContext(), "text not be chinese", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                    Bitmap mBmpOneCode = null;
                    try {
                        if (contentString != null && !"".equals(contentString)) {
                            mBmpOneCode = EncodingHandler.CreateOneDCode(contentString);
                            qrStrEditText.setText("");
                            mTextView.setText(contentString);
                        }
                    } catch (WriterException e) {
                        e.printStackTrace();
                    }
                    if (mBmpOneCode != null) {
                        mImageView.setImageBitmap(mBmpOneCode);
                    }
                }
            });
        }


        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            switch (requestCode) {
                case SCANNIN_GREQUEST_CODE:
                    if(resultCode == RESULT_OK){
                        Bundle bundle = data.getExtras();
                        //显示扫描到的内容
                        mTextView.setText(bundle.getString("result"));
                        //显示
                        mImageView.setImageBitmap((Bitmap) data.getParcelableExtra("bitmap"));
                    }
                    break;
            }
        }
    }
