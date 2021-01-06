package cn.ahut.untils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Byt_Code {
    public static byte[] byt_c(List<String> lists)throws Exception {
        byte[] std;
        ByteArrayOutputStream byt=new ByteArrayOutputStream();
        ObjectOutputStream obj=new ObjectOutputStream(byt);
        obj.writeObject(lists);
        std=byt.toByteArray();
        return std;
    }
    public static List<String> c_byte(byte[] pid)throws Exception {

        ByteArrayInputStream byt=new ByteArrayInputStream(pid);
        ObjectInputStream obj = new ObjectInputStream(byt);
        List<String> list = castList(obj.readObject(), String.class);
        return list;

    }
    public static  <T> List<T> castList(Object obj, Class<T> c)
    {
        List<T> result = new ArrayList<T>();
        if(obj instanceof List<?>)
        {
            for (Object o : (List<?>) obj)
            {
                result.add(c.cast(o));
            }
            return result;
        }
        return null;
    }
}
