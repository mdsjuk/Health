package cn.ahut.data_check;

import cn.ahut.Mapper.personMapper;
import cn.ahut.untils.BGNKey.get_PrivateKey;
import cn.ahut.untils.BGNKey.get_PubilcKey;
import it.unisa.dia.gas.jpbc.PairingParameters;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

public class Readfile {



    @Autowired
    private personMapper mapper;
    @Autowired
    private get_PrivateKey get_sk;
    @Autowired
    private get_PubilcKey get_pk;
    @Autowired
    private cn.ahut.untils.Get_Param get_Param;
    private PairingParameters param;


    public static void main(String[] args) {
        byte[] allbyte = null;
        try {
            //读取输入的文件====文件输入流
            FileInputStream fis = new FileInputStream("d:/a.txt");
            //字节数组输出流  在内存中创建一个字节数组缓冲区，所有输出流的数据都会保存在字符数组缓冲区中
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int len;
            byte[] buffer = new byte[1024];
            //将文件读入到字节数组中
            while ((len = fis.read(buffer)) != -1) {
                // 将指定字节数组中从偏移量 off 开始的 len 个字节写入此字节数组输出流。
                baos.write(buffer, 0, len);
            }
            allbyte = baos.toByteArray();//当前输出流的拷贝  拷贝到指定的字节数组中

            fis.close();
            baos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        int m=97;

        for (byte bt: allbyte){
            System.out.println(bt);

        }


    }
}
