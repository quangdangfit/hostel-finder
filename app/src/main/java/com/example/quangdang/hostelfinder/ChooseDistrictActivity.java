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

public class ChooseDistrictActivity extends AppCompatActivity {

    Spinner spnDist;
    ArrayList<String> allDistNames;
    DatabaseHandler db;
    Button btnSearch;
    String cityName;
    String distName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_district);
        db = new DatabaseHandler(this);
        btnSearch = findViewById(R.id.btn_dist_search);

        Intent intent = getIntent();
        cityName = intent.getStringExtra("cityName");
        setSpinnerDist(cityName);

        spnDist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                distName = allDistNames.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                distName = spnDist.getSelectedItem().toString();
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseDistrictActivity.this, HostelListActivity.class);
                intent.putExtra("cityName", cityName);
                intent.putExtra("distName", distName);
                startActivity(intent);
            }
        });
    }


    public void setSpinnerDist(String city_value){
        spnDist = findViewById(R.id.spn_dist);
        String[] tphcm = {"Quận 1", "Quận 2", "Quận 3", "Quận 4", "Quận 5", "Quận 6", "Quận 7", "Quận 8",
                "Quận 9", "Quận 10", "Quận 11", "Quận 12", "Quận Thủ Đức", "Quận Bình Thạnh", "Quận Gò Vấp",
                "Quận Phú Nhuận", "Quận Tân Phú", "Quận Bình Tân", "Quận Tân Bình", "Huyện Nhà Bè",
                "Huyện Bình Chánh", "Huyện Hóc Môn", "Huyện Củ Chi", "Huyện Cần Giờ"};
        String[] hn = {"Quận Ba Đình", "Quận Hoàn Kiếm", "Quận Hai Bà Trưng", "Quận Đống Đa",
                "Quận Tây Hồ", "Quận Cầu Giấy", "Quận Thanh Xuân", "Quận Hoàng Mai", "Quận Long Biên",
                "Quận Bắc Từ Liêm", "Huyện Thanh Trì", "Huyện Gia Lâm", "Huyện Đông Anh", "Huyện Sóc Sơn",
                "Quận Hà Đông", "Thị xã Sơn Tây", "Huyện Ba Vì", "Huyện Phúc Thọ", "Huyện Thạch Thất",
                "Huyện Quốc Oai", "Huyện Chương Mỹ", "Huyện Đan Phượng", "Huyện Hoài Đức", "Huyện Thanh Oai",
                "Huyện Mỹ Đức", "Huyện Ứng Hòa", "Huyện Thường Tín", "Huyện Phú Xuyên", "Huyện Mê Linh",
                "Quận Nam Từ Liêm"};
        String[] danang = {"Quận Hải Châu", "Quận Thanh Khê", "Quận Sơn Trà", "Quận Ngũ Hành Sơn",
                "Quận Liên Chiểu", "Quận Cẩm Lệ", "Huyện Hòa Vang"};
        allDistNames = new ArrayList<String>();
        allDistNames.add("Tất cả");
        switch (city_value){
            case "TP. Hồ Chí Minh":
                for (int i = 0; i < tphcm.length; i++){
                    allDistNames.add(tphcm[i]);
                }
                break;
            case "Hà Nội":
                for (int i = 0; i < hn.length; i++){
                    allDistNames.add(hn[i]);
                }
                break;
            case "TP. Đà Nẵng":
                for (int i = 0; i < danang.length; i++){
                    allDistNames.add(danang[i]);
                }
                break;
        }
        ArrayAdapter<String> distAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spiner_item, allDistNames);
        spnDist.setAdapter(distAdapter);
        spnDist.setSelection(allDistNames.indexOf("Tất cả"));
    }
}
