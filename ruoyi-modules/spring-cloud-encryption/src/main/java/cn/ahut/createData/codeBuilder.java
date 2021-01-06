package cn.ahut.createData;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class codeBuilder {
    /**
     * 随机年龄
     * @param i 0-19
     * @return
     */
    public static int getage(int i){
        return i;
    }

    /**
     * 精神症状编码
     * @param i 0-11
     * 1-10 11 99
     * @return
     */
    public static int getssymcode(int i){
        int[] symcode={1,2,3,4,5,6,7,8,9,10,11,99};

        return symcode[i];

    }

    /**
     *住院次数
     * @param i 0-9
     * @return
     */
    public static int gethosp(int i){
        return i;
    }

    /**
     *精神病 名称
     * @param i 0-6
     * @return
     */
    public static int getsychcode(int i){
        int[] sychcode={1,2,3,4,5,6,9};
        return sychcode[i];
    }

    /**
     * 治愈情况
     * @param i 0-5
     * @return
     */
    public  static int gettreat(int i){
        int[] treatcode={1,2,3,4,5,9};
        return treatcode[i];
    }
    public static String getcountry(int i) {
        String[] country = {"雨山区", "花山区", "博望区", "当涂县", "含山县", "和县"};
        return country[i];
    }
    /**手机号码
     *
     */
    public static String getHeadMobile(Integer type) {
        switch (type) {
            case 1:
                return "130";
            case 2:
                return "131";
            case 3:
                return "132";
            case 4:
                return "133";
            case 5:
                return "134";
            case 6:
                return "135";
            case 7:
                return "136";
            case 8:
                return "137";
            case 9:
                return "138";
            case 10:
                return "139";
            case 11:
                return "150";
            case 12:
                return "151";
            case 13:
                return "152";
            case 14:
                return "153";
            case 15:
                return "155";
            case 16:
                return "156";
            case 17:
                return "157";
            case 18:
                return "158";
            case 19:
                return "159";
            case 20:
                return "177";
            case 21:
                return "186";
            case 22:
                return "183";
            case 23:
                return "187";
            case 24:
                return "188";
            case 25:
                return "189";
            default:
                return "173";
        }
    }

    /**
     * 获取尾号4位
     *
     * @return
     */
    public static String getEndMobile() {
        String ychar = "0,1,2,3,4,5,6,7,8,9";
        int wei = 8;
        String[] ychars = ychar.split(",");
        String endMobile = "";
        Random rdm = new Random();
        for (int i = 0; i < wei; i++) {
            int j = (rdm.nextInt() >>> 1) % 10;
            if (j > 10)
                j = 0;
            endMobile = endMobile + ychars[j];
        }
        return endMobile;

    }

    public static String getMobilePhone() {
        Integer headRandom = new Random().nextInt(25);
        String mobile = getHeadMobile(headRandom) + getEndMobile();
        return mobile;
    }
    /**
     * 随访意见
     *
     */
    public static String getadvice(int i) {
        String[] advice = {"随访，注意休息", "注意饮食，经常锻炼", "不要急躁，保持乐观心态", "多运动，按时吃药"};
        return advice[i];
    }

    /**
     *
     */
    public static Date randomDate(){
        String beginDate ="2010-01-01";
        String endDate="2020-01-01";
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date start = format.parse(beginDate);
            Date end = format.parse(endDate);

            if(start.getTime() >= end.getTime()){
                return null;
            }
            long date = random(start.getTime(),end.getTime());
            return new Date(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static long random(long begin,long end){
        long rtn = begin + (long)(Math.random() * (end - begin));
        if(rtn == begin || rtn == end){
            return random(begin,end);
        }
        return rtn;
    }


}



