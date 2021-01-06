package cn.ahut;


import cn.ahut.Mapper.personMapper;
import cn.ahut.untils.AddLength;
import cn.ahut.untils.BGNKey.get_PrivateKey;
import cn.ahut.untils.BGNKey.get_PubilcKey;
import cn.ahut.untils.DataCreate.sysmId;
import cn.ahut.untils.Get_Param;
import cn.ahut.untils.SigKey.Get_SigKey;
import it.unisa.dia.gas.jpbc.PairingParameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={NscrlV1Application.class})
public class sysmcode_ini {
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
    @Autowired
    sysmId symid;

    private PairingParameters param;

    //初始化索引结构
    @Test
    public void test() throws Exception {
        symid.age_code();
        symid.sysm_code();
        symid.hosp_code();
        symid.ihit_code();
        symid.sysch_code();
        symid.treat_code();
        symid.attempt_code();
        symid.suicide_code();
    }
    @Test
    public void test1() throws Exception {
        symid.versysmcode();
    }
}