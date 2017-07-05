package com.wp.textbomber;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends Activity {

    private Button button;
    private Button button2;
    private EditText phonenumber;
    private EditText sms_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        phonenumber = (EditText) findViewById(R.id.ed_phone);
        sms_content = (EditText) findViewById(R.id.ed_sms_content);
        button.setOnClickListener(new sendListener());
        button2.setOnClickListener(new sendListener2());
        // String phone = button.getText().toString().trim();
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(),"正在发送中...",Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    class sendListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            String phone = phonenumber.getText().toString().trim();
            String content = sms_content.getText().toString().trim();
            SmsManager smsManager = SmsManager.getDefault();
            if (sms_content.length() > 70) {
                List<String> contents = smsManager.divideMessage(content);
                for (String c : contents) {
                    smsManager.sendTextMessage(phone, null, c, null, null);
                }
            } else {
                smsManager.sendTextMessage(phone, null, content, null, null);
            }
            Toast.makeText(MainActivity.this, "短信发送成功。。。", Toast.LENGTH_LONG).show();
        }
    }

    class sendListener2 implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            String phone = phonenumber.getText().toString().trim();
            String content = sms_content.getText().toString().trim();
            SmsManager smsManager = SmsManager.getDefault();
            int count = 10;
            while (count >5){
                if (sms_content.length() > 70) {
                    List<String> contents = smsManager.divideMessage(content);
                    for (String c : contents) {
                        smsManager.sendTextMessage(phone, null, c, null, null);
                    }
                } else {
                    smsManager.sendTextMessage(phone, null, content, null, null);
                }
                count --;
                //Toast.makeText(MainActivity.this, "短信发送成功。。。", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
