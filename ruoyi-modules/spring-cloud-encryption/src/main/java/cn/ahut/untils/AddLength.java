package cn.ahut.untils;

import org.springframework.stereotype.Component;

@Component
public class AddLength {
    public  byte[] addBtLength(byte[] array,int length){
        byte[] newArray = new byte[array.length+length];
        System.arraycopy(array,0,newArray,0,array.length);
        return newArray;
    }
    public  int[] addIntLength(int[] array){
        int[] newArray = new int[array.length+1];
        System.arraycopy(array,0,newArray,0,array.length);
        return newArray;
    }

}
