package cn.ahut;


import cn.ahut.Mapper.personMapper;
import cn.ahut.entity.key.PrivateKey;
import cn.ahut.entity.key.PublicKey;
import cn.ahut.untils.AddLength;
import cn.ahut.untils.BGNKey.get_PrivateKey;
import cn.ahut.untils.BGNKey.get_PubilcKey;
import cn.ahut.untils.BGNOp.DoAdd;
import cn.ahut.untils.BGNOp.DoDecrypt;
import cn.ahut.untils.BGNOp.DoEncrypt;
import cn.ahut.untils.Get_Param;
import cn.ahut.untils.SigKey.Get_SigKey;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.PairingParameters;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
import it.unisa.dia.gas.plaf.jpbc.pairing.a1.TypeA1Pairing;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.spec.PKCS8EncodedKeySpec;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={NscrlV1Application.class})
public class demo {
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
    @Autowired
    Get_SigKey sigKey;

    private PairingParameters param ;
    @Test
    public void test1() throws Exception{

//        param=get_Param.getparam();
//        TypeA1Pairing pairing = (TypeA1Pairing) PairingFactory.getPairing(param);
//        System.out.println(sigKey.get_Prk());
//        System.out.println(sigKey.getpbk());
        param=get_Param.getparam();
        PrivateKey sk=get_sk.createsk(get_Param.getparam());
        PublicKey pk=get_pk.createpk(get_Param.getparam());
        TypeA1Pairing pairing = (TypeA1Pairing) PairingFactory.getPairing(param);
        Element[] count = new Element[10];
        int[] sum = new int[1];
        int o=3;
        int i=1;
        Element element=DoEncrypt.encrypt(o, pk);
        Element element2=DoEncrypt.encrypt(i,pk);
        Element element1=DoEncrypt.encrypt(6, pk);
        count[0]=element2;

        element=DoAdd.add(element,count[0]);
        element=DoAdd.add(element,element1);
        System.out.println(DoDecrypt.decrypt(element,pk,sk));
    }
}
