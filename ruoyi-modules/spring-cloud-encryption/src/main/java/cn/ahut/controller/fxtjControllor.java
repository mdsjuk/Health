package cn.ahut.controller;


import cn.ahut.Mapper.JsbDataShow;
import cn.ahut.entity.DictInfo;
import cn.ahut.entity.FileDemo;
import cn.ahut.entity.Jmpsychosispersoninfop;
import cn.ahut.entity.Psychosispersoninfop;
import cn.ahut.entity.key.PrivateKey;
import cn.ahut.entity.key.PublicKey;
import cn.ahut.untils.BGNKey.get_PrivateKey;
import cn.ahut.untils.BGNKey.get_PubilcKey;
import cn.ahut.untils.BGNOp.DoDecrypt;
import cn.ahut.untils.DataOp.JsbOp;
import cn.ahut.untils.Get_Param;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.PairingParameters;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
import it.unisa.dia.gas.plaf.jpbc.pairing.a1.TypeA1Pairing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by wzw on 2020/10/30.
 */

@RestController
@RequestMapping("/fxtj")
public class fxtjControllor extends BaseController {
    @Autowired
    JsbDataShow jsbDataShow;
    @Autowired
    private get_PrivateKey get_sk;
    @Autowired
    private get_PubilcKey get_pk;
    @Autowired
    private Get_Param get_Param;
    @Autowired private JsbOp jsbop;

    private PairingParameters param ;


    /**
     * 统计
     * @param
     * @return
     */
    @PostMapping("/tj")
    public AjaxResult dataTj(@RequestBody Map map) throws Exception {
        param=get_Param.getparam();
        //配对曲线 通过曲线去找群G1的元素
        TypeA1Pairing pairing = (TypeA1Pairing) PairingFactory.getPairing(param);
        PublicKey pk = get_pk.createpk(param);
        PrivateKey sk = get_sk.createsk(param);
        String y1=(String) map.get("y1");

        String y2=(String) map.get("y2");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
        Date date1 = simpleDateFormat.parse(y1);
        Date date2 = simpleDateFormat.parse(y2);


        String c1=(String) map.get("c1");
        String c2=(String) map.get("c2");
        Map<String, String> tj=new HashMap<>();
        if(c1.equals("精神病数据统计")){
            if(c2.equals("肇事次数")){
                tj = jsbop.zscs(date1, date2);
            }
            if(c2.equals("住院次数")){

                tj = jsbop.zycs(date1, date2);

            }
            if(c2.equals("自伤次数")){

                tj = jsbop.zshangcs(date1, date2);

            }
            if(c2.equals("自杀次数")){

                tj = jsbop.zshacs(date1, date2);

            }
        }
        return    AjaxResult.success(tj);


    }



}
