package cn.ahut;


import cn.ahut.Mapper.personMapper;
import cn.ahut.createData.NameBuilder;
import cn.ahut.createData.codeBuilder;
import cn.ahut.entity.PersonInfo;
import cn.ahut.entity.code_id.*;
import cn.ahut.entity.key.PrivateKey;
import cn.ahut.entity.key.PublicKey;
import cn.ahut.untils.BGNKey.get_PrivateKey;
import cn.ahut.untils.BGNKey.get_PubilcKey;
import cn.ahut.untils.BGNOp.DoDecrypt;
import cn.ahut.untils.BGNOp.DoEncrypt;
import cn.ahut.untils.BGNOp.Doselect;
import cn.ahut.untils.Byt_Code;
import cn.ahut.untils.Get_Param;
import cn.ahut.untils.Map_list_code;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.PairingParameters;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
import it.unisa.dia.gas.plaf.jpbc.pairing.a1.TypeA1Pairing;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


@RunWith(SpringRunner.class)
@SpringBootTest(classes={NscrlV1Application.class})
public class test {

    @Autowired
    private personMapper mapper;
    @Autowired
    private get_PrivateKey get_sk;
    @Autowired
    private get_PubilcKey get_pk;
    @Autowired
    private Get_Param get_Param;
    @Autowired
    private Doselect ds;

    private PairingParameters param ;


    /**
     * 产生数据
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
        param= get_Param.getparam();
       PrivateKey sk=get_sk.createsk(param);
       PublicKey pk=get_pk.createpk(param);
       List<PersonInfo> persons=mapper.selectAll();
       //PersonInfo demo=persons.get(0);
      /*  int  code=20;
        Element a= doEncrypt.encrypt(code,pk);
        byte[] bytes = a.toBytes();*/
        //
        System.out.println("开始"+System.currentTimeMillis());
        Random random= new Random();
        String demo1=null;
        Map<Integer,List<String>> ageMap= new HashMap<Integer, List<String>>();
        byte[] byt_age;

        Map<Integer,List<String>> symcodeMap= new HashMap<Integer, List<String>>();
        byte[] byt_sysmcode;
        Map<Integer,List<String>> hospMap= new HashMap<Integer, List<String>>();
        byte[] byt_hosp;
        Map<Integer,List<String>> syschMap= new HashMap<Integer, List<String>>();
        byte[] byt_sysch;
        Map<Integer,List<String>> treatMap= new HashMap<Integer, List<String>>();
        byte[] byt_treat;
        Map<Integer,List<String>> ihitMap= new HashMap<Integer, List<String>>();
        byte[] byt_ihit;
        Map<Integer,List<String>> sucideMap= new HashMap<Integer, List<String>>();
        byte[] byt_sucide;
        Map<Integer,List<String>> attemptMap= new HashMap<Integer, List<String>>();
        byte[] byt_attempt;


        Map_list_code mlc= new Map_list_code();
        for(int i=1;i<5000;i++){
            PersonInfo person =new PersonInfo();
            person.setSguardianname(NameBuilder.build());
            person.setSno(i+"");
            person.setSguardianprovince("安徽省");
            person.setSguardiancity("马鞍山市");
            person.setSguardiancountry(codeBuilder.getcountry(random.nextInt(6)));
            person.setSguardianvillage(demo1);
            person.setSguardianhouseno(demo1);
            person.setSguardiantelephone(codeBuilder.getMobilePhone());
            person.setSpatienttelephone(codeBuilder.getMobilePhone());
            person.setSdoctoradvice(codeBuilder.getadvice(random.nextInt(4)));
            person.setDfilltime(codeBuilder.randomDate());
            person.setSchargephysician(NameBuilder.build());
            //首次发病年龄
            int age=codeBuilder.getage(random.nextInt(150)+1);
            ageMap = Map_list_code.code_sno(age, ageMap, i + "");
//            if(ageMap.get(age)==null){
//                List<String> agelist= new ArrayList<>();
//                agelist.add(i+"");
//                ageMap.put(i,agelist);
//            }else{
//                List<String> agelist=ageMap.get(age);
//                agelist.add(i+"");
//                ageMap.put(i,agelist);
//            }
            Element firstage=  DoEncrypt.encrypt(age,pk);
            person.setIfirstdiseaseage(firstage.toBytes());
            //精神病症状
            int int_syscode=codeBuilder.getssymcode(random.nextInt(12));

            symcodeMap=Map_list_code.code_sno(int_syscode,symcodeMap,i+"");
            Element symcode = DoEncrypt.encrypt(int_syscode, pk);
            person.setSsymptomscode(symcode.toBytes());
            //住院次数
            int int_hosp = codeBuilder.gethosp(random.nextInt(10));
            hospMap=Map_list_code.code_sno(int_hosp,hospMap,i+"");
            Element hosp = DoEncrypt.encrypt(int_hosp, pk);
            person.setIhospitalizations(hosp.toBytes());
            //重性精神病代码
            int int_syschcode = codeBuilder.getsychcode(random.nextInt(7));
            syschMap= Map_list_code.code_sno(int_syschcode,syschMap,i+"");
            Element sychcode = DoEncrypt.encrypt(int_syschcode, pk);
            person.setSpsychosiscode(sychcode.toBytes());
            //治愈情况
            int int_treat = codeBuilder.gettreat(random.nextInt(6));
            Element treat = DoEncrypt.encrypt(int_treat, pk);
            treatMap=Map_list_code.code_sno(int_treat,treatMap,i+"");
            person.setTreatmenteffectcode(treat.toBytes());
            //肇事次数
            int int_ihit = random.nextInt(8) + 1;
            Element ihit= DoEncrypt.encrypt(int_ihit,pk);
            ihitMap= Map_list_code.code_sno(int_ihit,ihitMap,i+"");
            person.setIhit(ihit.toBytes());
            //自伤次数
            int int_sucide = random.nextInt(6) + 1;
            Element sucide= DoEncrypt.encrypt(int_sucide,pk);
            sucideMap= Map_list_code.code_sno(int_sucide,sucideMap,i+"");
            person.setIsuicide(sucide.toBytes());

            //自杀次数
            int int_attempt= random.nextInt(4) + 1;
            Element attempt= DoEncrypt.encrypt(int_attempt,pk);
            attemptMap= Map_list_code.code_sno(int_attempt,attemptMap,i+"");
            person.setIattemptedsuicide(attempt.toBytes());
            mapper.addPerson(person);

        }
        System.out.println("结束"+System.currentTimeMillis());


        ByteArrayOutputStream byt=new ByteArrayOutputStream();

        TypeA1Pairing pairing = (TypeA1Pairing) PairingFactory.getPairing(param);
 //插入年龄pid实际长度 1-150
        for (Map.Entry<Integer, List<String>> entry : ageMap.entrySet()) {
            List<String> list = entry.getValue();
            FirstAge firstAge = new FirstAge();
            List<FirstAge> lists = mapper.selectfa();
            int key = entry.getKey();
            int low = 0;
            int high = 149;
            while (low <= high) {
                int mid = (low + high) / 2;
                FirstAge fa = lists.get(mid);
                Element element = pairing.getG1().newElementFromBytes(fa.getAge());
                int age = DoDecrypt.decrypt(element, pk, sk);
                if (key == age) {
                    byte[] bytes = Byt_Code.byt_c(list);
                    fa.setPid(bytes);
                    mapper.updateFristage_code(fa);
                    break;
                }
                if (key > age) {
                    low = mid + 1;
                }
                if (key < age) {
                    high = mid - 1;
                }


            }
        }
        //插入精神病状代码  1-11 99
        for (Map.Entry<Integer, List<String>> entry : symcodeMap.entrySet()) {
            List<String> list = entry.getValue();
            SysmCode sysmCode=new SysmCode();
            List<SysmCode> lists = mapper.selectsysmcode();
            int key = entry.getKey();
            int low = 0;
            int high = 11;
            while (low <= high) {
                int mid = (low + high) / 2;
                SysmCode sc= lists.get(mid);
                Element element = pairing.getG1().newElementFromBytes(sc.getCode());
                int code = DoDecrypt.decrypt(element, pk, sk);
                if (key == code) {
                    byte[] bytes = Byt_Code.byt_c(list);
                    sc.setPid(bytes);
                    mapper.updateSysmcode(sc);
                    break;
                }
                if (key > code) {
                    low = mid + 1;
                }
                if (key < code) {
                    high = mid - 1;
                }


            }
        }

        //插入住院次数 1-100次下 标0-99
        for (Map.Entry<Integer, List<String>> entry : hospMap.entrySet()) {
            List<String> list = entry.getValue();
            Hosp hosp= new Hosp();
            List<Hosp> lists = mapper.selecthosp();
            int key = entry.getKey();
            int low = 0;
            int high = 30;
            while (low <= high) {
                int mid = (low + high) / 2;
                Hosp hp= lists.get(mid);
                Element element = pairing.getG1().newElementFromBytes(hp.getHosps());
                int code = DoDecrypt.decrypt(element, pk, sk);
                if (key == code) {
                    byte[] bytes = Byt_Code.byt_c(list);
                    hp.setPid(bytes);
                    mapper.updatehosp(hp);
                    break;
                }
                if (key > code) {
                    low = mid + 1;
                }
                if (key < code) {
                    high = mid - 1;
                }


            }
        }

        //插入重性精神病代码
        for (Map.Entry<Integer, List<String>> entry : syschMap.entrySet()) {
            List<String> list = entry.getValue();
            Syschcode syschcode= new Syschcode();
            List<Syschcode> lists = mapper.selectsyscode();
            int key = entry.getKey();
            int low = 0;
            int high = 6;
            while (low <= high) {
                int mid = (low + high) / 2;
                syschcode= lists.get(mid);
                Element element = pairing.getG1().newElementFromBytes(syschcode.getSychcode());
                int code = DoDecrypt.decrypt(element, pk, sk);
                if (key == code) {
                    byte[] bytes = Byt_Code.byt_c(list);
                    syschcode.setPid(bytes);
                    mapper.updatesyschcode(syschcode);
                    break;
                }
                if (key > code) {
                    low = mid + 1;
                }
                if (key < code) {
                    high = mid - 1;
                }


            }
        }
        //插入治愈情况0-5
        for (Map.Entry<Integer, List<String>> entry : treatMap.entrySet()) {
            List<String> list = entry.getValue();
            Treat treat= new Treat();
            List<Treat> lists = mapper.selecttreat();
            int key = entry.getKey();
            int low = 0;
            int high = 5;
            while (low <= high) {
                int mid = (low + high) / 2;
                treat= lists.get(mid);
                Element element = pairing.getG1().newElementFromBytes(treat.getTreat());
                int code = DoDecrypt.decrypt(element, pk, sk);
                if (key == code) {
                    byte[] bytes = Byt_Code.byt_c(list);
                    treat.setPid(bytes);
                    mapper.updatetreat(treat);
                    break;
                }
                if (key > code) {
                    low = mid + 1;
                }
                if (key < code) {
                    high = mid - 1;
                }


            }
        }
        //插入肇事次数
        for (Map.Entry<Integer, List<String>> entry : ihitMap.entrySet()) {
            List<String> list = entry.getValue();
            Ihit ihit=new Ihit();
            List<Ihit> lists = mapper.selectihit();
            int key = entry.getKey();
            int low = 0;
            int high = 10;
            while (low <= high) {
                int mid = (low + high) / 2;
                ihit= lists.get(mid);
                Element element = pairing.getG1().newElementFromBytes(ihit.getHit());
                int code = DoDecrypt.decrypt(element, pk, sk);
                if (key == code) {
                    byte[] bytes = Byt_Code.byt_c(list);
                    ihit.setPid(bytes);
                    mapper.updatehit(ihit);
                    break;
                }
                if (key > code) {
                    low = mid + 1;
                }
                if (key < code) {
                    high = mid - 1;
                }


            }
        }

        //插入自伤次数
        for (Map.Entry<Integer, List<String>> entry : sucideMap.entrySet()) {
            List<String> list = entry.getValue();
            Isuicide isuicide= new Isuicide();
            List<Isuicide> lists = mapper.selectsucide();
            int key = entry.getKey();
            int low = 0;
            int high = 6;
            while (low <= high) {
                int mid = (low + high) / 2;
                 isuicide= lists.get(mid);
                Element element = pairing.getG1().newElementFromBytes(isuicide.getSucide());
                int code = DoDecrypt.decrypt(element, pk, sk);
                if (key == code) {
                    byte[] bytes = Byt_Code.byt_c(list);
                    isuicide.setPid(bytes);
                    mapper.updatesucide(isuicide);
                    break;
                }
                if (key > code) {
                    low = mid + 1;
                }
                if (key < code) {
                    high = mid - 1;
                }


            }
        }

/*

        System.out.println(a);*/
//自杀次数
        for (Map.Entry<Integer, List<String>> entry : sucideMap.entrySet()) {
            List<String> list = entry.getValue();
            Iattempt iattempt= new Iattempt();
            List<Iattempt> lists = mapper.selectattempt();
            int key = entry.getKey();
            int low = 0;
            int high = 4;
            while (low <= high) {
                int mid = (low + high) / 2;
                iattempt= lists.get(mid);
                Element element = pairing.getG1().newElementFromBytes(iattempt.getAttempt());
                int code = DoDecrypt.decrypt(element, pk, sk);
                if (key == code) {
                    byte[] bytes = Byt_Code.byt_c(list);
                    iattempt.setPid(bytes);
                    mapper.updateattempt(iattempt);
                    break;
                }
                if (key > code) {
                    low = mid + 1;
                }
                if (key < code) {
                    high = mid - 1;
                }


            }
        }

    }
    //初始化code——id
    @Test
    public void test2() throws Exception {
        param= get_Param.getparam();
        PrivateKey sk=get_sk.createsk(param);
        PublicKey pk=get_pk.createpk(param);
     //   List<PersonInfo> persons=mapper.selectAll();
       // PersonInfo demo=persons.get(0);
      /*  int  code=20;
        Element a= doEncrypt.encrypt(code,pk);
        byte[] bytes = a.toBytes();*/
        //
        System.out.println("开始"+System.currentTimeMillis());
        Random random= new Random();
        TypeA1Pairing pairing = (TypeA1Pairing) PairingFactory.getPairing(param);


           // 首次发病年龄

            for(int i=1;i<=150;i++){
                FirstAge fa= new FirstAge();
                Element firstage=  DoEncrypt.encrypt(i,pk);
                byte[] age=firstage.toBytes();
                fa.setId(i);
                fa.setAge(age);
                byte[] pid= null;
                fa.setPid(pid);
              //  System.out.println(fa);
                mapper.insertFristage(fa);


            }
//验证年龄
        List<FirstAge> selectfa = mapper.selectfa();
        //TypeA1Pairing pairing = (TypeA1Pairing) PairingFactory.getPairing(param);

        for (FirstAge fa:selectfa){

            Element element = pairing.getG1().newElementFromBytes(fa.getAge());
            int age= DoDecrypt.decrypt(element,pk,sk);
            System.out.println(fa.getId()+"     "+age);
        }

//            //精神病症状
        Element code;
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
        //验证年龄
        List<SysmCode> selectsys = mapper.selectsysmcode();

        for (SysmCode sysmcode:selectsys){

            Element element = pairing.getG1().newElementFromBytes(sysmcode.getCode());
            int code1= DoDecrypt.decrypt(element,pk,sk);
            System.out.println(sysmcode.getId()+"     "+code1);
        }
           // Element symcode = doEncrypt.encrypt(codeBuilder.getssymcode(random.nextInt(12)), pk);

            //住院次数
//
        for(int i=1;i<=100;i++){
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
       // 验证住院次数
        List<Hosp> hosps= mapper.selecthosp();


        for (Hosp hosp:hosps){
            Element element = pairing.getG1().newElementFromBytes(hosp.getHosps());
            int code1= DoDecrypt.decrypt(element,pk,sk);
            System.out.println(hosp.getId()+"     "+code1);
        }


            //重性精神病代码
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
////验证重性精神病代码
        List<Syschcode> syschcodes = mapper.selectsyscode();


        for (Syschcode syschcode:syschcodes){
            Element element = pairing.getG1().newElementFromBytes(syschcode.getSychcode());
            int code1= DoDecrypt.decrypt(element,pk,sk);
            System.out.println(syschcode.getId()+"     "+code1);
        }
            //治愈情况
            Element treat= DoEncrypt.encrypt(codeBuilder.gettreat(random.nextInt(6)), pk);
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
//        //验证
        List<Treat> treats = mapper.selecttreat();
        for(Treat treat2:treats){
            Element element = pairing.getG1().newElementFromBytes(treat2.getTreat());
            int code1= DoDecrypt.decrypt(element,pk,sk);
            System.out.println(treat2.getId()+"  "+code1);
        }

//肇事
        for(int i=0;i<=50;i++) {
            Ihit ihit= new Ihit();
            ihit.setId(i);

            code=  DoEncrypt.encrypt(i,pk);


            byte[] sys=code.toBytes();
            ihit.setHit(sys);
            byte[] pid=null;
            ihit.setPid(pid);
            mapper.inserthit(ihit);
        }
     //   验证
        List<Ihit> ihits = mapper.selectihit();
        for(Ihit ihit:ihits){
            Element element = pairing.getG1().newElementFromBytes(ihit.getHit());
            int code1= DoDecrypt.decrypt(element,pk,sk);
            System.out.println(ihit.getId()+"  "+code1);
        }


//            //肇事次数

//
//          //  mapper.addPerson(person);


        System.out.println("结束"+System.currentTimeMillis());
/*
        System.out.println(a);*/



    }
//    解密
   @Test
    public void test3() throws Exception {
       PrivateKey sk=get_sk.createsk(get_Param.getparam());
       PublicKey pk=get_pk.createpk(get_Param.getparam());
       List<PersonInfo> persons=mapper.selectAll();
       param=get_Param.getparam();
       TypeA1Pairing pairing = (TypeA1Pairing) PairingFactory.getPairing(param);
//       for(PersonInfo person:persons){
//           if(("1031").equals(person.getSno())){
//               System.out.println(person.getSchargephysician()+"  "+Decrycode.decrypt(person.getIfirstdiseaseage(),param,sk,pk)+"  "
//                       +Decrycode.decrypt(person.getSsymptomscode(),param,sk,pk)+"  "+Decrycode.decrypt(person.getIhospitalizations(),param,sk,pk)
//               +" "+Decrycode.decrypt(person.getSpsychosiscode(),param,sk,pk)+" "+Decrycode.decrypt(person.getTreatmenteffectcode(),param,sk,pk)+" "
//                       +Decrycode.decrypt(person.getIhit(),param,sk,pk));
//
//           }
//           if(("1032").equals(person.getSno())){
//               System.out.println(person.getSchargephysician()+"  "+Decrycode.decrypt(person.getIfirstdiseaseage(),param,sk,pk)+"  "
//                       +Decrycode.decrypt(person.getSsymptomscode(),param,sk,pk)+"  "+Decrycode.decrypt(person.getIhospitalizations(),param,sk,pk)
//                       +" "+Decrycode.decrypt(person.getSpsychosiscode(),param,sk,pk)+" "+Decrycode.decrypt(person.getTreatmenteffectcode(),param,sk,pk)+" "
//                       +Decrycode.decrypt(person.getIhit(),param,sk,pk));
//
//           }
//
//           if(("1033").equals(person.getSno())){
//               System.out.println(person.getSchargephysician()+"  "+Decrycode.decrypt(person.getIfirstdiseaseage(),param,sk,pk)+"  "
//                       +Decrycode.decrypt(person.getSsymptomscode(),param,sk,pk)+"  "+Decrycode.decrypt(person.getIhospitalizations(),param,sk,pk)
//                       +" "+Decrycode.decrypt(person.getSpsychosiscode(),param,sk,pk)+" "+Decrycode.decrypt(person.getTreatmenteffectcode(),param,sk,pk)+" "
//                       +Decrycode.decrypt(person.getIhit(),param,sk,pk));
//
//           }
//
//           if(("1034").equals(person.getSno())){
//               System.out.println(person.getSchargephysician()+"  "+Decrycode.decrypt(person.getIfirstdiseaseage(),param,sk,pk)+"  "
//                       +Decrycode.decrypt(person.getSsymptomscode(),param,sk,pk)+"  "+Decrycode.decrypt(person.getIhospitalizations(),param,sk,pk)
//                       +" "+Decrycode.decrypt(person.getSpsychosiscode(),param,sk,pk)+" "+Decrycode.decrypt(person.getTreatmenteffectcode(),param,sk,pk)+" "
//                       +Decrycode.decrypt(person.getIhit(),param,sk,pk));
//
//           }
//
//           if(("1035").equals(person.getSno())){
//               System.out.println(person.getSchargephysician()+"  "+Decrycode.decrypt(person.getIfirstdiseaseage(),param,sk,pk)+"  "
//                       +Decrycode.decrypt(person.getSsymptomscode(),param,sk,pk)+"  "+Decrycode.decrypt(person.getIhospitalizations(),param,sk,pk)
//                       +" "+Decrycode.decrypt(person.getSpsychosiscode(),param,sk,pk)+" "+Decrycode.decrypt(person.getTreatmenteffectcode(),param,sk,pk)+" "
//                       +Decrycode.decrypt(person.getIhit(),param,sk,pk));
//
//           }
//
//           if(("1036").equals(person.getSno())){
//               System.out.println(person.getSchargephysician()+"  "+Decrycode.decrypt(person.getIfirstdiseaseage(),param,sk,pk)+"  "
//                       +Decrycode.decrypt(person.getSsymptomscode(),param,sk,pk)+"  "+Decrycode.decrypt(person.getIhospitalizations(),param,sk,pk)
//                       +" "+Decrycode.decrypt(person.getSpsychosiscode(),param,sk,pk)+" "+Decrycode.decrypt(person.getTreatmenteffectcode(),param,sk,pk)+" "
//                       +Decrycode.decrypt(person.getIhit(),param,sk,pk));
//
//           }
//           if(("1037").equals(person.getSno())){
//               System.out.println(person.getSchargephysician()+"  "+Decrycode.decrypt(person.getIfirstdiseaseage(),param,sk,pk)+"  "
//                       +Decrycode.decrypt(person.getSsymptomscode(),param,sk,pk)+"  "+Decrycode.decrypt(person.getIhospitalizations(),param,sk,pk)
//                       +" "+Decrycode.decrypt(person.getSpsychosiscode(),param,sk,pk)+" "+Decrycode.decrypt(person.getTreatmenteffectcode(),param,sk,pk)+" "
//                       +Decrycode.decrypt(person.getIhit(),param,sk,pk));
//
//           }
//           if(("1038").equals(person.getSno())){
//               System.out.println(person.getSchargephysician()+"  "+Decrycode.decrypt(person.getIfirstdiseaseage(),param,sk,pk)+"  "
//                       +Decrycode.decrypt(person.getSsymptomscode(),param,sk,pk)+"  "+Decrycode.decrypt(person.getIhospitalizations(),param,sk,pk)
//                       +" "+Decrycode.decrypt(person.getSpsychosiscode(),param,sk,pk)+" "+Decrycode.decrypt(person.getTreatmenteffectcode(),param,sk,pk)+" "
//                       +Decrycode.decrypt(person.getIhit(),param,sk,pk));
//
//           }
//           if(("1039").equals(person.getSno())){
//               System.out.println(person.getSchargephysician()+"  "+Decrycode.decrypt(person.getIfirstdiseaseage(),param,sk,pk)+"  "
//                       +Decrycode.decrypt(person.getSsymptomscode(),param,sk,pk)+"  "+Decrycode.decrypt(person.getIhospitalizations(),param,sk,pk)
//                       +" "+Decrycode.decrypt(person.getSpsychosiscode(),param,sk,pk)+" "+Decrycode.decrypt(person.getTreatmenteffectcode(),param,sk,pk)+" "
//                       +Decrycode.decrypt(person.getIhit(),param,sk,pk));
//
//           }
//
//
//
//
//       }
//
       Element c1 = DoEncrypt.encrypt(5, pk);
       Element c2 = DoEncrypt.encrypt(5, pk);
       double d1=System.currentTimeMillis();

//       Element c3=c1.add(c2);
//       double d2=System.currentTimeMillis();
//       System.out.println("第一次对5进行加密的密文c1:"+c1.toString());
//       System.out.println("第二次对5进行加密的密文c2:"+c2.toString());
//       System.out.println(d2-d1);
       byte[] bytes = c1.toBytes();
       for (int i=0;i<bytes.length;i++){
          bytes[i]= (byte) (bytes[i]& 0xff);
         //  System.out.println((bt & 0xff)+"  "+bt);
       }
       Element element = pairing.getG1().newElementFromBytes(bytes);
       System.out.println(DoDecrypt.decrypt(element,pk,sk)+"fsdfsdfsf");
   }
    @Test
    public void test4() throws Exception {
        PrivateKey sk=get_sk.createsk(get_Param.getparam());
        PublicKey pk=get_pk.createpk(get_Param.getparam());
        List<PersonInfo> persons=mapper.selectAll();
        int m=3;
        BigInteger m1=BigInteger.valueOf(1);
        param=get_Param.getparam();
        TypeA1Pairing pairing = (TypeA1Pairing) PairingFactory.getPairing(param);
        Element a= DoEncrypt.encrypt(199,pk);
        byte[] bytes = a.toBytes();
        String str= new String(bytes);
        System.out.println(str);
        byte[] bytes1 = str.getBytes();
        if(bytes.equals(bytes)){
            System.out.println("sss");
            System.out.println(str.length());
            Element element = pairing.getG1().newElementFromBytes(bytes);
            System.out.println(DoDecrypt.decrypt(element,pk,sk));
        }
    }

}
