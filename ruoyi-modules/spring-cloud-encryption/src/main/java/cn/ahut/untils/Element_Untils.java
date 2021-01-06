package cn.ahut.untils;

import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class Element_Untils {


    private  byte[] b_g;
    public  byte[] getB_g(String str) throws Exception {
        String element_g = this.getClass().getClassLoader().getResource(str).getPath();
        File f = new File(element_g);
        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(f));
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len = 0;
            while (-1 != (len = in.read(buffer, 0, buf_size))) {
                bos.write(buffer, 0, len);
            }

            return bos.toByteArray();


        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            bos.close();
        }

    }

    public  void writeBytesToFile(byte[] bytes, String path) throws IOException{
        File file= new File(path);
        if(!file.exists()){
            file.getParentFile().mkdirs();//不存在则创建父目录
            file.createNewFile();
        }
        OutputStream out = new FileOutputStream(file);
        InputStream is = new ByteArrayInputStream(bytes);
        byte[] buff = new byte[1024];
        int len = 0;
        while((len=is.read(buff))!=-1){
            out.write(buff, 0, len);
        }
        is.close();
        out.close();
    }
}
