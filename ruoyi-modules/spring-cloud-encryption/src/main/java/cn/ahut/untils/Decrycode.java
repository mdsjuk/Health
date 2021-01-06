package cn.ahut.untils;

import cn.ahut.entity.key.PrivateKey;
import cn.ahut.entity.key.PublicKey;
import cn.ahut.untils.BGNOp.DoDecrypt;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.PairingParameters;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
import it.unisa.dia.gas.plaf.jpbc.pairing.a1.TypeA1Pairing;

public class Decrycode {



    private PairingParameters param ;
    public static int decrypt(byte[] byt, PairingParameters param,PrivateKey sk,PublicKey pk) throws Exception{

        TypeA1Pairing pairing = (TypeA1Pairing) PairingFactory.getPairing(param);
        Element element = pairing.getG1().newElementFromBytes(byt);
        return DoDecrypt.decrypt(element,pk,sk);
    }
}
