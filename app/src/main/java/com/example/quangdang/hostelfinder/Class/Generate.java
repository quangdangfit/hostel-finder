package com.example.quangdang.hostelfinder.Class;

import com.example.quangdang.hostelfinder.R;

import java.util.ArrayList;

public class Generate {
    private ArrayList<Hostel> hostelArrayList = new ArrayList<>();

    public Generate() {
        Hostel hostel = new Hostel(
                0, "14/8 Trần Văn Nửa, Phường Linh Tây, Quận Thủ Đức, TP. HCM",
                new Float(20.2), 3500000, "Nhà trọ gần chợ Thủ Đức, gần các tuyến xe bus số 8, 29, 141",
                4, "01667551021", new Float(10.853418), new Float(106.752568), R.drawable.a1,
                "TP. Hồ Chí Minh", "Quận Thủ Đức");
        hostelArrayList.add(hostel);
        hostel = new Hostel(
                1, "111 Tô Ngọc Vân, Phường Linh Tây, Quận Thủ Đức, TP. HCM",
                new Float(50.5), 5500000, "Nhà trọ gần chợ Thủ Đức, gần các tuyến xe bus số 89, 29, 141, gần đường" +
                "Phạm Văn Đồng, cách đại học Sư Phạm Kỹ Thuật 3km",
                3, "0251655615", new Float(10.853031), new Float(106.752093), R.drawable.a2,
                "TP. Hồ Chí Minh", "Quận Thủ Đức");
        hostelArrayList.add(hostel);
        hostel = new Hostel(
                2, "Chung Cư Linh Tây, Đường D1, Phước Linh Tây, Thủ Đức, Hồ Chí Minh",
                new Float(30), 5000000, "Chung cư nguyên căn, giá rẻ phù hợp với học sinh, sinh viên",
                5, "082012544", new Float(10.862261), new Float(106.758832), R.drawable.a3,
                "TP. Hồ Chí Minh", "Quận Thủ Đức");
        hostelArrayList.add(hostel);
    }

    public ArrayList<Hostel> getHostelArrayList() {
        return hostelArrayList;
    }

    public ArrayList<Hostel> getHostelInCity(String cityName){
        ArrayList<Hostel> result = new ArrayList<>();
        for (int i=0; i < hostelArrayList.size(); i++){
            if (hostelArrayList.get(i).getCityName().equals(cityName)) {
                result.add(hostelArrayList.get(i));
            }
        }
        return result;
    }

    public ArrayList<Hostel> getHostelInDist(String cityName, String distName){
        ArrayList<Hostel> hostelInCity = getHostelInCity(cityName);
        if (distName.equals("Tất cả")){
            return hostelInCity;
        }else {
            ArrayList<Hostel> result = new ArrayList<>();
            for (int i = 0; i < hostelInCity.size(); i++){
                if (hostelInCity.get(i).getDistName().equals(distName)) {
                    result.add(hostelInCity.get(i));
                }
            }
            return result;
        }
    }

    public Hostel getHostelById(int id){
        for (int i = 0; i<hostelArrayList.size(); i++){
            if (hostelArrayList.get(i).getId() == id)
                return hostelArrayList.get(i);
        }
        return null;
    }

}
