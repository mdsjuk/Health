package cn.ahut.entity.code_id;

import java.util.Arrays;


public class Hosp {
    private int id;
    private byte[] hosps;
    private byte[] pid;


    @Override
    public String toString() {
        return "Hosp{" +
                "id=" + id +
                ", hosp=" + Arrays.toString(hosps) +
                ", pid=" + Arrays.toString(pid) +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getHosps() {
        return hosps;
    }

    public void setHosps(byte[] hosp) {
        this.hosps = hosp;
    }

    public byte[] getPid() {
        return pid;
    }

    public void setPid(byte[] pid) {
        this.pid = pid;
    }
}
