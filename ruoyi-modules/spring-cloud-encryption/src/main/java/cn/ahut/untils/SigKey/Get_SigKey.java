package cn.ahut.untils.SigKey;

import cn.ahut.Mapper.personMapper;
import cn.ahut.untils.AddLength;
import cn.ahut.untils.BGNKey.get_PrivateKey;
import cn.ahut.untils.BGNKey.get_PubilcKey;
import cn.ahut.untils.Element_Untils;
import cn.ahut.untils.File_Until;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.PairingParameters;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
import it.unisa.dia.gas.plaf.jpbc.pairing.a1.TypeA1Pairing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class Get_SigKey {
    @Autowired
    private personMapper mapper;
    @Autowired
    private get_PrivateKey get_sk;
    @Autowired
    private get_PubilcKey get_pk;
    @Autowired
    private cn.ahut.untils.Get_Param get_Param;
    @Autowired
    AddLength addLen;
    @Autowired
    private Element_Untils getElementBytes;

    private PairingParameters param ;

    public BigInteger get_Prk() throws  Exception{
        File_Until fu=new File_Until();
        String path = this.getClass().getClassLoader().getResource("prk.md").getPath();

        BigInteger prk=fu.file_int(path);
        return prk;
    }
    public Element getpbk() throws Exception{
        param=get_Param.getparam();
        TypeA1Pairing pairing = (TypeA1Pairing) PairingFactory.getPairing(param);
        Field<Element> Field_G = pairing.getG1();
        Element R = Field_G.newElementFromBytes(getElementBytes.getB_g("Element_R.md")).getImmutable();
        return R.pow(get_Prk());

    }
    public Element getv() throws Exception{

        param=get_Param.getparam();
        TypeA1Pairing pairing = (TypeA1Pairing) PairingFactory.getPairing(param);
        Field<Element> Field_G = pairing.getG1();
        Element R = Field_G.newElementFromBytes(getElementBytes.getB_g("Element_R.md")).getImmutable();
        return R;

    }

}
