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
    List<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

        context = this;
        //Create DB and Table
        db = new DatabaseHandler(this);

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        contacts = db.getAllContacts();

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

                int[] _id = new int[contacts.size()];
                for(int i = position;i < _id.length;i++){
                    _id[i] = contacts.get(i)._id;
                }
                final CustomAdapter adapter = new CustomAdapter(_id);
                final int posID = adapter.id[position];

                builder.setTitle("Choose the one you want.");
                builder.setMessage("Are you sure you want to delete ID: " + posID);
                builder.setIcon(R.drawable.mut);
                builder.setCancelable(false);
                builder.setPositiveButton("Show", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplication(),Activity_detail.class);
                        intent.putExtra("position",position);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.deleteContact(new Contact(posID));
                        listView.invalidateViews();// refresh listview
                        //adapter.notifyDataSetChanged();

                        Toast.makeText(getApplicationContext(),"Delete ID: " + posID + " successfully",Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

}
