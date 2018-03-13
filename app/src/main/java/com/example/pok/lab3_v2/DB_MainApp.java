package com.example.pok.lab3_v2;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DB_MainApp extends AppCompatActivity {

    Button addBtn;
    Button viewBtn;
    Context context;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db__main_app);

        addBtn = findViewById(R.id.addBtnID);
        viewBtn = findViewById(R.id.viewBtnID);
        textView = findViewById(R.id.textView1);

        context = this.getApplicationContext();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Week8_DBAdd.class);
                startActivity(intent);
            }
        });


        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Week8_DBView.class);
                startActivity(intent);
            }
        });

        isExternalStorageWritable();
    }

    public void isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            textView.setText("External Storage Writable : True");
        } else {
            textView.setText("External Storage Writable : Write Permission Failed (False)");
            Toast.makeText(this, "You must allow permission write external storage to your mobile device.", Toast.LENGTH_SHORT).show();
        }
    }
}
