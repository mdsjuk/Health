package cn.ahut.untils.BGNOp;


import cn.ahut.entity.key.PrivateKey;
import cn.ahut.entity.key.PublicKey;
import it.unisa.dia.gas.jpbc.Element;

import java.math.BigInteger;

public class DoDecrypt {
    public static int decrypt(Element c, PublicKey pubkey,
                              PrivateKey prikey) throws Exception {
        BigInteger p = prikey.getP();
        Element g = pubkey.getG();
        Element cp = c.pow(p).getImmutable();
        Element gp = g.pow(p).getImmutable();
        for (int i = 0; i <= 10000000; i++) {
            if (gp.pow(BigInteger.valueOf(i)).isEqual(cp)) {
                return i;
            }
        }
        throw new Exception(
                "BGN.decrypt(Element c, PublicKey pubkey, PrivateKey prikey): "
                        + "plaintext m is not in [0,1,2,...,100"+
                        "]");
    }
}
