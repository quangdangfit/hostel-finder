package com.example.quangdang.hostelfinder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quangdang.hostelfinder.Class.Hostel;

import java.util.ArrayList;

public class HostelAdapter extends BaseAdapter{
    private Context context;
    private int layout;
    private ArrayList<Hostel> hostelArrayList;

    public HostelAdapter(Context context, int layout, ArrayList<Hostel> hostelArrayList) {
        this.context = context;
        this.layout = layout;
        this.hostelArrayList = hostelArrayList;
    }

    @Override
    public int getCount() {
        return hostelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout, null);

        // Ánh xạ view
        ImageView imghinh = convertView.findViewById(R.id.imghinh);
        TextView tvdiaChi = convertView.findViewById(R.id.tvdiaChi);
        TextView tvdienTich = convertView.findViewById(R.id.tvdienTich);
        TextView tvgia = convertView.findViewById(R.id.tvgia);

        // Gán giá trị
        Hostel hostel = hostelArrayList.get(position);

        imghinh.setImageResource(hostel.getImage());
        tvdiaChi.setText(hostel.getAddress());
        tvdienTich.setText("Diện tích: " + Float.toString(hostel.getArea()) + " m2");
        tvgia.setText("Giá: " + Integer.toString(hostel.getPrice()) + " VNĐ");

        return convertView;
    }
}
