package cn.ahut.untils;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CutSQL {
    public List<String> way1(String st) {
        String startContent="[";
        String endContent="]";
        List<String> listData=new ArrayList<>();
        while(st.indexOf(startContent)>=0) {
            String sx1=st.substring(0,st.indexOf(startContent));
            listData.add(sx1);
            st=st.substring(st.indexOf(startContent)+startContent.length());
            if(st.indexOf(endContent)==0){
                System.out.println("开始标记后就是结束标记则不做处理。");
            }else if(st.indexOf(endContent)>0){
                String sx=st.substring(0,st.indexOf(endContent));
                listData.add(sx);
                st=st.substring(st.indexOf(endContent)+endContent.length());
                listData.add(st);

            }
        }
        return listData;
    }
    public List<String> way2(String st) {
        String startContent="@";
        String endContent="@";
        List<String> listData=new ArrayList<>();
        while(st.indexOf(startContent)>=0) {
            String sx1=st.substring(0,st.indexOf(startContent));
            listData.add(sx1);
            st=st.substring(st.indexOf(startContent)+startContent.length());
            if(st.indexOf(endContent)==0){
                System.out.println("开始标记后就是结束标记则不做处理。");
            }else if(st.indexOf(endContent)>0){
                String sx=st.substring(0,st.indexOf(endContent));
                listData.add(sx);
                st=st.substring(st.indexOf(endContent)+endContent.length());
                listData.add(st);

            }
        }
        return listData;
    }
    public List<String> way3(String st) {
        String startContent="<";
        String endContent=">";
        List<String> listData=new ArrayList<>();
        while(st.indexOf(startContent)>=0) {
            String sx1=st.substring(0,st.indexOf(startContent));
            listData.add(sx1);
            st=st.substring(st.indexOf(startContent)+startContent.length());
            if(st.indexOf(endContent)==0){
                System.out.println("开始标记后就是结束标记则不做处理。");
            }else if(st.indexOf(endContent)>0){
                String sx=st.substring(0,st.indexOf(endContent));
                listData.add(sx);
                st=st.substring(st.indexOf(endContent)+endContent.length());
                listData.add(st);

            }
        }
        return listData;
    }
}
