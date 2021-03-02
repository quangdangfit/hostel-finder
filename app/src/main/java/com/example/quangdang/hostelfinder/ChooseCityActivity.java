package com.example.quangdang.hostelfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.quangdang.hostelfinder.Class.DatabaseHandler;

import java.util.ArrayList;

public class ChooseCityActivity extends AppCompatActivity {
    DatabaseHandler db;
    Spinner spnCity;
    Spinner spnDist;
    Button btnnext;
    ArrayList<String> allCityNames;
    String cityName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_city);
        btnnext = findViewById(R.id.btn_city_next);

        setSpinnerCity();

        spnCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cityName = allCityNames.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                cityName = spnCity.getSelectedItem().toString();
            }
        });

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseCityActivity.this, ChooseDistrictActivity.class);
                intent.putExtra("cityName", cityName);
                startActivity(intent);
            }
        });
    }

    public void setSpinnerCity(){
        spnCity = findViewById(R.id.spn_city);
        allCityNames = new ArrayList<String>();
        allCityNames.add("Hà Nội");
        allCityNames.add("TP. Hồ Chí Minh");
        allCityNames.add("TP. Đà Nẵng");
        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spiner_item, allCityNames);
        spnCity.setAdapter(cityAdapter);
        spnCity.setSelection(this.allCityNames.indexOf("TP. Hồ Chí Minh"));
    }
}
