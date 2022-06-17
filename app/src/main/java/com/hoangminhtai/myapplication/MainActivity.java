package com.hoangminhtai.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    Button btnDrawView;
    EditText edtPhone;
    LinearLayout row1,row2,row3,row4;

    Integer index =1;

    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);


    Handler handler = new Handler();
    Runnable UIThread = new Runnable() {
        @Override
        public void run() {
            Button btn = new Button(MainActivity.this);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    edtPhone.setText(edtPhone.getText().toString() + btn.getText());
                }
            });
            if(index <=3)
                createButton(btn,index.toString(),row1);
            if(index >3 && index<=6)
                createButton(btn,index.toString(),row2);
            if(index >6 && index<=9)
                createButton(btn,index.toString(),row3);
            if(index ==10)
                createButton(btn, "*", row4);
            if(index ==11)
                createButton(btn, "0", row4);
            if(index ==12)
                createButton(btn, "#", row4);

        }
    };

    private void createButton(Button btn, String index, LinearLayout row1) {
        btn.setLayoutParams(params);
        params.weight =1;
        btn.setText(index);
        row1.addView(btn);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linkView();
        addEvent();
    }

    private void addEvent() {
        btnDrawView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtPhone.setText("");
                runInBackground();
            }
        });
    }

    private void runInBackground() {
        row1.removeAllViews();
        row2.removeAllViews();
        row3.removeAllViews();
        row4.removeAllViews();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (Integer i=1; i<13;i++){
                    index = i;
                    handler.post(UIThread);
                    SystemClock.sleep(50);
                }
            }
        });
        thread.start();
    }

    private void linkView() {
        btnDrawView = findViewById(R.id.btnDrawView);
        edtPhone =findViewById(R.id.edtPhoneNumb);
        row1 = findViewById(R.id.row1);
        row2 = findViewById(R.id.row2);
        row3 = findViewById(R.id.row3);
        row4 = findViewById(R.id.row4);
    }
}