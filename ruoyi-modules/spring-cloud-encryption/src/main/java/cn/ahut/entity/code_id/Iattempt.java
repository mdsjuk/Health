package cn.ahut.entity.code_id;

import java.util.Arrays;

public class Iattempt {
    private int id;
    private byte[] attempt;
    private byte[] pid;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getAttempt() {
        return attempt;
    }

    public void setAttempt(byte[] attempt) {
        this.attempt = attempt;
    }

    public byte[] getPid() {
        return pid;
    }

    public void setPid(byte[] pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "Iattempt{" +
                "id=" + id +
                ", attempt=" + Arrays.toString(attempt) +
                ", pid=" + Arrays.toString(pid) +
                '}';
    }
}
