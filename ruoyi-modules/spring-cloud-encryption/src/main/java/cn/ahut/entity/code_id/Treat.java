package cn.ahut.entity.code_id;

import java.util.Arrays;

public class Treat {
    private int id;
    private byte[] treat;
    private byte[] pid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getTreat() {
        return treat;
    }

    public void setTreat(byte[] treat) {
        this.treat = treat;
    }

    public byte[] getPid() {
        return pid;
    }

    public void setPid(byte[] pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "Treat{" +
                "id=" + id +
                ", treat=" + Arrays.toString(treat) +
                ", pid=" + Arrays.toString(pid) +
                '}';
    }
}
