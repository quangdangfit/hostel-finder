package com.example.quangdang.hostelfinder;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quangdang.hostelfinder.Class.Generate;
import com.example.quangdang.hostelfinder.Class.Hostel;

public class HostelDetailActivity extends AppCompatActivity {

    ImageView img;
    TextView tvdiaChi;
    TextView tvgia;
    TextView tvdienTich;
    TextView tvsdt;
    TextView tvstar;
    TextView tvintro;
    Button btnmap;
    Button btncall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostel_detail);
        img = findViewById(R.id.imghinhDetail);
        tvdiaChi = findViewById(R.id.tvdiaChiDetail);
        tvgia = findViewById(R.id.tvgiaDetail);
        tvdienTich = findViewById(R.id.tvdienTichDetail);
        tvsdt = findViewById(R.id.tvsdtDetail);
        tvstar = findViewById(R.id.tvstarDetail);
        tvintro = findViewById(R.id.tvintroDetail);
        btnmap = findViewById(R.id.btnmapDetail);
        btncall = findViewById(R.id.btngoiDetail);

        Intent intent = getIntent();
        int id = intent.getIntExtra("hostelID", 0);

        Generate gen = new Generate();

        final Hostel hostel = gen.getHostelById(id);
        img.setImageResource(hostel.getImage());
        tvdiaChi.setText("Địa chỉ: " + hostel.getAddress());
        tvgia.setText("Giá: " + hostel.getPrice() + " VNĐ");
        tvdienTich.setText("Diện tích: " + hostel.getArea() + " m2");
        tvsdt.setText("SĐT: " + hostel.getPhoneNum());
        tvstar.setText("Đánh giá: " + hostel.getStarNum() + " sao");
        tvintro.setText(hostel.getIntro());

        btncall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:" + hostel.getPhoneNum()));
                if (ActivityCompat.checkSelfPermission(HostelDetailActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(i);
            }
        });

        btnmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HostelDetailActivity.this, MapsActivity.class);
                intent.putExtra("latitude", hostel.getLatitude());
                intent.putExtra("longitude", hostel.getLongitude());
                startActivity(intent);
            }
        });
    }
}
