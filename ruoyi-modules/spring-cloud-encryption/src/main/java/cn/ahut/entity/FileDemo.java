package cn.ahut.entity;

/**
 * Created by wzw on 2020/11/8.
 */
public class FileDemo {
    private String name;
    private String ctName;
    private String upDate;
    private String fileSize;

    public FileDemo() {
    }

    public FileDemo(String name, String ctName, String upDate, String fileSize) {
        this.name = name;
        this.ctName = ctName;
        this.upDate = upDate;
        this.fileSize = fileSize;
    }

    @Override
    public String toString() {
        return "FileDemo{" +
                "name='" + name + '\'' +
                ", ctName='" + ctName + '\'' +
                ", upDate='" + upDate + '\'' +
                ", fileSize='" + fileSize + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCtName() {
        return ctName;
    }

    public void setCtName(String ctName) {
        this.ctName = ctName;
    }

    public String getUpDate() {
        return upDate;
    }

    public void setUpDate(String upDate) {
        this.upDate = upDate;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }
}
