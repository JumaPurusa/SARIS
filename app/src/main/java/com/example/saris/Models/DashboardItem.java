package com.example.saris.Models;


import com.example.saris.R;

import java.util.ArrayList;

public class DashboardItem {

    private int image;
    private String text;

    public static DashboardItem[] dashboardItems = {
            new DashboardItem(R.drawable.admission, "Admission"),
            new DashboardItem(R.drawable.accomodation, "Accommodation"),
            new DashboardItem(R.drawable.timetable, "Timetable"),
            new DashboardItem(R.drawable.accademicrecord, "Results"),
            new DashboardItem(R.drawable.elearning, "E-Learning"),
            new DashboardItem(R.drawable.evoting, "E-Voting"),
            new DashboardItem(R.drawable.communication, "Communication"),
            new DashboardItem(R.drawable.security, "Security")
    };


    public DashboardItem(int image, String text){
        this.image = image;
        this.text = text;
    }
    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
