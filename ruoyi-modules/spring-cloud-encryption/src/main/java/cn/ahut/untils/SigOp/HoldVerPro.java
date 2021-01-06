package cn.ahut.untils.SigOp;

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
import com.alibaba.fastjson.JSONObject;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.PairingParameters;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
import it.unisa.dia.gas.plaf.jpbc.pairing.a1.TypeA1Pairing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class HoldVerPro {
    @Autowired
    private get_PrivateKey get_sk;
    @Autowired
    private get_PubilcKey get_pk;
    @Autowired
    private Get_Param get_Param;
    @Autowired
    private AddLength addLen;
    @Autowired
    private Get_SigKey sigKey;

    private PairingParameters param ;
    public void cipherGen(JSONObject mJson,JSONObject cJson) throws Exception {

        PrivateKey sk = get_sk.createsk(get_Param.getparam());
        PublicKey pk = get_pk.createpk(get_Param.getparam());
        param = get_Param.getparam();
        byte[] bt1 = new byte[258];
        String path1 = mJson.getString("dir");
        String name1= mJson.getString("filename");


        TypeA1Pairing pairing = (TypeA1Pairing) PairingFactory.getPairing(param);
        File_Until fwrite = new File_Until();

        byte[] allbyte = fwrite.readFile(path1, name1);
        System.out.println(allbyte.length);
        //ecodebyte存储加密后的byte字节码
        double d1 = System.currentTimeMillis();
        byte[] ecodebyte = new byte[0];
        int i = 0;
        for (byte bt : allbyte) {
            int bt2 = bt & 0xFF;

            Element encrypt = DoEncrypt.encrypt(bt2, pk);
            byte[] bytes = encrypt.toBytes();
            int length = ecodebyte.length;
            ecodebyte = addLen.addBtLength(ecodebyte, 258);
            System.arraycopy(bytes, 0, ecodebyte, length, bytes.length);
            System.arraycopy(ecodebyte, length, bt1, 0, 258);

        }
        //加密后写入
        String path=cJson.getString("dir");
        String name=cJson.getString("filename");
        fwrite.createFile(ecodebyte,path,name);
        //读加密文件
        //encodebt 取出加密文件的字节码
        double d2=System.currentTimeMillis();
        System.out.println("加密文件"+( d2-d1)+"ms");

    }
    public void sigGen(JSONObject mJson,JSONObject sJson)  throws Exception{
        byte[] bt1=new byte[258];
        param=get_Param.getparam();
        PrivateKey sk=get_sk.createsk(get_Param.getparam());
        PublicKey pk=get_pk.createpk(get_Param.getparam());
        TypeA1Pairing pairing = (TypeA1Pairing) PairingFactory.getPairing(param);
        String path1=mJson.getString("dir");
        String name1=mJson.getString("filename");
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
        String path=sJson.getString("dir");
        String name=sJson.getString("filename");
        fwrite.createFile(ecodebyte,path,name);
        System.out.println("数字签名生成并保存"+(t2-t1)+"ms");


    }
    public boolean chanLlAge(JSONObject cJson,JSONObject sJson) throws Exception{
        byte[] bt1=new byte[258];
        param=get_Param.getparam();
        PrivateKey sk=get_sk.createsk(get_Param.getparam());
        PublicKey pk=get_pk.createpk(get_Param.getparam());
        TypeA1Pairing pairing = (TypeA1Pairing) PairingFactory.getPairing(param);
        BigInteger prk = sigKey.get_Prk();
        Element pbk = sigKey.getpbk();

        String path2=sJson.getString("dir");
        String name2=sJson.getString("filename");
        String path3=cJson.getString("dir");
        String name3=cJson.getString("filename");


        File_Until fwrite= new File_Until();

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
            return true;
        }
        double d2= System.currentTimeMillis();
        System.out.println(d2-d1+"ms");
        System.out.println(pairing1);
        System.out.println(pairing2);
        return false;


    }
    public void deCipher(JSONObject cJson,JSONObject deJson) throws Exception{
        param=get_Param.getparam();
        int read_length=0;
        byte[] decode=new byte[0];
        PrivateKey sk = get_sk.createsk(get_Param.getparam());
        PublicKey pk = get_pk.createpk(get_Param.getparam());
        TypeA1Pairing pairing = (TypeA1Pairing) PairingFactory.getPairing(param);
        File_Until fWrite = new File_Until();
        String path = cJson.getString("dir");
        String name = cJson.getString("filename");
        String path1 = deJson.getString("dir");
        String name1 = deJson.getString("filename");
        byte[] encodebt = fWrite.readFile(path, name);
        double t1=System.currentTimeMillis();
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
        double t2=System.currentTimeMillis();
        System.out.println();
        fWrite.writeFile(decode,path1,name1);
        System.out.println("解密密文件"+(t2-t1)+"ms");

    }

}
