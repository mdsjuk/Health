package cn.ahut.entity;

/**
 * Created by wzw on 2020/11/2.
 */
public class DictInfo {
    private int dm;
    private String dmsm;

    public DictInfo(int dm, String dmsm) {
        this.dm = dm;
        this.dmsm = dmsm;
    }

    public DictInfo() {
    }

    @Override
    public String toString() {
        return "DictInfo{" +
                "dm=" + dm +
                ", dmsm='" + dmsm + '\'' +
                '}';
    }

    public int getDm() {
        return dm;
    }

    public void setDm(int dm) {
        this.dm = dm;
    }

    public String getDmsm() {
        return dmsm;
    }

    public void setDmsm(String dmsm) {
        this.dmsm = dmsm;
    }
}
