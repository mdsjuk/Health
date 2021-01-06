package cn.ahut;


import cn.ahut.Mapper.personMapper;
import cn.ahut.entity.key.PrivateKey;
import cn.ahut.entity.key.PublicKey;
import cn.ahut.untils.AddLength;
import cn.ahut.untils.BGNKey.get_PrivateKey;
import cn.ahut.untils.BGNKey.get_PubilcKey;
import cn.ahut.untils.BGNOp.DoDecrypt;
import cn.ahut.untils.BGNOp.DoEncrypt;
import cn.ahut.untils.File_Until;
import cn.ahut.untils.Get_Param;
import cn.ahut.untils.SigKey.Get_SigKey;
import cn.ahut.untils.SigOp.HoldVerPro;
import com.alibaba.fastjson.JSONObject;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.PairingParameters;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
import it.unisa.dia.gas.plaf.jpbc.pairing.a1.TypeA1Pairing;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;


@RunWith(SpringRunner.class)
@SpringBootTest(classes={NscrlV1Application.class})
public class Test3 {
    @Autowired
    private personMapper mapper;
    @Autowired
    private get_PrivateKey get_sk;
    @Autowired
    private get_PubilcKey get_pk;
    @Autowired
    private Get_Param get_Param;
    @Autowired
    HoldVerPro hvp;
    @Autowired
    AddLength addLen;
    @Autowired
    Get_SigKey sigKey;

    private PairingParameters param ;
    //加密文件
    @Test
    public void test1() throws Exception{
        byte[] bt1=new byte[258];
        param=get_Param.getparam();
        String path1="d:/test/";
        String name1="timg.jpg";
        PrivateKey sk=get_sk.createsk(get_Param.getparam());
        PublicKey pk=get_pk.createpk(get_Param.getparam());
        TypeA1Pairing pairing = (TypeA1Pairing) PairingFactory.getPairing(param);
        File_Until fwrite= new File_Until();

        byte[] allbyte = fwrite.readFile(path1,name1);
        System.out.println(allbyte.length);
        //ecodebyte存储加密后的byte字节码
        double d1= System.currentTimeMillis();
        byte[] ecodebyte=new byte[0];
        int i=0;
        for (byte bt: allbyte){
            Element encrypt = DoEncrypt.encrypt(bt, pk);
            byte[] bytes = encrypt.toBytes();
            int length=ecodebyte.length;
            ecodebyte=addLen.addBtLength(ecodebyte,258);
            System.arraycopy(bytes,0,ecodebyte,length,bytes.length);
            System.arraycopy(ecodebyte,length,bt1,0,258);




        }

        //加密后写入
        String path="d:/test/";
        String name="en.jpg";
        fwrite.createFile(ecodebyte,path,name);
        //读加密文件
        //encodebt 取出加密文件的字节码
        byte[] encodebt = fwrite.readFile(path, name);



        int read_length=0;
        byte[] decode=new byte[0];
        int de_length=0;
        //解密文件块
        while(read_length<encodebt.length){
            byte[] bt= new byte[258];
            System.arraycopy(encodebt,read_length,bt,0,258);
            Element element = pairing.getG1().newElementFromBytes(bt);
            decode=addLen.addBtLength(decode,1);
            int m= DoDecrypt.decrypt(element,pk,sk);
            byte var= (byte) m;

            decode[decode.length-1]=var;
            read_length+=258;
        }
        double d2=System.currentTimeMillis();
        System.out.println(d2-d1+"ms");
        int j=0;
        for(byte bt:allbyte){
            System.out.println(bt+"   "+decode[j++]);
        }
//        if(decode.equals(allbyte)){
//            System.out.println("success");
//        }



    }
    //标签生成
    @Test
    public void sigGer() throws  Exception{
        byte[] bt1=new byte[258];
        param=get_Param.getparam();
        PrivateKey sk=get_sk.createsk(get_Param.getparam());
        PublicKey pk=get_pk.createpk(get_Param.getparam());
        TypeA1Pairing pairing = (TypeA1Pairing) PairingFactory.getPairing(param);
        String path1="d:/";
        String name1="a.txt";
        File_Until fwrite= new File_Until();
        byte[] allbyte = fwrite.readFile(path1,name1);
        BigInteger prk = sigKey.get_Prk();
        Element pbk = sigKey.getpbk();
        //ecodebyte存储数据签名的byte字节码
        double t1=System.currentTimeMillis();
        byte[] ecodebyte=new byte[0];
        int i=0;
        for (byte bt: allbyte){
            Element encrypt = DoEncrypt.encrypt(bt, pk);
            encrypt = encrypt.pow(sigKey.get_Prk());
            byte[] bytes = encrypt.toBytes();
            int length=ecodebyte.length;
            ecodebyte=addLen.addBtLength(ecodebyte,258);
            System.arraycopy(bytes,0,ecodebyte,length,bytes.length);
            System.arraycopy(ecodebyte,length,bt1,0,258);

        }
        double t2= System.currentTimeMillis();
        String path="d:/";
        String name="sig.txt";
        fwrite.createFile(ecodebyte,path,name);
        System.out.println(t2-t1+"ms");

    }
    @Test
    public void chanllgeSig() throws  Exception{
        byte[] bt1=new byte[258];
        param=get_Param.getparam();
        PrivateKey sk=get_sk.createsk(get_Param.getparam());
        PublicKey pk=get_pk.createpk(get_Param.getparam());
        TypeA1Pairing pairing = (TypeA1Pairing) PairingFactory.getPairing(param);
        BigInteger prk = sigKey.get_Prk();
        Element pbk = sigKey.getpbk();
        String path1="d:/";
        String name1="a.txt";
        String path2="d:/";
        String name2="sig.txt";
        String path3="d:/";
        String name3="en.txt";
        File_Until fwrite= new File_Until();
        byte[] allbyte = fwrite.readFile(path1,name1);
        byte[] allbyte2 = fwrite.readFile(path2,name2);
        byte[] allbyte3 = fwrite.readFile(path3,name3);

//        System.out.println(allbyte.length);
//        System.out.println(allbyte2.length);

        //挑战随机数集合
        double d1=System.currentTimeMillis();
        int[] chan=new int[]{2,3,4,5};
        //ecodebyte存储数据签名的byte字节码
        //计算标签和
        Element sigpow=pairing.getG1().newElement();
        for (int i:chan){
            byte[] bt= new byte[258];
            System.arraycopy(allbyte2,(i-1)*258,bt,0,258);
            Element element = pairing.getG1().newElementFromBytes(bt);
            element=element.pow(sk.getP());

            if(chan[0]==i){
                sigpow=element;
                System.out.println(element);
            }else{
                sigpow.mul(element);
            }
           // System.out.print(DoDecrypt.decrypt(element,pk,sk)+"  ");


        }
        //计算文件块和
        Element cadd=pairing.getG1().newElement();
        for (int i:chan) {
            byte[] bt = new byte[258];
            System.arraycopy(allbyte3, (i - 1) * 258, bt, 0, 258);
            Element element = pairing.getG1().newElementFromBytes(bt);
            if (chan[0] == i) {
                cadd = element;
            } else {
                cadd.mul(element);
            }
        }
        BigInteger q=new BigInteger(String.valueOf(chan.length));
        System.out.println(q);
        Element pairing1 = pairing.pairing(pbk, cadd.pow(sk.getP()));
        Element pairing2 = pairing.pairing(sigKey.getv(), sigpow);
        System.out.println();
        if (pairing1.equals(pairing2)){
            System.out.println("success");
        }
        double d2= System.currentTimeMillis();
        System.out.println(d2-d1+"ms");
        System.out.println(pairing1);
        System.out.println(pairing2);

//        System.out.println();
//        for(byte bt:allbyte){
//            System.out.print(bt+"   ");
//        }




    }
    @Test
    public void hvpTest() throws  Exception{
        JSONObject mJson=new JSONObject();
        JSONObject cJson=new JSONObject();
        JSONObject sJson=new JSONObject();
        JSONObject deJson=new JSONObject();

        mJson.put("dir","d:/");
        mJson.put("filename","a.txt");
        cJson.put("dir","d:/");
        cJson.put("filename","en.txt");
        sJson.put("dir","d:/");
        sJson.put("filename","sig.txt");
        deJson.put("dir","d:/");
        deJson.put("filename","de.txt");

        hvp.cipherGen(mJson,cJson);
        hvp.deCipher(cJson,deJson);

    }
    @Test
    public void hvpTest2() throws  Exception{
        JSONObject mJson=new JSONObject();
        JSONObject cJson=new JSONObject();
        JSONObject sJson=new JSONObject();
        JSONObject deJson=new JSONObject();

        mJson.put("dir","d:/test/");
        mJson.put("filename","timg.jpg");
        cJson.put("dir","d:/test/");
        cJson.put("filename","en.jpg");
        sJson.put("dir","d:/test/");
        sJson.put("filename","sig.jpg");
        deJson.put("dir","d:/test/");
        deJson.put("filename","de.jpg");

        hvp.cipherGen(mJson,cJson);
        hvp.deCipher(cJson,deJson);
        hvp.sigGen(mJson,sJson);

    }
}