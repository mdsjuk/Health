package cn.ahut.entity.code_id;

import java.util.Arrays;

public class Ihit {
    private int id;
    private byte[] hit;
    private byte[] pid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getHit() {
        return hit;
    }

    public void setHit(byte[] hit) {
        this.hit = hit;
    }

    public byte[] getPid() {
        return pid;
    }

    public void setPid(byte[] pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "Ihit{" +
                "id=" + id +
                ", hit=" + Arrays.toString(hit) +
                ", pid=" + Arrays.toString(pid) +
                '}';
    }
}
