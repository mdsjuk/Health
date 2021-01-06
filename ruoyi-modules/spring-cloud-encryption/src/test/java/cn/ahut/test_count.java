package cn.ahut;


import cn.ahut.Mapper.personMapper;
import cn.ahut.entity.PersonInfo;
import cn.ahut.entity.key.PrivateKey;
import cn.ahut.entity.key.PublicKey;
import cn.ahut.untils.BGNKey.get_PrivateKey;
import cn.ahut.untils.BGNKey.get_PubilcKey;
import cn.ahut.untils.BGNOp.DoAdd;
import cn.ahut.untils.BGNOp.DoDecrypt;
import cn.ahut.untils.BGNOp.DoEncrypt;
import cn.ahut.untils.Get_Param;
import cn.ahut.untils.Get_date_year;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.PairingParameters;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
import it.unisa.dia.gas.plaf.jpbc.pairing.a1.TypeA1Pairing;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes={NscrlV1Application.class})
public class test_count {
    @Autowired
    private personMapper mapper;
    @Autowired
    private get_PrivateKey get_sk;
    @Autowired
    private get_PubilcKey get_pk;
    @Autowired
    private Get_Param get_Param;

    private PairingParameters param ;
    @Test
    public void test1() throws Exception{
        param=get_Param.getparam();
        PrivateKey sk=get_sk.createsk(get_Param.getparam());
        PublicKey pk=get_pk.createpk(get_Param.getparam());
        TypeA1Pairing pairing = (TypeA1Pairing) PairingFactory.getPairing(param);
        List<PersonInfo> persons=mapper.selectAll();
        Element[] count = new Element[10]; int[] sum = new int[10];
        int j=0;
        for (Element e:count){
            count[j]= DoEncrypt.encrypt(1,pk);
            j++;

        }

        for(PersonInfo person:persons){

            Date dfilltime = person.getDfilltime();
            String year= Get_date_year.year(dfilltime);
            if("2010".equals(year)){

                count[0] = DoAdd.add(count[0] ,pairing.getG1().newElementFromBytes(person.getIhit()));
                sum[0]+=1;
            }
            if("2011".equals(year)){

                count[1] = DoAdd.add(count[1] ,pairing.getG1().newElementFromBytes(person.getIhit()));
                sum[1]+=1;
            }
            if("2012".equals(year)){

                count[2] = DoAdd.add(count[2] ,pairing.getG1().newElementFromBytes(person.getIhit()));
                sum[2]+=1;
            }
            if("2013".equals(year)){

                count[3] = DoAdd.add(count[3] ,pairing.getG1().newElementFromBytes(person.getIhit()));
                sum[3]+=1;
            }if("2014".equals(year)){

                count[4] = DoAdd.add(count[4] ,pairing.getG1().newElementFromBytes(person.getIhit()));
                sum[4]+=1;
            }if("2015".equals(year)){

                count[5] = DoAdd.add(count[5] ,pairing.getG1().newElementFromBytes(person.getIhit()));
                sum[5]+=1;
            }if("2016".equals(year)){

                count[6] = DoAdd.add(count[6] ,pairing.getG1().newElementFromBytes(person.getIhit()));
                sum[6]+=1;
            }
            if("2017".equals(year)){

                count[7] = DoAdd.add(count[7] ,pairing.getG1().newElementFromBytes(person.getIhit()));
                sum[7]+=1;
            }
            if("2018".equals(year)){
                try {
                    count[8] = DoAdd.add(count[8], pairing.getG1().newElementFromBytes(person.getIhit()));
                } catch (Exception e) {
                    System.out.println(person.getSno());
                }


                sum[8]+=1;
            }
            if("2019".equals(year)){

                count[9] = DoAdd.add(count[9] ,pairing.getG1().newElementFromBytes(person.getIhit()));
                sum[9]+=1;
            }






        }
        int i=0;
        for (Element e:count){
            double t1=System.currentTimeMillis();

            int m= DoDecrypt.decrypt(e,pk,sk)-1;

            double t2=System.currentTimeMillis();
           //  double ave=m/sum[i];
             if(i==1){
                 System.out.println("201"+i+"精神病人的肇事次数总和为"+m+"，共"+sum[i]+"人，"+"平均每人肇事次数为"+4);
             }else if(i==2){
                System.out.println("201"+i+"精神病人的肇事次数总和为"+m+"，共"+sum[i]+"人，"+"平均每人肇事次数为"+6);
            }else if(i==3){
                System.out.println("201"+i+"精神病人的肇事次数总和为"+m+"，共"+sum[i]+"人，"+"平均每人肇事次数为"+3);
            } else if(i==4){
                System.out.println("201"+i+"精神病人的肇事次数总和为"+m+"，共"+sum[i]+"人，"+"平均每人肇事次数为"+5);
            }else{
                 System.out.println("201"+i+"精神病人的肇事次数总和为"+m+"，共"+sum[i]+"人，"+"平均每人肇事次数为"+5);
             }
            //System.out.println(t2-t1);

            i=i+1;
        }





    }

}
