package cn.ahut.untils.DataCreate;

import cn.ahut.Mapper.personMapper;
import cn.ahut.createData.codeBuilder;
import cn.ahut.entity.code_id.*;
import cn.ahut.entity.key.PrivateKey;
import cn.ahut.entity.key.PublicKey;
import cn.ahut.untils.BGNKey.get_PrivateKey;
import cn.ahut.untils.BGNKey.get_PubilcKey;
import cn.ahut.untils.BGNOp.DoDecrypt;
import cn.ahut.untils.BGNOp.DoEncrypt;
import cn.ahut.untils.BGNOp.Doselect;
import cn.ahut.untils.Get_Param;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.PairingParameters;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
import it.unisa.dia.gas.plaf.jpbc.pairing.a1.TypeA1Pairing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 初始化表格  症状编码-病人id集合
 */

@Component
public class sysmId {

    @Autowired
    private get_PrivateKey get_sk;
    @Autowired
    private get_PubilcKey get_pk;
    @Autowired
    private Get_Param get_Param;
    @Autowired
    private Doselect ds;
    @Autowired
    private personMapper mapper;

    private PairingParameters param ;

    public void age_code() throws Exception{
        param=get_Param.getparam();

        PublicKey pk=get_pk.createpk(param);
        for(int i=1;i<=150;i++){
            FirstAge fa= new FirstAge();
            Element firstage=  DoEncrypt.encrypt(i,pk);
            byte[] age=firstage.toBytes();
            fa.setId(i);
            fa.setAge(age);
            byte[] pid= null;
            fa.setPid(pid);
            mapper.insertFristage(fa);
        }
    }
    //精神病状代码
    public void sysm_code() throws Exception{
        Element code;
        param=get_Param.getparam();
        PrivateKey sk=get_sk.createsk(param);
        PublicKey pk=get_pk.createpk(param);
        for(int j=1;j<=12;j++){
            SysmCode sysmcode=new SysmCode();
            sysmcode.setId(j);
            if(j<12){
                code=  DoEncrypt.encrypt(j,pk);
            }else {
                code = DoEncrypt.encrypt(99,pk);
            }

            byte[] sys=code.toBytes();
            sysmcode.setCode(sys);
            byte[] pid=null;
            sysmcode.setPid(pid);
            mapper.insertSysmcode(sysmcode);
        }
    }

    //住院次数
    public void hosp_code() throws Exception{
        Element code;
        param=get_Param.getparam();
        PrivateKey sk=get_sk.createsk(param);
        PublicKey pk=get_pk.createpk(param);
        for(int i=1;i<=30;i++){
            Hosp hosp= new Hosp();
            Element times=  DoEncrypt.encrypt(i,pk);
            byte[] hos=times.toBytes();
            hosp.setId(i);
            hosp.setHosps(hos);
            byte[] pid= null;
            hosp.setPid(pid);
            //  System.out.println(fa);
            mapper.inserthosp(hosp);


        }
    }
    //重性精神病代码
    public void sysch_code() throws Exception{
        Element code;
        param=get_Param.getparam();
        PrivateKey sk=get_sk.createsk(param);
        PublicKey pk=get_pk.createpk(param);
        for(int i=1;i<=7;i++){
            Syschcode syschcode=new Syschcode();
            Element c = DoEncrypt.encrypt(codeBuilder.getsychcode(i-1),pk);
            byte[] code1=c.toBytes();
            syschcode.setId(i);
            syschcode.setSychcode(code1);
            byte[] pid= null;
            syschcode.setPid(pid);
            mapper.insertsyschcode(syschcode);

        }
    }
    //肇事
    public void ihit_code() throws Exception{
        Element code;
        param=get_Param.getparam();
        PrivateKey sk=get_sk.createsk(param);
        PublicKey pk=get_pk.createpk(param);
        for(int i=0;i<=10;i++) {
            Ihit ihit= new Ihit();
            ihit.setId(i);

            code=  DoEncrypt.encrypt(i,pk);


            byte[] sys=code.toBytes();
            ihit.setHit(sys);
            byte[] pid=null;
            ihit.setPid(pid);
            mapper.inserthit(ihit);
        }
    }
    //治疗
    public void treat_code() throws Exception{
        Element code;
        param=get_Param.getparam();
        PrivateKey sk=get_sk.createsk(param);
        PublicKey pk=get_pk.createpk(param);
        for(int i=1;i<=6;i++) {
            Treat trea= new Treat();
            trea.setId(i);
            if(i<6){
                code=  DoEncrypt.encrypt(i,pk);
            }else {
                code = DoEncrypt.encrypt(9,pk);
            }

            byte[] sys=code.toBytes();
            trea.setTreat(sys);
            byte[] pid=null;
            trea.setPid(pid);
            mapper.inserttreat(trea);
        }
    }
    //自伤次数
    public void suicide_code() throws Exception{

        Element code;
        param=get_Param.getparam();
        PrivateKey sk=get_sk.createsk(param);
        PublicKey pk=get_pk.createpk(param);
        for(int i=0;i<=6;i++) {
            Isuicide isuicide=new Isuicide();
            isuicide.setId(i);

            code=  DoEncrypt.encrypt(i,pk);


            byte[] sys=code.toBytes();
            isuicide.setSucide(sys);
            byte[] pid=null;
            isuicide.setPid(pid);
            mapper.insertisuicide(isuicide);
        }
    }
    //自杀次数
    public void attempt_code() throws Exception{

        Element code;
        param=get_Param.getparam();
        PrivateKey sk=get_sk.createsk(param);
        PublicKey pk=get_pk.createpk(param);
        for(int i=0;i<=4;i++) {
            Iattempt iattempt= new Iattempt();
            iattempt.setId(i);

            code=  DoEncrypt.encrypt(i,pk);


            byte[] sys=code.toBytes();
            iattempt.setAttempt(sys);
            byte[] pid=null;
            iattempt.setPid(pid);
            mapper.insertiattempt(iattempt);
        }
    }
//验证所有集合
    public void versysmcode() throws Exception{
        param=get_Param.getparam();
        PrivateKey sk=get_sk.createsk(param);
        PublicKey pk=get_pk.createpk(param);
        TypeA1Pairing pairing = (TypeA1Pairing) PairingFactory.getPairing(param);
        //验证年龄
        List<FirstAge> selectfa = mapper.selectfa();
        System.out.println("验证年龄");
        for (FirstAge fa:selectfa){

            Element element = pairing.getG1().newElementFromBytes(fa.getAge());
            int age= DoDecrypt.decrypt(element,pk,sk);
            System.out.println(fa.getId()+"     "+age);
        }
        //验证精神病
        List<SysmCode> selectsys = mapper.selectsysmcode();
        System.out.println("精神病");
        for (SysmCode sysmcode:selectsys){

            Element element = pairing.getG1().newElementFromBytes(sysmcode.getCode());
            int code1= DoDecrypt.decrypt(element,pk,sk);
            System.out.println(sysmcode.getId()+"     "+code1);
        }
        // 验证住院次数
        List<Hosp> hosps= mapper.selecthosp();
        System.out.println("住院次数");

        System.out.println("住院");
        for (Hosp hosp:hosps){
            Element element = pairing.getG1().newElementFromBytes(hosp.getHosps());
            int code1= DoDecrypt.decrypt(element,pk,sk);
            System.out.println(+hosp.getId()+"     "+code1);
        }
        //验证重性精神病代码
        List<Syschcode> syschcodes = mapper.selectsyscode();
        System.out.println("重性精神病代码");
        for (Syschcode syschcode:syschcodes){
            Element element = pairing.getG1().newElementFromBytes(syschcode.getSychcode());
            int code1= DoDecrypt.decrypt(element,pk,sk);
            System.out.println(syschcode.getId()+"     "+code1);
        }
        //验证治疗情况
        List<Treat> treats = mapper.selecttreat();
        System.out.println("验证治疗情况");
        for(Treat treat2:treats){
            Element element = pairing.getG1().newElementFromBytes(treat2.getTreat());
            int code1= DoDecrypt.decrypt(element,pk,sk);
            System.out.println(treat2.getId()+"  "+code1);
        }
        //验证肇事
        System.out.println("验证肇事次数");
        List<Ihit> ihits = mapper.selectihit();
        for(Ihit ihit:ihits){
            Element element = pairing.getG1().newElementFromBytes(ihit.getHit());
            int code1= DoDecrypt.decrypt(element,pk,sk);
            System.out.println(ihit.getId()+"  "+code1);
        }

    }



}
