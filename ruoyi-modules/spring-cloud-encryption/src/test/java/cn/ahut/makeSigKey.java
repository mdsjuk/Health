package cn.ahut;


import cn.ahut.Mapper.personMapper;
import cn.ahut.untils.AddLength;
import cn.ahut.untils.BGNKey.get_PrivateKey;
import cn.ahut.untils.BGNKey.get_PubilcKey;
import cn.ahut.untils.Element_Untils;
import cn.ahut.untils.File_Until;
import cn.ahut.untils.Get_Param;
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
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={NscrlV1Application.class})
public class makeSigKey {
    @Autowired
    private personMapper mapper;
    @Autowired
    private get_PrivateKey get_sk;
    @Autowired
    private get_PubilcKey get_pk;
    @Autowired
    private Get_Param get_Param;
    @Autowired
    AddLength addLen;

    private PairingParameters param ;
    @Test
    public void test1() throws Exception{
        //私钥
        File_Until fwrite= new File_Until();
        BigInteger var1= new BigInteger(8, new Random());
        System.out.println(var1);
        fwrite.int_file(var1,"d:/keyGen/sig/","prk.md");
        //fwrite.file_int("d:/","prk.txt");
        param=get_Param.getparam();
        TypeA1Pairing pairing = (TypeA1Pairing) PairingFactory.getPairing(param);
        Element element = pairing.getG1().newRandomElement();
        byte[] bytes = element.toBytes();
        Element_Untils eu= new Element_Untils();
        System.out.println(element);
        System.out.println(element.toString());
        eu.writeBytesToFile(bytes, "d:/keyGen/sig/Element_R.md");

    }
}
