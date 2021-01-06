package cn.ahut.entity.code_id;


import java.util.Arrays;

public class Syschcode {
    private int id;
    private byte[] sychcode;
    private byte[] pid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getSychcode() {
        return sychcode;
    }

    public void setSychcode(byte[] sychcode) {
        this.sychcode = sychcode;
    }

    public byte[] getPid() {
        return pid;
    }

    public void setPid(byte[] pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "Syschcode{" +
                "id=" + id +
                ", sychcode=" + Arrays.toString(sychcode) +
                ", pid=" + Arrays.toString(pid) +
                '}';
    }
}

