package com.example.pok.lab3_v2;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Activity_detail extends AppCompatActivity {
    int pos = 0;
    TextView name_Detail,phone_Detail;
    ImageView img_Detail;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //Create DB and Table
        db = new DatabaseHandler(this);

        Intent intent = getIntent();
        pos = intent.getExtras().getInt("position");

        name_Detail = findViewById(R.id.NameDetail);
        phone_Detail = findViewById(R.id.PhoneDetail);
        img_Detail = findViewById(R.id.imgDetail);

        List<Contact> contacts = db.getAllContacts();

        String[] datas = new String[contacts.size()];
        String[] pn    = new String[contacts.size()];
        String[] imgPaths = new String[contacts.size()];

        for(int i=pos; i< datas.length; i++)
        {
            datas[i]= contacts.get(i)._name;
            pn[i] = contacts.get(i)._phone_number;
            imgPaths[i] = contacts.get(i)._img_path;
        }
        final CustomAdapter adapter = new CustomAdapter(getApplicationContext(),datas,pn,imgPaths);

        name_Detail.setText(adapter.strName[pos]);
        phone_Detail.setText(adapter.phoneNum[pos]);
        img_Detail.setImageBitmap(BitmapFactory.decodeFile(adapter.imgPath[pos]));

    }
}
