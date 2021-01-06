package cn.ahut.handleSQL;

import cn.ahut.Mapper.personMapper;
import cn.ahut.entity.code_id.Ihit;
import cn.ahut.entity.key.PrivateKey;
import cn.ahut.entity.key.PublicKey;
import cn.ahut.untils.BGNKey.get_PrivateKey;
import cn.ahut.untils.BGNKey.get_PubilcKey;
import cn.ahut.untils.BGNOp.DoDecrypt;
import cn.ahut.untils.Byt_Code;
import cn.ahut.untils.CutSQL;
import cn.ahut.untils.Get_Param;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.PairingParameters;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
import it.unisa.dia.gas.plaf.jpbc.pairing.a1.TypeA1Pairing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JudgeSql {
    @Autowired
    private CutSQL cts;
    @Autowired
    private personMapper mapper;
    @Autowired
    private get_PrivateKey get_sk;
    @Autowired
    private get_PubilcKey get_pk;
    @Autowired
    private Get_Param get_Param;

    private PairingParameters param ;
    public int handleAndRe(String sql) throws Exception{
        param= get_Param.getparam();
        PrivateKey sk=get_sk.createsk(param);
        PublicKey pk=get_pk.createpk(param);
        TypeA1Pairing pairing = (TypeA1Pairing) PairingFactory.getPairing(param);

        List<String> st= cts.way1(sql);
        String key=st.get(1);
        String other=st.get(2);
         int total=0;
        if(key.equals("select")) {
            List<String> st1 = cts.way2(other);
            String str1_1 = st1.get(0);
            String str1_2 = st1.get(1);
            String str1_3 = st1.get(2);

            if (str1_2.equals("PSYCHOSISPERSONINFO")) {
                List<String> st2 = cts.way3(str1_3);
                String str2_1 = st2.get(0);
                String str2_2 = st2.get(1);
                String str2_3 = st2.get(2);

                List<Ihit> selectihit = mapper.selectihit();
                String str2_4=str2_3.substring(1,str2_3.length());
                for (Ihit ihit:selectihit) {
                    Element element = pairing.getG1().newElementFromBytes(ihit.getHit());
                    int decrypt = DoDecrypt.decrypt(element, pk, sk);
                    if(decrypt== Integer.valueOf(str2_4)){
                        System.out.println(ihit.getPid());
                        List<String> list= Byt_Code.c_byte(ihit.getPid());
                        for (String str: list){
                            System.out.println(str);

                        }
                    }


                }
                //System.out.println(str2_4);



            }
        }

        return total;
    }

}
