package cn.ahut.untils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Map_list_code {
    public static Map<Integer, List<String>> code_sno(Integer key,Map<Integer, List<String>> map_code,String sno){
        if(map_code.get(key)==null){
            List<String> codelist= new ArrayList<>();
            codelist.add(sno);
            map_code.put(key,codelist);
        }else{
            List<String> codelist=map_code.get(key) ;
            codelist.add(sno);
            map_code.put(key,codelist);
        }
        return map_code;
    }
}
