package com.example.pok.lab3_v2;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Locale;

public class Week8_DBAdd extends AppCompatActivity {

    private static int RESULT_LOAD_IMAGE = 1;

    Button insertBtn;
    Button browseImgBtn;
    EditText nameEdt;
    EditText phoneEdt;
    TextView imgPathTxt;
    String imgPath = "";
    int[] imageId;
    private boolean Formatting;
    private int After;
    private Context context;

    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week8__dbadd);


        insertBtn = findViewById(R.id.insertBtn);
        browseImgBtn = findViewById(R.id.browseImgBtnID);
        nameEdt = findViewById(R.id.nameEdt);
        phoneEdt = findViewById(R.id.phoneEdt);
        imgPathTxt = findViewById(R.id.imgTxtID);
        context = this;

        //Create DB and Table
        db = new DatabaseHandler(this);

        phoneEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                After = i2; // เช็คการกด backspace
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!Formatting) {
                    Formatting = true;
                    if(After != 0) //กรณีที่ไม่ได้กด backspace ใช้ format US และกำหนด length ถ้าไทยจะเป็น getDefault() แต่จะไม่ได้ผลลัพธ์ที่ต้องการคือ xxx-xxx-xxxx
                        PhoneNumberUtils.formatNumber(editable, PhoneNumberUtils.getFormatTypeForLocale(Locale.US));
                    Formatting = false;
                }
            }
        });

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Contact person = new Contact();
                person._name = nameEdt.getText().toString();
                person._phone_number = phoneEdt.getText().toString();
                person._img_path = imgPathTxt.getText().toString();

                db.updateOrInsert(person);
                finish();
            }
        });

        browseImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {

            Uri selectedImage = data.getData();

            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);

            //picturePath is the path to save in DB
            imgPath = cursor.getString(columnIndex);
            imgPathTxt.setText(imgPathTxt.getText() + imgPath);
            cursor.close();

            ImageView imageView = findViewById(R.id.imageView3);
            //imageView.setImageBitmap(BitmapFactory.decodeFile(imgPath));
           Glide.with(this).load(imgPath).into(imageView);
        }
    }
}
