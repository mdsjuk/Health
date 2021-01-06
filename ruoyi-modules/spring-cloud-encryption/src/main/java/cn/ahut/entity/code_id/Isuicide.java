package cn.ahut.entity.code_id;

import java.util.Arrays;

public class Isuicide {
    private int id;
    private byte[] sucide;
    private byte[] pid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getSucide() {
        return sucide;
    }

    public void setSucide(byte[] sucide) {
        this.sucide = sucide;
    }

    public byte[] getPid() {
        return pid;
    }

    public void setPid(byte[] pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "Isuicide{" +
                "id=" + id +
                ", sucide=" + Arrays.toString(sucide) +
                ", pid=" + Arrays.toString(pid) +
                '}';
    }
}
