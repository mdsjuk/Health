package cn.ahut.untils.BGNKey;


import cn.ahut.entity.key.PrivateKey;
import cn.ahut.untils.Get_Param;
import it.unisa.dia.gas.jpbc.PairingParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class get_PrivateKey {
    @Autowired
    private Get_Param getParam;

    public PrivateKey createsk(PairingParameters param) {

        BigInteger sk=param.getBigInteger("n1");
        return new PrivateKey(sk);
    }
}
