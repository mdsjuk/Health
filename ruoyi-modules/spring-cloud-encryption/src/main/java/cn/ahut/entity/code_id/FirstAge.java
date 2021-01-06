package cn.ahut.entity.code_id;

import java.util.Arrays;

public class FirstAge {
    private int id;
    private byte[] age;
    private byte[] pid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getAge() {
        return age;
    }

    public void setAge(byte[] age) {
        this.age = age;
    }

    public byte[] getPid() {
        return pid;
    }

    public void setPid(byte[] pid) {
        this.pid = pid;
    }

    @Override
    public String  toString() {
        return "FirstAge{" +
                "id=" + id +
                ", age=" + Arrays.toString(age) +
                ", pid=" + Arrays.toString(pid) +
                '}';
    }
}
