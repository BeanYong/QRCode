package com.anasit.beanyong.qrcode;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

public class MainActivity extends AppCompatActivity {

    private TextView mResult;
    private Button mScan;
    private Button mGenerate;
    private EditText mInput;
    private ImageView mQR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mScan = (Button) findViewById(R.id.btn_scan);
        mResult = (TextView) findViewById(R.id.tv_result);
        mGenerate = (Button) findViewById(R.id.btn_generate);
        mInput = (EditText) findViewById(R.id.et_input);
        mQR = (ImageView) findViewById(R.id.iv_qr);
        mScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.startActivityForResult(new Intent(MainActivity.this, CaptureActivity.class),0);
            }
        });
        mGenerate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String input = mInput.getText().toString().trim();
                Bitmap logo = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
                Bitmap qr = EncodingUtils.createQRCode(input, 300, 300, logo);
                mQR = (ImageView) findViewById(R.id.iv_qr);
                mQR.setImageBitmap(qr);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Bundle bundle = data.getExtras();
            String result = bundle.getString("result");
            mResult.setText(result);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
