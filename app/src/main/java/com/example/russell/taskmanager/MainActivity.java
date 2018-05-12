package com.example.russell.taskmanager;

import android.support.v4.app.FragmentManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> strList;
    ArrayAdapterWButton adapter;
    DataBaseSQLite dataBaseSQLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //strList = new ArrayList<>();
        openDB();
        strList = dataBaseSQLite.read();

        adapter = new ArrayAdapterWButton(this, strList);

        ListView listView = findViewById(R.id.listview);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, R.string.toast, Toast.LENGTH_SHORT).show();
            }
        });

        final FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NoteFragment nf = new NoteFragment();
                FragmentManager fm = getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.note_fragment , nf).addToBackStack(null).commit();
            }
        });
    }

    @Override
    protected void onDestroy() {
        dataBaseSQLite.close();
        super.onDestroy();
    }

    private void openDB() {
        dataBaseSQLite = new DataBaseSQLite(this);
        dataBaseSQLite.open();
        System.out.println();
    }
}
