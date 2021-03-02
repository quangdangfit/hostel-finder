package com.example.quangdang.hostelfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.quangdang.hostelfinder.Class.DatabaseHandler;
import com.example.quangdang.hostelfinder.Class.Generate;
import com.example.quangdang.hostelfinder.Class.Hostel;

import java.util.ArrayList;

public class HostelListActivity extends AppCompatActivity {
    DatabaseHandler db;
    TextView txt;
    String cityName;
    String ditsName;
    ArrayList<Hostel> hostelArrayList;

    ListView listViewHostel;
    HostelAdapter hostelAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostel_list);
        db = new DatabaseHandler(this);
        listViewHostel = findViewById(R.id.lvhostel);

        Intent intent = getIntent();
        cityName = intent.getStringExtra("cityName");
        ditsName = intent.getStringExtra("distName");

        Generate gen = new Generate();

        hostelArrayList = gen.getHostelInDist(cityName, ditsName);

        hostelAdapter = new HostelAdapter(this, R.layout.listview_layout, hostelArrayList);
        listViewHostel.setAdapter(hostelAdapter);

        listViewHostel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(HostelListActivity.this, HostelDetailActivity.class);
                intent.putExtra("hostelID", hostelArrayList.get(position).getId());
                startActivity(intent);
            }
        });
    }
}
