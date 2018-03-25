package com.example.pok.lab3_v2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class Week8_DBView extends AppCompatActivity {
    Context context;
    ListView listView;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

        context = this;
        //Create DB and Table
        db = new DatabaseHandler(this);

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        List<Contact> contacts = db.getAllContacts();

        String[] datas = new String[contacts.size()];
        String[] pn    = new String[contacts.size()];
        String[] imgPaths = new String[contacts.size()];

        for(int i=0; i<datas.length; i++)
        {
            datas[i]= contacts.get(i)._name;
            pn[i] = contacts.get(i)._phone_number;
            imgPaths[i] = contacts.get(i)._img_path;
        }

        final CustomAdapter adapter = new CustomAdapter(getApplicationContext(),datas,pn,imgPaths);
        listView = findViewById(R.id.listView1);

        listViewDialog(builder);
        listView.setAdapter(adapter);
    }

    private void listViewDialog(final AlertDialog.Builder builder) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                builder.setTitle("Choose the one you want.");
                builder.setIcon(R.drawable.mut);
                builder.setCancelable(false);
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        builder.setMessage("Are you sure you want to delete " + position);
                        db.deleteContact(new Contact(position));
                        listView.invalidateViews();// refresh listview
                        //adapter.notifyDataSetChanged();

                        Toast.makeText(getApplicationContext(),"Delete successfully",Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Show", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplication(),Activity_detail.class);
                        intent.putExtra("position",position);
                        startActivity(intent);
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

}
