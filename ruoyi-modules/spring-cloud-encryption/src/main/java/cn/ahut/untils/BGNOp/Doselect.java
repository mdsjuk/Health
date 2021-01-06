package cn.ahut.untils.BGNOp;


import cn.ahut.entity.key.PrivateKey;
import cn.ahut.entity.key.PublicKey;
import it.unisa.dia.gas.jpbc.Element;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class Doselect {
    public boolean select(Element c, PublicKey pubkey,
                              PrivateKey prikey, BigInteger s) throws Exception {
        boolean flag=false;
        BigInteger p = prikey.getP();
        Element g = pubkey.getG();
        Element cp = c.pow(p).getImmutable();
        Element gp = g.pow(p).getImmutable();
        if (cp.isEqual(gp.pow(s))) {

            flag=true;

        }
        return flag;
    }

}
