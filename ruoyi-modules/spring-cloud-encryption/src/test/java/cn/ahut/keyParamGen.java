package cn.ahut;



import cn.ahut.untils.Element_Untils;
import cn.ahut.untils.Get_Param;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.PairingParameters;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
import it.unisa.dia.gas.plaf.jpbc.pairing.a1.TypeA1Pairing;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes={NscrlV1Application.class})
public class keyParamGen {

    @Autowired
    private Get_Param get_Param;


    @Test
    public void BGNEleGen() throws Exception{
        PairingParameters param = get_Param.getparam();
        TypeA1Pairing pairing = (TypeA1Pairing) PairingFactory.getPairing(param);
        Field<Element> Field_G = pairing.getG1();
        Element element = Field_G.newRandomElement();
        byte[] bytes = element.toBytes();
        Element_Untils eu= new Element_Untils();
        eu.writeBytesToFile(bytes,"d:/keyGen/Element_G.md");



    }

}
