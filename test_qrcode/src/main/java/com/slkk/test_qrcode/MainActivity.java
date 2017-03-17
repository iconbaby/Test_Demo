package com.slkk.test_qrcode;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView img_qrcode;
    private EditText et_url;
    private Button btn_creator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img_qrcode = (ImageView) findViewById(R.id.img_qrcode);
        et_url = (EditText) findViewById(R.id.url);
        btn_creator = (Button) findViewById(R.id.btn_creater);
        btn_creator.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String url = et_url.getText().toString().trim();
        Bitmap bitmap = ZXingUtils.createQRImage(url, img_qrcode.getWidth(), img_qrcode.getHeight());
        img_qrcode.setImageBitmap(bitmap);
    }
}
