package cn.ahut.untils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Get_date_year {
    public  static String year(Date date){
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();

        c.setTime(date);
        int year=c.get(Calendar.YEAR);
        String str=year+"";
        return str;


    }
}
