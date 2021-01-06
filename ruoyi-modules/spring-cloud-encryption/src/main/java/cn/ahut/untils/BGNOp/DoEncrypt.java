package cn.ahut.untils.BGNOp;


import cn.ahut.entity.key.PublicKey;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;

import java.math.BigInteger;

public class DoEncrypt {
    public static Element encrypt(int m, PublicKey pubkey)
            throws Exception {
//        if (m > 200) {
//            throw new Exception(
//                    "BGN.encrypt(int m, PublicKey pubkey): "
//                            + "plaintext m is not in [0,1,2,...,"+
//                             "]");
//        }
        Pairing pairing = pubkey.getPairing();
        Element g = pubkey.getG();
        Element h = pubkey.getH();
        BigInteger r = pairing.getZr().newRandomElement()
                .toBigInteger();
        return g.pow(BigInteger.valueOf(m)).mul(h.pow(r))
                .getImmutable();
    }
}
