package cn.ahut.entity.code_id;

import java.util.Arrays;

public class SysmCode {
    private int id;
    private byte[] code;
    private byte[]  pid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getCode() {
        return code;
    }

    public void setCode(byte[] code) {
        this.code = code;
    }

    public byte[] getPid() {
        return pid;
    }

    @Override
    public String toString() {
        return "SysmCode{" +
                "id=" + id +
                ", code=" + Arrays.toString(code) +
                ", pid=" + Arrays.toString(pid) +
                '}';
    }

    public void setPid(byte[] pid) {
        this.pid = pid;
    }
}
