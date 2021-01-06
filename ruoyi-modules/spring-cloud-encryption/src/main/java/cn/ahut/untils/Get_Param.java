package cn.ahut.untils;

import it.unisa.dia.gas.jpbc.PairingParameters;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
import org.springframework.stereotype.Component;


@Component
public class Get_Param {
    private  PairingParameters param;
    public  PairingParameters getparam() {
        String pairingUrl = getClass().getClassLoader().getResource("pairing.properties").getPath();

        param = PairingFactory.getPairingParameters(pairingUrl);
        return param;
    }




}
