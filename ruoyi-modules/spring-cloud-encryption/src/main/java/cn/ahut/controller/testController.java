package cn.ahut.controller;

import cn.ahut.Mapper.personMapper;
import cn.ahut.entity.PersonInfo;
import cn.ahut.entity.key.PrivateKey;
import cn.ahut.entity.key.PublicKey;
import cn.ahut.untils.BGNKey.get_PrivateKey;
import cn.ahut.untils.BGNKey.get_PubilcKey;
import cn.ahut.untils.BGNOp.Doselect;
import cn.ahut.untils.Get_Param;
import cn.ahut.untils.SigOp.HoldVerPro;
import com.alibaba.fastjson.JSONObject;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.PairingParameters;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
import it.unisa.dia.gas.plaf.jpbc.pairing.a1.TypeA1Pairing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Controller
public class testController {
    @Autowired
    private personMapper mapper;
    @Autowired
    private Get_Param getParam;
    @Autowired
    private get_PrivateKey psk;
    @Autowired
    private get_PubilcKey puk;
    @Autowired
    private Doselect ds;
    @Autowired
    private HoldVerPro hvp;

    @RequestMapping("/quick2")
    @ResponseBody

    public List<PersonInfo> findAll() throws Exception {
        PairingParameters param;
        System.out.println("q2");
        List<PersonInfo> persons=mapper.selectAll();

        param= getParam.getparam();
        int count=0;
        List<PersonInfo> list = new ArrayList<PersonInfo>();
        PrivateKey sk= psk.createsk(param);
        PublicKey pk=puk.createpk(param);
        BigInteger m1=BigInteger.valueOf(2);
        TypeA1Pairing pairing = (TypeA1Pairing) PairingFactory.getPairing(param);
        double t1=System.currentTimeMillis();

        for (PersonInfo person:persons) {
            count++;
            byte[] ihpz = person.getIhospitalizations();
            Element b = pairing.getG1().newElementFromBytes(person.getSpsychosiscode());
           // int i=doDecrypt.decrypt(b,pk,sk);
            // System.out.println(i);
        if(ds.select(b,pk,sk,m1)==true){
               count=count+1;
             list.add(person);
           }
        
        }
        double t2=System.currentTimeMillis();
        System.out.println("查询40条时间："+(t2-t1)+"ms");

        return list;
    }
    @RequestMapping("/quick3")

    public void  hvpTest() throws Exception{

        JSONObject mJson=new JSONObject();
        JSONObject cJson=new JSONObject();
        JSONObject sJson=new JSONObject();
        JSONObject deJson=new JSONObject();

        mJson.put("dir","d:/test/");
        mJson.put("filename","timg.jpg");
        cJson.put("dir","d:/test/");
        cJson.put("filename","en.jpg");
        sJson.put("dir","d:/test/");
        sJson.put("filename","sig.jpg");
        deJson.put("dir","d:/test/");
        deJson.put("filename","de.jpg");

        hvp.cipherGen(mJson,cJson);
        hvp.deCipher(cJson,deJson);
        hvp.sigGen(mJson,sJson);
        //hvp.chanLlAge(cJson,sJson);
    }
}
