package cn.ahut.untils.DataOp;

import breeze.linalg.sum;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class JsbOp {
    @Autowired
    private personMapper mapper;
    @Autowired
    private get_PrivateKey get_sk;
    @Autowired
    private get_PubilcKey get_pk;
    @Autowired
    private Get_Param get_Param;

    private PairingParameters param ;
    /*
    年均肇事次数
     */
    public Map<String,String> zscs(Date d1, Date d2) throws Exception{
        param=get_Param.getparam();
        PrivateKey sk=get_sk.createsk(get_Param.getparam());
        PublicKey pk=get_pk.createpk(get_Param.getparam());
        TypeA1Pairing pairing = (TypeA1Pairing) PairingFactory.getPairing(param);
        Map<String,String> map= new HashMap<String,String>();
        List<PersonInfo> persons=mapper.selectAll();
        String year1=new SimpleDateFormat("yyyy").format(d1);
        String year2=new SimpleDateFormat("yyyy").format(d2);

        int y1=Integer.parseInt(year1);
        int y2=Integer.parseInt(year2);

        int time=y2-y1;

        if(time==0){

            Element count=pairing.getG1().newElement(1);
            int sum=0;
            for(PersonInfo person:persons){


                Date dfilltime = person.getDfilltime();
                String year= Get_date_year.year(dfilltime);

                if((y1+"").equals(year)){

                    count = DoAdd.add(count ,pairing.getG1().newElementFromBytes(person.getIhit()));
                    sum+=1;
                }

            }
            double ave= (DoDecrypt.decrypt(count,pk,sk)) /sum;
            map.put(y1+"",ave+"");
        }else {

            time = time + 1;
            Element[] count = new Element[time];
            int[] sum = new int[time];
            //初始化数组
            //Arrays.fill(sum,0);
         //   Arrays.fill(count,pairing.getG1().newElement(1));
            for (int i=0;i<time;i++){
                count[i]=pairing.getG1().newElement(1);
                sum[i]=0;
            }
            for (PersonInfo person : persons) {

                Date dfilltime = person.getDfilltime();
                //System.out.println(person.getSno());
                String year = Get_date_year.year(dfilltime);
                int y=Integer.parseInt(year);
                if (y1<=y&&y<=y2) {

                    int j=y-y2+time-1;
                    count[j] = DoAdd.add(count[j],
                            pairing.getG1().newElementFromBytes(person.getIhit()));
                    sum[j]+=1;

                }


            }
            for (int i=0;i<time;i++){
                DecimalFormat df = new DecimalFormat("0.00");//格式化小数
                String ave= df.format((float)DoDecrypt.decrypt(count[i],pk,sk)/sum[i]);//返回的是String类型


                map.put(y1+i+"",ave+"");
            }
        }
        return map;
    }
    /*
    年均住院次数
     */
    public Map<String,String> zycs(Date d1, Date d2) throws Exception{
        param=get_Param.getparam();
        PrivateKey sk=get_sk.createsk(get_Param.getparam());
        PublicKey pk=get_pk.createpk(get_Param.getparam());
        TypeA1Pairing pairing = (TypeA1Pairing) PairingFactory.getPairing(param);
        Map<String,String> map= new HashMap<String,String>();
        List<PersonInfo> persons=mapper.selectAll();
        String year1=new SimpleDateFormat("yyyy").format(d1);
        String year2=new SimpleDateFormat("yyyy").format(d2);
        int y1=Integer.parseInt(year1);
        int y2=Integer.parseInt(year2);
        int time=y2-y1;

        if(time==0){

            Element count=pairing.getG1().newElement(1);
            int sum=0;
            for(PersonInfo person:persons){


                Date dfilltime = person.getDfilltime();
                String year= Get_date_year.year(dfilltime);

                if((y1+"").equals(year)){

                    count = DoAdd.add(count ,pairing.getG1().newElementFromBytes(person.getIhospitalizations()));
                    sum+=1;
                }

            }
            double ave= (DoDecrypt.decrypt(count,pk,sk)) /sum;
            map.put(y1+"",ave+"");
        }else {

            time = time + 1;
            Element[] count = new Element[time];
            int[] sum = new int[time];
            //初始化数组
            //Arrays.fill(sum,0);
            //   Arrays.fill(count,pairing.getG1().newElement(1));
            for (int i=0;i<time;i++){
                count[i]=pairing.getG1().newElement(1);
                sum[i]=0;
            }
            for (PersonInfo person : persons) {

                Date dfilltime = person.getDfilltime();
                //System.out.println(person.getSno());
                String year = Get_date_year.year(dfilltime);
                int y=Integer.parseInt(year);
                if (y1<=y&&y<=y2) {

                    int j=y-y2+time-1;
                    count[j] = DoAdd.add(count[j],
                            pairing.getG1().newElementFromBytes(person.getIhospitalizations()));
                    sum[j]+=1;

                }


            }
            for (int i=0;i<time;i++){
                DecimalFormat df = new DecimalFormat("0.00");//格式化小数
                String ave= df.format((float)DoDecrypt.decrypt(count[i],pk,sk)/sum[i]);//返回的是String类型

                System.out.println(DoDecrypt.decrypt(count[i],pk,sk)+"value="+sum[i]);
                map.put(y1+i+"",ave+"");
            }
        }
        return map;
    }

    /**
     * 年均自伤次数
     * @param d1
     * @param d2
     * @return
     * @throws Exception
     */
    public Map<String,String> zshangcs(Date d1, Date d2) throws Exception{
        param=get_Param.getparam();
        PrivateKey sk=get_sk.createsk(get_Param.getparam());
        PublicKey pk=get_pk.createpk(get_Param.getparam());
        TypeA1Pairing pairing = (TypeA1Pairing) PairingFactory.getPairing(param);
        Map<String,String> map= new HashMap<String,String>();
        List<PersonInfo> persons=mapper.selectAll();
        String year1=new SimpleDateFormat("yyyy").format(d1);
        String year2=new SimpleDateFormat("yyyy").format(d2);
        int y1=Integer.parseInt(year1);
        int y2=Integer.parseInt(year2);
        int time=y2-y1;

        if(time==0){

            Element count=pairing.getG1().newElement(1);
            int sum=0;
            for(PersonInfo person:persons){


                Date dfilltime = person.getDfilltime();
                String year= Get_date_year.year(dfilltime);

                if((y1+"").equals(year)){

                    count = DoAdd.add(count ,pairing.getG1().newElementFromBytes(person.getIsuicide()));
                    sum+=1;
                }

            }
            double ave= (DoDecrypt.decrypt(count,pk,sk)) /sum;
            map.put(y1+"",ave+"");
        }else {

            time = time + 1;
            Element[] count = new Element[time];
            int[] sum = new int[time];
            //初始化数组
            //Arrays.fill(sum,0);
            //   Arrays.fill(count,pairing.getG1().newElement(1));
            for (int i=0;i<time;i++){
                count[i]=pairing.getG1().newElement(1);
                sum[i]=0;
            }
            for (PersonInfo person : persons) {

                Date dfilltime = person.getDfilltime();
                //System.out.println(person.getSno());
                String year = Get_date_year.year(dfilltime);
                int y=Integer.parseInt(year);
                if (y1<=y&&y<=y2) {

                    int j=y-y2+time-1;
                    System.out.println(DoDecrypt.decrypt(pairing.getG1().newElementFromBytes(person.getIsuicide()),pk,sk));
                    count[j] = DoAdd.add(count[j],
                            pairing.getG1().newElementFromBytes(person.getIsuicide()));
                    sum[j]+=1;

                }


            }
            for (int i=0;i<time;i++){
                DecimalFormat df = new DecimalFormat("0.00");//格式化小数
                String ave= df.format((float)DoDecrypt.decrypt(count[i],pk,sk)/sum[i]);//返回的是String类型
                System.out.println(DoDecrypt.decrypt(count[i],pk,sk)+"value="+sum[i]);
                map.put(y1+i+"",ave+"");
            }
        }
        return map;
    }

    /**
     * 自杀次数
     * @param d1
     * @param d2
     * @return
     * @throws Exception
     */
    public Map<String,String> zshacs(Date d1, Date d2) throws Exception{
        param=get_Param.getparam();
        PrivateKey sk=get_sk.createsk(get_Param.getparam());
        PublicKey pk=get_pk.createpk(get_Param.getparam());
        TypeA1Pairing pairing = (TypeA1Pairing) PairingFactory.getPairing(param);
        Map<String,String> map= new HashMap<String,String>();
        List<PersonInfo> persons=mapper.selectAll();
        String year1=new SimpleDateFormat("yyyy").format(d1);
        String year2=new SimpleDateFormat("yyyy").format(d2);
        int y1=Integer.parseInt(year1);
        int y2=Integer.parseInt(year2);
        int time=y2-y1;

        if(time==0){

            Element count=pairing.getG1().newElement(1);
            int sum=0;
            for(PersonInfo person:persons){


                Date dfilltime = person.getDfilltime();
                String year= Get_date_year.year(dfilltime);

                if((y1+"").equals(year)){

                    count = DoAdd.add(count ,pairing.getG1().newElementFromBytes(person.getIattemptedsuicide()));
                    sum+=1;
                }

            }
            double ave= (DoDecrypt.decrypt(count,pk,sk)) /sum;
            map.put(y1+"",ave+"");
        }else {

            time = time + 1;
            Element[] count = new Element[time];
            int[] sum = new int[time];
            //初始化数组
            //Arrays.fill(sum,0);
            //   Arrays.fill(count,pairing.getG1().newElement(1));
            for (int i=0;i<time;i++){
                count[i]=pairing.getG1().newElement(1);
                sum[i]=0;
            }
            for (PersonInfo person : persons) {

                Date dfilltime = person.getDfilltime();
                //System.out.println(person.getSno());
                String year = Get_date_year.year(dfilltime);
                int y=Integer.parseInt(year);
                if (y1<=y&&y<=y2) {

                    int j=y-y2+time-1;
                    count[j] = DoAdd.add(count[j],
                            pairing.getG1().newElementFromBytes(person.getIattemptedsuicide()));
                    sum[j]+=1;

                }


            }
            for (int i=0;i<time;i++){
                DecimalFormat df = new DecimalFormat("0.00");//格式化小数
                String ave= df.format((float)DoDecrypt.decrypt(count[i],pk,sk)/sum[i]);//返回的是String类型

                System.out.println(DoDecrypt.decrypt(count[i],pk,sk)+"value="+sum[i]);
                map.put(y1+i+"",ave+"");
            }
        }
        return map;
    }
}
