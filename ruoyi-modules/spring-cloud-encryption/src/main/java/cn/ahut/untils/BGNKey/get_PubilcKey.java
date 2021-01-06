package cn.ahut.untils.BGNKey;


import cn.ahut.entity.key.PublicKey;
import cn.ahut.untils.Element_Untils;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.PairingParameters;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
import it.unisa.dia.gas.plaf.jpbc.pairing.a1.TypeA1Pairing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.math.BigInteger;
import java.util.Properties;

@Component
public class get_PubilcKey{
    @Autowired
    private Element_Untils getElementBytes;

    public PublicKey createpk(PairingParameters param) throws Exception  {


        TypeA1Pairing pairing = (TypeA1Pairing) PairingFactory.getPairing(param);
        BigInteger n = param.getBigInteger("n");
        BigInteger q = param.getBigInteger("n0");
        BigInteger p = param.getBigInteger("n1");
        Field<Element> Field_G = pairing.getG1();
        Field<Element> Field_GT = pairing.getGT();
        Properties properties = new Properties();
        // 使用InPutStream流读取properties文件
        BufferedReader bufferedReader = null;


        Element g = Field_G.newElementFromBytes(getElementBytes.getB_g("Element_G.md")).getImmutable();
        Element h = g.pow(q).getImmutable();

        return new PublicKey(n, Field_G, Field_GT, pairing, g, h);


    }


}
